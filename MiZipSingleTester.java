/*	
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Scanner;

/**
 * Tester for the MiZipGen class
 * Prints a generated key
 */
public class MiZipSingleTester{
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		
		// get input parameters
		System.out.println("MIZIP SINGLE KEY GENERATOR\n");
		System.out.print("Insert your Mifare tag UID: ");
		String uid = input.nextLine();
		
		System.out.print("Insert your sector type (A or B): ");
		char keyType = input.nextLine().charAt(0);
		
		System.out.print("Insert your sector number (0 to 4): ");
		int sectorNumber = input.nextInt();
		
		try {
			// print generated key
			System.out.println("\nGenerated Key: " + MiZipGen.genKey(uid, keyType, sectorNumber));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
