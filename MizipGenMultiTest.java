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

import java.util.Scanner;

public class MiZipGenMultiTest{
	public static void main(String[] args){
		// Test GenAB class
		// Return is a two-dimensional array (0: A keys, 1: B keys)
		// example: keys[0][2] is the key A for sector 2
		
		System.out.println("MIZIP KEYS GENERATOR\n");
		System.out.print("Insert your Mifare tag UID: ");
		Scanner input = new Scanner(System.in);
		String uid = input.nextLine();
		
		// Generate keys with the class
		String[][] keys = MiZipGen.genAllKeys(uid);
		
		// Print results
		System.out.println("\n--- MiZip generated keys ---");
		System.out.println("UID: " + uid + "\n");
		
		for(int sector = 0; sector < keys.length; sector++){
			for(int key = 0; key < keys[sector].length; key++){
				char keyType = (key == 0) ? 'A' : 'B';
				System.out.println("Sector: " + sector + "| Key " + keyType + ": "+keys[sector][key]);
			}
		}
	}
}
