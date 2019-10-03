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

public class MizipGenMultiTest{
	public static void main(String[] args){
		// Test GenAB class
		// Return is a two-dimensional array (0: A keys, 1: B keys)
		// example: keys[0][2] is the key A for sector 2
		
		System.out.println("MIZIP KEYS GENERATOR\n");
		System.out.print("Insert your Mifare tag UID: ");
		Scanner input = new Scanner(System.in);
		String uid = input.nextLine();
		char keyType;
		
		// Generate keys with the class
		String[][] keys = MizipGen.genAllKeys(uid);
		
		// Print results with a for cycle
		System.out.println("\n--- MiZip generated keys ---");
		System.out.println("UID: "+uid+"\n");
		
		for(int a=0; a<keys.length; a++){
			for(int b=0; b<keys[a].length; b++){
				if(a==0){
					keyType='A';
				}else{
					keyType='B';
				}
				System.out.println("Sector "+b+" Key "+keyType+": "+keys[a][b]);
			}
		}
	}
}
