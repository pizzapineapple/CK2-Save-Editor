package backend;
//A generic class to store random info associated with a charecter
public class CharInfo {
	String[][] strings;
	
	String name;
	String[] value;
	
	//This is  intented to be able to embed charinfo inside other charinfo
	CharInfo(String[] strings) {
		this.value = strings;
	}
	
	//this is the main constructor being called
	//it takes a single string or line as input
	CharInfo(String string) {
		this.strings = new String[1][1];
		this.strings[0][0] = string;
		this.value = new String[1];
		
		try {
			this.name = string.substring(0, string.indexOf("=")).trim().replace("\"", ""); //parses the string splitting it from the first index to the first =	
			this.value[0] = string.substring(string.indexOf("=")).trim().replace("\"", "").replace("=", ""); //split from 1st ='s to the end of the string
			System.out.println(name + " "+ value[0]);
		} catch(StringIndexOutOfBoundsException e) { //if the line is weird sometimes it wont have an equals this is to catch that
			//e.printStackTrace();
			name = "";
			value[0] = string;
		}
		
		
	}
	
	//getter and setter methods
	public String getName() {
		return name;
	}
	
	public String getValue() {
		//System.out.println(value[0]);
		return value[0];
	}
	
	public void setValue(String input) {
		this.value[0] = input; 
	}
	
	
	
	
	
}
