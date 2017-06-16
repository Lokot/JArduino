package org.sintef.jarduino.examples.sensorread;

import org.sintef.jarduino.DigitalPin;
import org.sintef.jarduino.DigitalState;
import org.sintef.jarduino.InvalidPinTypeException;
import org.sintef.jarduino.JArduinoDSensors;

public class DigitalPinStateNotifier extends JArduinoDSensors {

	private DigitalPin inputPin = DigitalPin.PIN_13;
	
	public DigitalPinStateNotifier(String port) {
		super(port);
	}

	@Override
	protected void setup() throws InvalidPinTypeException {
		pinMode(inputPin, INPUT);
	}

	@Override
	protected void loop() throws InvalidPinTypeException {
		// TODO Auto-generated method stub
		delay(1000);
	}

	@Override
	protected void interruptDigitalPinState(DigitalPin pin, DigitalState state) {

		switch (pin) {
		case PIN_13:
			System.out.println("Pin " + pin.name() + " state is " + state.name());
			break;

		default:
			break;
		}

	}

	public static void main(String[] args) throws InvalidPinTypeException {
		JArduinoDSensors arduino = new Dht22Reader("COM6");
		arduino.runArduinoProcess();
	}

}
