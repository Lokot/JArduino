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
import org.sintef.jarduino.DigitalState;
import org.sintef.jarduino.JArduinoSensorMessageHandler;
import org.sintef.jarduino.JArduinoSensorProtocol;
import org.sintef.jarduino.JArduinoSensorProtocolPacket;

public class DigitalPinStateNotification extends JArduinoSensorProtocolPacket {

	private DigitalPin pin;
	private DigitalState value;

	public DigitalPinStateNotification(DigitalPin pin, DigitalState value) {
		setCommandID(JArduinoSensorProtocol.DIGITAL_PIN_STATE);
		setByteValue(pin.getValue());
		this.pin = pin;
		setByteValue(value.getValue());
		this.value = value;
	}

	public DigitalPinStateNotification(byte[] packet) {
		setPacketData(packet);
		pin = DigitalPin.fromValue(buffer.get());
		value = DigitalState.fromValue(buffer.get());	
	}

	@Override
	public void acceptHandler(JArduinoSensorMessageHandler v) {
		v.handleDigitalPinStateNotification(this);
	}

	@Override
	public String toString() {
		String myString = "digitalPinStateNotification:";
		myString += " [pin: " + pin + ", value: " + value + "]";
		return myString;
	}

	public DigitalPin getPin() {
		return pin;
	}
	
	public DigitalState getValue() {
		return value;
	}

}