package backend;

public class Dynasty {
	private int lineNumber, id;
	private String name = "Historical Dynasty";
	
	//A class for each dynasty found in initial parse
	Dynasty(int id, int lineNumber, boolean isLowborn) {
		this.id = id;
		this.lineNumber = lineNumber;
		
		if(isLowborn) {
			id = 0;
			lineNumber = 0;
			name = "Lowborn";
		}
	}
	
	public int getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		//System.out.println(name);
		return name;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	
	
	
}
