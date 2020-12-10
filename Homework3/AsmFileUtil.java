package hw3;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;

import hw3.CS227Asm;

/*
	 * A collection of utility methods for working with files containing programs
	 * for the CS227Comp architecture.
	 * @author Bryanna Adamson
	 */

public class AsmFileUtil {

	/*
	 * Reads the given file and assembles the program, writing the machine code to a file.
	 * Descriptions are included only if the annotated parameter is true, otherwise each
	 * line in the output file includes just the first five characters (representing the
	 * integer value of the instruction). The name of the output file is the same as the
	 * name of the input file, with the file extension (portion after the last dot) removed
	 * and is replaced with ".mach227". For example, given the filename "test1.asm227", the
	 * output file would be named "test1.mach227".
	 * @param filename - name of the file to read
	 * @throws java.io.FileNotFoundException
	 */
	public static void assembleAndWriteFile(java.lang.String filename, boolean annotated)
													throws java.io.FileNotFoundException{
		File file = new File(filename);
		PrintWriter pw = new PrintWriter(filename);
		Scanner scan = new Scanner(f);
		CS227 asm = new CS227(filename);
		list = asm.assemble();
		while(scan.hasNextLine()) {
			if(annotated == true) { //include descriptions
				pw.println(list);
			}
			else {

			}
		}
	}

	/*
	 * Reads the given file and assembles the program, returning the machine code as
	 * an array of integer values (not including the sentinel value).
	 * @param filename - name of the file to read
	 * @return assembled machine code as an array of integer values
	 * @throws java.io.FileNotFoundException
	 */
	public static int[] createMemoryImageFromFile(java.lang.String filename)
            							throws java.io.FileNotFoundException{
		ArrayList<String> list = new ArrayList<String>();

		CS227 asm = new CS227(filename);
		list = asm.assemble();
		int[] machineCode = new int [filename.length()] ;

		Scanner scan = new Scanner((Readable) list);
		int index = 0;
		while(scan.hasNextLine()) {
			int i = scan.nextInt();
			machineCode[index] = i;
			i++;
		}
		return machineCode;
	}

	/*
	 * Reads the given file and assembles the program, returning the machine code as a
	 * list of strings (including descriptions).
	 * @param filename - name of the file to read
	 * @return assembled machine code as a list of strings
	 * @throws java.io.FileNotFoundException
	 */
	public static java.util.ArrayList<java.lang.String> assembleFromFile(java.lang.String filename)
           								throws java.io.FileNotFoundException{
		ArrayList<String> machineCode = new ArrayList<String>();
		File f = new File(filename);
		Scanner scan = new Scanner(f);
		while(scan.hasNextLine()) {
			machineCode.add(scan.nextLine());
		}

		//}
		return machineCode;
	}
}
