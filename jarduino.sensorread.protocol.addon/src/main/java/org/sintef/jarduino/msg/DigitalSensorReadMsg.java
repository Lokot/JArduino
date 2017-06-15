/**
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Authors: Franck Fleurey and Brice Morin
 * Company: SINTEF IKT, Oslo, Norway
 * Date: 2011
 */
package org.sintef.jarduino.msg;

import org.sintef.jarduino.DigitalPin;
import org.sintef.jarduino.JArduinoSensor;
import org.sintef.jarduino.JArduinoSensorMessageHandler;
import org.sintef.jarduino.JArduinoSensorParametr;
import org.sintef.jarduino.JArduinoSensorProtocol;
import org.sintef.jarduino.JArduinoSensorProtocolPacket;

public class DigitalSensorReadMsg extends JArduinoSensorProtocolPacket {

	private DigitalPin pin;
	private JArduinoSensor sensor;
	private JArduinoSensorParametr parametr;

	public DigitalSensorReadMsg(DigitalPin pin, JArduinoSensor sensor,
			JArduinoSensorParametr parametr) {
		setCommandID(JArduinoSensorProtocol.DIGITAL_SENSOR_READ);
		setByteValue(pin.getValue());
		setByteValue(sensor.getValue());
		setByteValue(parametr.getValue());
		this.pin = pin;
		this.sensor = sensor;
		this.parametr = parametr;
	}

	public DigitalSensorReadMsg(byte[] packet) {
		setPacketData(packet);
		pin = DigitalPin.fromValue(buffer.get());
		sensor = JArduinoSensor.fromValue(buffer.get());
		parametr = sensor.getParametrByVal(buffer.get());
	}

	@Override
	public void acceptHandler(JArduinoSensorMessageHandler v) {
		v.handleDigitalSensorRead(this);
	}

	@Override
	public String toString() {
		String myString = "digitalSensorRead:";
		myString += " [pin: " + pin + ", sensor: " + sensor + ", parametr: "
				+ parametr + "]";
		return myString;
	}

	public DigitalPin getPin() {
		return pin;
	}

	public JArduinoSensor getSensor() {
		return sensor;
	}

	public JArduinoSensorParametr getParametr() {
		return parametr;
	}

}