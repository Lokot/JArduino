package org.sintef.jarduino;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.sintef.jarduino.DigitalPin;
import org.sintef.jarduino.DigitalState;
import org.sintef.jarduino.FixedSizePacket;
import org.sintef.jarduino.InvalidPinTypeException;
import org.sintef.jarduino.JArduino;
import org.sintef.jarduino.JArduinoCom;
import org.sintef.jarduino.JArduinoProtocolPacket;
import org.sintef.jarduino.Pin;
import org.sintef.jarduino.ProtocolConfiguration;
import org.sintef.jarduino.msg.DigitalPinStateNotification;
import org.sintef.jarduino.msg.DigitalSensorReadResultMsg;
import org.sintef.jarduino.observer.JArduinoObserver;

public abstract class JArduinoDSensors extends JArduino {

	private ExecutorService pinStateExecutor = Executors
			.newSingleThreadExecutor();

	protected JArduinoDriverSensorMessageHandler sensorMessageHandler;

	private String sensorRead_result;
	private boolean sensorRead_result_available;
	private final Object sensorReadMonitor = "sensorReadMonitor";

	public JArduinoDSensors() throws JArduinoConnectionException {
		super();
		init();
	}

	public JArduinoDSensors(JArduinoCom com, ProtocolConfiguration prot) throws JArduinoConnectionException {
		super(com, prot);
		init();
	}

	public JArduinoDSensors(JArduinoCom com) throws JArduinoConnectionException {
		super(com);
		init();
	}

	public JArduinoDSensors(String ID, JArduinoCom com,
			ProtocolConfiguration prot) throws JArduinoConnectionException {
		super(ID, com, prot);
		init();
	}

	public JArduinoDSensors(String ID, JArduinoCom com) throws JArduinoConnectionException {
		super(ID, com);
		init();
	}

	public JArduinoDSensors(String id, ProtocolConfiguration prot) throws JArduinoConnectionException {
		super(id, prot);
		init();
	}

	public JArduinoDSensors(String id) throws JArduinoConnectionException {
		super(id);
		init();
	}

	private void init() {
		sensorMessageHandler = new JArduinoDriverSensorMessageHandler();
		serial.register(sensorMessageHandler);
	}

	public String sensorRead(DigitalPin pin, JArduinoSensor sensor,
			JArduinoSensorParametr parametr) {
		return sensorRead(pin, sensor, parametr, -1);
	}

	public String sensorRead(DigitalPin pin, JArduinoSensor sensor,
			JArduinoSensorParametr parametr, long wait) {
		if (wait < 0)
			wait = 500;
		try {
			synchronized (sensorReadMonitor) {
				sensorRead_result_available = false;
				// Create message using the factory
				FixedSizePacket p = JArduinoSensorProtocol.createSensorRead(
						pin, sensor, parametr);
				// Create message using the factory
				serial.receiveMsg(p.getPacket());
				sensorReadMonitor.wait(wait);
				if (sensorRead_result_available)
					return sensorRead_result;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// The exception alternative
		// throw new
		// Error("JArduino: Timeout waiting for the result of analogRead");
		// The error message alternative
		System.err
				.println("JArduino: Timeout waiting for the result of sensorRead");
		return null;

	}

	public String sensorRead(Pin pin, JArduinoSensor sensor, JArduinoSensorParametr parametr,
			long wait) throws InvalidPinTypeException {
		if (!pin.isDigital())
			throw new InvalidPinTypeException();
		return sensorRead(DigitalPin.fromValue(pin.getValue()), sensor,
				parametr, wait);
	}

	public String sensorRead(Pin pin, JArduinoSensor sensor, JArduinoSensorParametr parametr)
			throws InvalidPinTypeException {
		return sensorRead(pin, sensor, parametr, -1);
	}

	private class JArduinoDriverSensorMessageHandler extends
			JArduinoSensorMessageHandler implements JArduinoObserver {

		public void receiveMsg(byte[] msg) {
			JArduinoProtocolPacket p = (JArduinoProtocolPacket) JArduinoSensorProtocol
					.createMessageFromPacket(msg);
			if (p != null) {
				p.acceptHandler(this);
			}
		}

		public void handleDigitalSensorReadResult(DigitalSensorReadResultMsg msg) {
			sensorRead_result = msg.getValue();
			sensorRead_result_available = true;
			synchronized (sensorReadMonitor) {
				sensorReadMonitor.notify();
			}
		}

		public void handleDigitalPinStateNotification(
				DigitalPinStateNotification msg) {
			receiveDigitalPinStateNotification(msg.getPin(), msg.getValue());
		}

	}

	protected void receiveDigitalPinStateNotification(final DigitalPin pin,
			final DigitalState value) {
		pinStateExecutor.submit(new Runnable() {
			public void run() {
				interruptDigitalPinState(pin, value);
			}
		});
	}

	// Operation to be redefined to handle interrupts
	protected void interruptDigitalPinState(DigitalPin pin, DigitalState state) {
	}

	public ExecutorService getPinStateExecutor() {
		return pinStateExecutor;
	}

	public void close() {
		serial.close();
	}

}
