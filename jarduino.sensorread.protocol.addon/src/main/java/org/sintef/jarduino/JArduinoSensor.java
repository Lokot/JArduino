package org.sintef.jarduino;

import java.util.HashMap;
import java.util.Map;

public interface JArduinoSensor {

	public byte getValue();
	
	public JArduinoSensorParametr[] getParams();
	
	public JArduinoSensorParametr getParametrByVal(byte parVal);
	
	public static final Map<Byte, JArduinoSensor> map = new HashMap<Byte, JArduinoSensor>();

	public static JArduinoSensor fromValue(byte b) {
		return map.get(b);
	}
}
