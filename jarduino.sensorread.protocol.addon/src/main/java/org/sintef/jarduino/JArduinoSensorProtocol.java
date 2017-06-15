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
package org.sintef.jarduino;

import org.sintef.jarduino.DigitalPin;
import org.sintef.jarduino.FixedSizePacket;
import org.sintef.jarduino.msg.DigitalPinStateNotification;
import org.sintef.jarduino.msg.DigitalSensorReadMsg;
import org.sintef.jarduino.msg.DigitalSensorReadResultMsg;

public abstract class JArduinoSensorProtocol {

	public static final byte DIGITAL_SENSOR_READ = 51;
	public static final byte DIGITAL_SENSOR_READ_RESULT = 52;
	public static final byte DIGITAL_PIN_STATE = 53;

	public static FixedSizePacket createMessageFromPacket(byte[] packet) {
		byte packetType = packet[4];
		FixedSizePacket result = null;
		switch (packetType) {
		case DIGITAL_SENSOR_READ:
			result = new DigitalSensorReadMsg(packet);
			break;
		case DIGITAL_SENSOR_READ_RESULT:
			result = new DigitalSensorReadResultMsg(packet);
			break;
		case DIGITAL_PIN_STATE:
			result = new DigitalPinStateNotification(packet);
			break;
		default:
			break;
		}
		return result;
	}

	public static FixedSizePacket createSensorRead(DigitalPin pin,
			JArduinoSensor sensor, JArduinoSensorParametr parametr) {
		return new DigitalSensorReadMsg(pin, sensor, parametr);
	}

}
