package hw3;

import java.util.ArrayList;
import java.util.Scanner;

import api.Instruction;
import api.NVPair;
import api.SymbolTable;

/*
 * Assembler for assembly language programs targeting the CS227Comp machine.
 * See the associated pdf for a detailed overview.
 * @author Bryanna Adamson
 */
public class CS227Asm {

	private ArrayList<String> programList; // turns program into an array list
	private SymbolTable data; // creates variable for data
	private SymbolTable label; // creates variable for data
	private Instruction instruction; // creates an instruction variable
	private ArrayList<Instruction> instrStream;	// creates a list of instructions
	private int operand; // variable for setting operand values
	private int IC; //instruction count
	/*
	 * Constructs an assembler for the given assembly language program, given as an ArrayList of strings (one per line of the program).
	 * Initially both symbol tables and the instruction stream are empty.
	 * @param program - the assembly language program to be translated
	 */
	public CS227Asm(java.util.ArrayList<java.lang.String> program) {
		programList = program;
		data = new SymbolTable();
		label = new SymbolTable();
		instrStream = new ArrayList<Instruction>();
		operand = 0;
		IC = 0;




	}

	/*
	 * For each instruction in the instruction stream that is a jump target, adds the label to the instruction's description.
	 * (See the method addLabelToDescription in the Instruction class.)
	 */
	public void addLabelAnnotations() {
		/*String s;
		Scanner scan = new Scanner(s);
		for(int i = 0; i < instrStream.size(); i++) {
			if(instrStream.get(i).requiresJumpTarget() == true) {
				 //i needs to getLabel() by using i as the Value
				instruction.addLabelToDescription();
			}
		}
	*/
	}

	/*
	 * Assembles the source program represented by this assembler instance and returns the generated machine code and
	 * data as an array list of strings.
	 * @return
	 * list of strings containing the machine code and data for the program represented by this assembler
	 */
	public java.util.ArrayList<java.lang.String> assemble(){
		parseData();
		parseLabels();
		parseInstructions();
		setOperandValues();
		addLabelAnnotations();
		return writeCode();
	}

	/*
	 * Returns the symbol table for data (variables).
	 * @return the symbol table for data
	 */
	public SymbolTable getData(){

		return data;
	}

	/*
	 * Returns the instruction stream. The index of each instruction in the list is its
	 * memory location in the generated code.
	 * @return list of machine instructions
	 */
	public java.util.ArrayList<Instruction> getInstructionStream(){

		return instrStream;
	}

	/*
	 * Returns the symbol table for labels (jump targets).
	 * @return the symbol table for labels
	 */
	public SymbolTable getLabels() {

		return label;
	}

	/*
	 * Creates the symbol table for the data section of this assembler's program.
	 */
	public void parseData() {
		for(int i =1; i < programList.size() ;i++) {
			String s = programList.get(i);
			Scanner scan = new Scanner(s);
			if(s != "labels:") {
				String n = scan.next();
				int v = scan.nextInt();
				data.add(n,v);
				}
			else
				break;
			}
		}

	/*
	 * Creates the symbol table for the label section of this assembler's program, leaving all label values as zeros.
	 */
	public void parseLabels() {
		for(int i =0; i < programList.size() ;i++) {
			String s = programList.get(i);
			Scanner scan = new Scanner(s);
			if(s.equals("labels:")) {
				for(int j = i+1; j < programList.size(); j++) {
					String t = programList.get(j);
					Scanner scan1 = new Scanner(t);
					if(!t.equals("instructions:"))  {
						String n = scan1.next();
						label.add(n);
					}
					else
						break;
				}
			}
		}
	}

	/*
	 * Creates instruction stream from the instruction section of this assembler's program and fills in label
	 * addresses in the symbol table for labels.
	 */
	public void parseInstructions() {
		for(int i =0; i < programList.size() ;i++) {
			String s = programList.get(i);
			if(s.equals("instructions:")) {
				for(int j = i+1; j<programList.size(); j++) {
					String t = programList.get(j);
					Scanner scan1 = new Scanner(t);
					String instr = scan1.next();
					int address = IC;
					if(label.containsName(instr)==true) {
						label.findByName(instr).setValue(address);
						//IC--;
					}
					else {
						IC++;
						instruction = new Instruction(t);

						//instruction.add(new Instruction(t);
						instrStream.add(new Instruction(t));
					}
				}
			}
		}
	}

	/*
	 * Fills in the correct operand value for all instructions (either a data address or jump target address,
	 *  depending on the instruction).
	 */
	public void setOperandValues() {


		int streamSize = instrStream.size();
		for(int i = 0; i < streamSize; i++) {
			String opString = instruction.getOperandString();
			if(instruction.requiresJumpTarget() == true) {
				if(label.containsName(opString))
					operand = label.findByName(opString).getValue();
			}
			else {
				if(instruction.requiresDataAddress() == true) {
					if(data.containsName(opString))
						operand = data.findByName(opString).getValue() + instrStream.size();
				}
			}
		}
	}

	/*
	 * Generates the machine code and data for this assembler's program, terminated by the string "-99999". Strings for
	 * instructions are produced by the Instruction method toString, and strings for data have the value formatted as a
	 * four-digit signed integer, followed by a space, followed by the variable name.
	 * @return list of strings containing the machine code and data for this assembler's program
	 */
	public java.util.ArrayList<java.lang.String> writeCode(){
		ArrayList<String> instructionList = new ArrayList<String>();
		for(int i = 0;i < instrStream.size(); i++) {
			 instructionList.add(instruction.toString());
		}
		return instructionList;
	}
}
