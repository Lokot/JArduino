package org.sintef.jarduino;

import org.sintef.jarduino.IJArduinoMessageHandler;
import org.sintef.jarduino.msg.DigitalPinStateNotification;
import org.sintef.jarduino.msg.DigitalSensorReadMsg;
import org.sintef.jarduino.msg.DigitalSensorReadResultMsg;

public interface IJArduinoSensorMessageHandler extends IJArduinoMessageHandler {
	
	public void handleDigitalSensorRead(DigitalSensorReadMsg msg);
	
	public void handleDigitalSensorReadResult(DigitalSensorReadResultMsg msg);
	
	public void handleDigitalPinStateNotification(DigitalPinStateNotification msg);
}
