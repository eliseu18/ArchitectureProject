package ifrn.arch.project.main;

public class Memory {

	int count;
	int size;
	String[] memory;
	
	public Memory(){
		memory = new String[32];
		count = 0;
		size = 32;
	}
	
	public boolean push(String value) {
		if(count < 32) {
			memory[count] = value;
			count++;
			return true;
		}else
			return false;
	}
	
	public boolean push(String value, int index){
		if(index < 32 || index >= 0 ) {
			memory[index] = value;
			return true;
		}
		else
			return false;
	}
	
	public String pull(int index){
		if(index < 32 || index >= 0 )
			return memory[index];
		else
			return null;
	}
}
