package org.sintef.jarduino.examples.sensorread;

import org.sintef.jarduino.InvalidPinTypeException;
import org.sintef.jarduino.JArduinoDSensors;

public class DigitalPinStateNotifier extends JArduinoDSensors {
	
	public DigitalPinStateNotifier(String port) {
		super(port);
	}

	@Override
	protected void setup() throws InvalidPinTypeException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loop() throws InvalidPinTypeException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) throws InvalidPinTypeException {
		JArduinoDSensors arduino = new Dht22Reader("COM6");
		arduino.runArduinoProcess();
	}

}
