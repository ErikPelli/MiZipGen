/*	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/**
 * Library that calculate keys A/B for a MiZip key
 * Based on: https://github.com/iceman1001/proxmark3/blob/master/client/scripts/calc_mizip.lua
 */
public class MiZipGen{

	// 6 byte xor of specific sector (key A/B)
	private final static String xortable[][] = {
			{"0", "A0A1A2A3A4A5", "B4C132439EEF"}, // sector 0 keys are costants
			{"1", "09125a2589e5", "F12C8453D821"},
			{"2", "AB75C937922F", "73E799FE3241"},
			{"3", "E27241AF2C09", "AA4D137656AE"},
			{"4", "317AB72F4490", "B01327272DFD"}
	};
	
	// Method that converts HEX String to byte array
	private static byte[] hexToBytes(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                             + Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}
	
	// Method that calculates a key
	private static String calcKey(byte[] uid, byte[] xorkey, int keyType){
		int position[];
		if(keyType == 1){
			position = new int[]{0, 1, 2, 3, 0, 1};
		}else{
			position = new int[]{2, 3, 0, 1, 2, 3};
		}
		
		String resultKey = "";
		for(int i = 0; i<position.length; i++){
			resultKey = resultKey.concat(String.format("%02X", (byte)(uid[position[i]] ^ xorkey[i])));
		}

		return resultKey;
	}
	
	 /**
	 * Generates all the keys from the UID
	 * @param uid UID of Mifare Tag as String
	 * @return multidimensional array with all keys
	 */	
	public static String[][] genAllKeys(String uid){
		// Check UID length
		if(uid.length() != 8){
			throw new IllegalArgumentException("UID must be 8 characters long!");
		}
		
		// result returned next
		String[][] keys = new String[5][2];
		
		// Define common sector 0 keys
		keys[0][0] = xortable[0][1];
		keys[0][1] = xortable[0][2];
		
		// Cycle to generate every sector key
		for(int i = 1; i < keys.length; i++){
			keys[i][0] = calcKey(hexToBytes(uid), hexToBytes(xortable[i][1]), 1);
			keys[i][1] = calcKey(hexToBytes(uid), hexToBytes(xortable[i][2]), 2);
		}
		 
		return keys;
	}
	
	/**
	 * It generates a specified key
	 * @param uid UID of Mifare Tag as String
	 * @param type type of the sector key ('A' or 'B')
	 * @param sector sector of the result key
	 * @return result key as String
	 */
	public static String genKey(String uid, int type, int sector){	
		// even the type 
		type = Character.toUpperCase(type);
		
		// Check UID length
		if(uid.length() != 8){
			throw new IllegalArgumentException("UID must be 8 characters long!");
		}
		
		// check the sector type
		if(!(type == 'A' || type == 'B')){
			throw new IllegalArgumentException("Invalid key type!");
		}
		
		// assign to A index 1 and to B index 2 (xortable)
		type = (type == 'A') ? 1 : 2;
		
		// use common keys if it's sector 0
		if(sector == 0){
			return xortable[sector][type];
		}else{
			// calculate the specific key
			return calcKey(hexToBytes(uid), hexToBytes(xortable[sector][type]), type);
		}
	}
}
