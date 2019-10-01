public class GenAB{
	// Library that calculate keys A/B for a Mizip key
	// Based on: https://github.com/iceman1001/proxmark3/blob/master/client/scripts/calc_mizip.lua
	
	// 6 byte xor, sector key A/B
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
	
	// Method that calculate the keys
	private static String calcKey(byte[] uid, byte[] xorkey, char keyType){
		String key = "";
		byte[] p = new byte[6];
		if(keyType == 'A'){
			p[0] = (byte)(uid[0] ^ xorkey[0]);
			p[1] = (byte)(uid[1] ^ xorkey[1]);
			p[2] = (byte)(uid[2] ^ xorkey[2]);
			p[3] = (byte)(uid[3] ^ xorkey[3]);
			p[4] = (byte)(uid[0] ^ xorkey[4]);
			p[5] = (byte)(uid[1] ^ xorkey[5]);
		}
		if(keyType == 'B'){
			p[0] = (byte)(uid[2] ^ xorkey[0]);
			p[1] = (byte)(uid[3] ^ xorkey[1]);
			p[2] = (byte)(uid[0] ^ xorkey[2]);
			p[3] = (byte)(uid[1] ^ xorkey[3]);
			p[4] = (byte)(uid[2] ^ xorkey[4]);
			p[5] = (byte)(uid[3] ^ xorkey[5]);
		}
		for(int i = 0; i<p.length; i++){
			key = key.concat(String.format("%02x", p[i]));
		}

		return key;
	}
		
	public static String[][] gen(String uid){
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
			byte[] uidbytes = hexToBytes(uid);
			keyGenA = calcKey(uidbytes, hexToBytes(xortable[i][1]), 'A');
			keyGenB = calcKey(uidbytes, hexToBytes(xortable[i][2]), 'B');
			keys[0][i+1] = keyGenA;
			keys[1][i+1] = keyGenB;
		}
		
		return keys;
	}
}
