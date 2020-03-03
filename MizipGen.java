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

public class MiZipGen{
	// Library that calculate keys A/B for a Mizip key
	// Based on: https://github.com/iceman1001/proxmark3/blob/master/client/scripts/calc_mizip.lua
	
	// 6 byte xor of specific sector (key A/B): sector 0 doesn't change
	private final static String xortable[][] = {
			{"1", "09125a2589e5", "F12C8453D821"},
			{"2", "AB75C937922F", "73E799FE3241"},
			{"3", "E27241AF2C09", "AA4D137656AE"},
			{"4", "317AB72F4490", "B01327272DFD"}
		};
	
	// Method that convert HEX String to byte array
	private static byte[] hexToBytes(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                             + Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}
	
	// Method that calculate a key
	private static String calcKey(byte[] uid, byte[] xorkey, char keyType){
		int position[];
		if(keyType == 'A'){
			position = new int[]{0, 1, 2, 3, 0, 1};
		}else if(keyType == 'B'){
			position = new int[]{2, 3, 0, 1, 2, 3};
		}else{
			throw new IllegalArgumentException("invalid key type!"); 
		}
		
		String key = "";
		for(int i = 0; i<position.length; i++){
			key = key.concat(String.format("%02x", (byte)(uid[position[i]] ^ xorkey[i])));
		}

		return key;
	}
		
	public static String[][] genAllKeys(String uid){
		// Check UID length
		if(uid.length() != 8){
			System.out.println("UID must be 8 character length!");
			System.exit(1);
		}
		
		String keyGenA, keyGenB;
		String[][] keys = new String[2][5];
		
		// Define common sector 0 keys
		keys[0][0] = "a0a1a2a3a4a5";
		keys[1][0] = "b4c132439eef";
		
		// Cycle to generate every sector key
		for(int i = 0; i<xortable.length; i++){
			keyGenA = calcKey(hexToBytes(uid), hexToBytes(xortable[i][1]), 'A');
			keyGenB = calcKey(hexToBytes(uid), hexToBytes(xortable[i][2]), 'B');
			keys[0][i+1] = keyGenA;
			keys[1][i+1] = keyGenB;
		}
		
		return keys;
	}
	
	public static String genKey(String uid, char type, int sector){
		// define xortable xor position
		int b;
		if(Character.toUpperCase(type)=='A'){b = 1;}else{b = 2;};
		
		// common keys for sector 0
		if(sector == 0){
			if(Character.toUpperCase(type)=='A'){
				return "a0a1a2a3a4a5";
			}else{
				return "b4c132439eef";
			}
		}else{
			// calculate the specific key
			return calcKey(hexToBytes(uid), hexToBytes(xortable[sector-1][Character.toUpperCase(type) == 'A' ? 1 : 2]), type);
		}
	}
}
