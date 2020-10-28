package backend;
//Charecter class stores all information regarding charecters
public class Char {
	//Properties every charecter by default has
	private String name = "Unnasigned"; //their name
	private int charid, lineNumber; //thir charecter id and linenumber they are found on in the textfile
	Dynasty dynasty; //their dynasty object
	CharInfo[] charinfo; //An array of charinfo objects
	
//Charecter class 
	Char(String charid, int lineNum) { //will attempt to set their charid and linenumber	
		try {
			this.charid = Integer.parseInt(charid.replace("=", "").trim());
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		this.lineNumber = lineNum;	
		//charinfo[0] = new CharInfo("CharId=\" " + charid + "\"");
	}
	

	//getter and setter methods
	public void setName(String name) {
		this.name = name.trim().replace("bn=", "").replace("\"", ""); //removes other chars
	}
		
	public void setDynasty(Dynasty dynasty) {
		if(dynasty != null) this.dynasty = dynasty;	
	}
	
	public String getName() {
		return name;
	}
		
	public int getId() {
		return charid;
	}
	
	public int getLineNumber() {
		return lineNumber;
	}
	
	public Dynasty getDynasty() {
		//System.out.println(dynasty);
		return dynasty;
	}
	
	public void addCharInfo(CharInfo[] charInfo) {
		charinfo = charInfo;
	}
	
	public CharInfo[] getCharInfo() {
		return charinfo;
	}
	
}
