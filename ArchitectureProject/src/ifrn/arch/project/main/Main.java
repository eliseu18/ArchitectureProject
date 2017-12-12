package ifrn.arch.project.main;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String input;
		Memory memory = new Memory();
		CPU cpu = new CPU();
		boolean outOfMemory = false;		
		do {
			input = sc.nextLine();
			outOfMemory = memory.push(input);
		}while(!outOfMemory || !input.equals("FINISHED"));
		
		System.out.println("");
		System.out.println("-----------------------------------");
		System.out.println("");
		
		if(outOfMemory)
			cpu.execute(memory);
		
		System.out.println(cpu.getR1(3));
	}
}
