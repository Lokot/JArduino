package org.sintef.jarduino.examples.sensorread;

import org.sintef.jarduino.InvalidPinTypeException;
import org.sintef.jarduino.JArduinoConnectionException;
import org.sintef.jarduino.JArduinoDSensors;
import org.sintef.jarduino.Pin;
import org.sintef.jarduino.examples.sensorread.sensors.Dht22Params;
import org.sintef.jarduino.examples.sensorread.sensors.Sensor;

public class Dht22Reader extends JArduinoDSensors {

	private final Pin dhtPin = p2;

	public Dht22Reader(String port) throws JArduinoConnectionException {
		super(port);
	}

	@Override
	protected void setup() throws InvalidPinTypeException {

	}

	@Override
	protected void loop() throws InvalidPinTypeException {
		String tempVal = sensorRead(dhtPin, Sensor.DHT22, Dht22Params.TEMP,
				3000);
		String humVal = sensorRead(dhtPin, Sensor.DHT22, Dht22Params.HUM);
		System.out.println("Temperature is " + tempVal + "C, Humidity is "
				+ humVal + "%");
		delay(1000);
	}

	public static void main(String[] args) throws Exception {
		JArduinoDSensors arduino = new Dht22Reader("COM9");
		arduino.runArduinoProcess();
	}
}
