package ifrn.arch.project.main;

public class CPU {

	int PC;
	String IR;
	String OPCode;
	String[] handler;
	int MAR;
	int MBR;
	int[] R1;
	
	CPU(){
		PC = 0;
		IR = null;
		OPCode = null;
		handler = null;
		MAR = 0;
		MBR = 0;
		R1 = new int[32];
		R1[1] = 0;
	}
	
	public int getPC() {
		return PC;
	}
	public String getIR() {
		return IR;
	}
	public String getOPCode() {
		return OPCode;
	}
	public int getMAR() {
		return MAR;
	}
	public int getMBR() {
		return MBR;
	}
	public int getR1(int index) {
		if(index >= 0 && index < 32)
			return R1[index];
		else
			return -1;
	}
	public void setPC(int value){
		PC = value;
	}
	public void setIR(String value) {
		IR = value;
	}
	public void setOPCode(String value) {
		OPCode = value;
	}
	public void setMAR(int value) {
		MAR = value;
	}
	public void setMBR(int value){
		MBR = value;
	}
	public void execute(Memory memory){
		do {
			IR = memory.pull(PC);
			System.out.println( "memoria(PC) --> IR: " + IR);
			if(IR.equals("FINISHED"))
				break;
			MAR = PC;
			System.out.println("PC --> MAR: " + MAR);
			PC++;
			System.out.println("PC++: " + PC);
			handler = IR.split(" ");
			MBR = Integer.parseInt(handler[4]);
			System.out.println("memoria(MAR) --> MBR: " + MBR);
			OPCode = handler[0];
			
			System.out.println("OPCode = " + OPCode);
			
			if(MBR == 0){
				switch (OPCode) {
				case "ADD":
					R1[Integer.parseInt(handler[1])] = R1[Integer.parseInt(handler[2])] + R1[Integer.parseInt(handler[3])];
					System.out.println("R1["+handler[2]+"] = " + R1[Integer.parseInt(handler[2])]);
					System.out.println("R1["+handler[3]+"] = " + R1[Integer.parseInt(handler[3])]);
					System.out.println("R1["+handler[1]+"] <-- " + R1[Integer.parseInt(handler[1])]);
					break;
				case "SUB":
					R1[Integer.parseInt(handler[1])] = R1[Integer.parseInt(handler[2])] - R1[Integer.parseInt(handler[3])];
					System.out.println("R1["+handler[2]+"] = " + R1[Integer.parseInt(handler[2])]);
					System.out.println("R1["+handler[3]+"] = " + R1[Integer.parseInt(handler[3])]);
					System.out.println("R1["+handler[1]+"] <-- " + R1[Integer.parseInt(handler[1])]);
					break;
				default:
					break;
				}
			}else {
				switch (OPCode) {
				case "ADD":
					R1[Integer.parseInt(handler[1])] = R1[Integer.parseInt(handler[2])] + MBR;
					System.out.println("R1["+handler[2]+"] = " + R1[Integer.parseInt(handler[2])]);
					System.out.println("MBR = " + MBR);
					System.out.println("R1["+handler[1]+"] <-- " + R1[Integer.parseInt(handler[1])]);
					break;
				case "SUB":
					R1[Integer.parseInt(handler[1])] = R1[Integer.parseInt(handler[2])] - MBR;
					System.out.println("R1["+handler[2]+"] = " + R1[Integer.parseInt(handler[2])]);
					System.out.println("MBR = " + MBR);
					System.out.println("R1["+handler[1]+"] <-- " + R1[Integer.parseInt(handler[1])]);
					break;
				case "JUMP":
					PC += MBR;
					System.out.println("PC <--" + PC);
					break;
				case "JUMPEQ":
					if (R1[Integer.parseInt(handler[2])] == R1[Integer.parseInt(handler[3])]) {
						PC += MBR;
						System.out.println("R1["+handler[2]+"] = " + R1[Integer.parseInt(handler[2])]);
						System.out.println("R1["+handler[3]+"] = " + R1[Integer.parseInt(handler[3])]);
						System.out.println("PC <--" + PC);
						
					}else {
						System.out.println("R1["+handler[2]+"] = " + R1[Integer.parseInt(handler[2])]);
						System.out.println("R1["+handler[3]+"] = " + R1[Integer.parseInt(handler[3])]);
					}
					break;
				default:
					break;
				}
			}
			System.out.println("");
		}while(!IR.equals("FINISHED")); 
	}
}

