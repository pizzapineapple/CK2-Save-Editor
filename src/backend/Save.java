package backend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * A class to store the modifications made to each scene
 * eventually written to a new text file when write() is called
 */
public class Save {
	//does everything related to the save files
	public BufferedReader br;
	public FileReader fileLocation;
	public String saveLocation = "";
	public ArrayList<Char> charArray = new ArrayList<Char>();
	public ArrayList<Dynasty> dynastyArray = new ArrayList<Dynasty>();

	//creates a save object to contain everything to do with a specific instance of modifing a save file
	public Save(String location) { 
		this.saveLocation = location; //is passes the location as a string
				
		try {
			fileLocation = new FileReader(saveLocation); //creates a new file reader object with the save location
			br = new BufferedReader(fileLocation); //creates a new bufferedReader object using the filereader object created above
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("fnf exception"); 
		}
		
		try {
			System.out.println("calls init parse");
			this.initialParse(); //calls initial parse to get basic info about the text file
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("throws io exp");
		}

	}

	//called by the construcor when the save object is initially created
	private void initialParse() throws IOException {
		int lineNum = -1; //denotes what line number it is on initied to -1 for reasons 
		String line = ""; //what is the text of the current line
		String currentSection = "Nothing"; //What section of the file it is in
		int bracketState = 0; //a way to determine if when the line is inside a block of curley brackets
		//its value denotes how many curley brackets its insde
		ArrayList<Declaration> declarationList = new ArrayList<Declaration>(10); //Creates an array of declaration objects to store when each declaration is in the fike

		//goes until it reaches the end of the text file
		do {
			lineNum++;
			
			//updates the bracketstate
			if(line.contains("{")) bracketState++;
			if(line.contains("}")) bracketState--;

			if(bracketState == 0) currentSection = "Nothing"; //if the line is not encased in any brackets it is not inside any section of the file
		
			if(line.equals("\tcharacter=")) { //its in the charecter section
				currentSection = "Chars"; //System.out.println("Char decl" + lineNum);
				declarationList.add(new Declaration(lineNum, "Chars"));
			}
			if(line.equals("\tdynasties=")) { //in dynasty section
				currentSection = "Dynasties";
				declarationList.add(new Declaration(lineNum, "Dynasties"));
			}
			if(line.equals("\treligion=")) { //in the religion section
				currentSection = "Religion";
				declarationList.add(new Declaration(lineNum, "Religion"));
			}
			if(line.equals("\tprovinces=")) { //in the province section
				currentSection = "Titles";
				declarationList.add(new Declaration(lineNum, "Provinces"));
				System.out.println(lineNum);
			}
			
			
			//In the dynasties section
			if(currentSection == "Dynasties" && line.contains("=") && bracketState == 1) {
				Dynasty dynasty = new Dynasty(Integer.parseInt(line.replaceAll("\t", "").replaceAll("\r", "").replaceAll("=", "")), lineNum, false);
				int conditions = 0;
				do { //executes this loop while its inside the dynasty seciton
					//loop executes to look for new dynasties, if it finds them it adds a new dynasty object to the arraylist
					line = br.readLine();
					//System.out.println(line);
					lineNum++;

					if(line.contains("{")) bracketState++;
					if(line.contains("}")) bracketState--;

					if(line.contains("name=\"") ) {
						//System.out.println("found line");
						dynasty.setName(line.trim().replaceAll("\"", "").replace("name=", ""));
						//System.out.println(dynasty.getName());
						conditions++;
					}
					
					//System.out.println("Executed");
				} while(bracketState > 1 || conditions == 2);
				
				while(bracketState < 1) {
					String Line = br.readLine();
					lineNum++;
					if(Line.contains("{")) bracketState++;
					if(Line.contains("}")) bracketState--;
					System.out.println(line);
			}
				dynastyArray.add(dynasty);
			}


			//In the section for characters
			if(currentSection == "Chars" && line.contains("=") && bracketState == 1) {
				Char Char = new Char(line, lineNum);
				int conditions = 0;
				
				do { //keeps reading lines until it gets what it needs when conditons = 2 or if has exhaused all the charecters lines
						line = br.readLine();
						//System.out.println(line);
						lineNum++;
						//System.out.println(lineNum);
						if(line.contains("{")) bracketState++;
						if(line.contains("}")) bracketState--;

						if(line.contains("bn") ) {
							Char.setName(line);
							conditions++;
						}
						if(line.contains("dnt=")) {
							//Associates all chars dynasty lines with their respective dynasty object
							Char.setDynasty(findDynasty(Integer.parseInt(line.trim().replaceAll("=", "").replace("dnt", "")) ) );
							conditions++;
						}
						if(conditions == 2) break; //if it has everything it needs then break

				} while(bracketState > 1);

				//when the charecter object has found everything it needs it reads lines until 
				while(bracketState > 1) {
						//System.out.println("easy out");
						String Line = br.readLine();
						lineNum++;
						if(Line.contains("{")) bracketState++;
						if(Line.contains("}")) bracketState--;
				}
				charArray.add(Char);

			}

			line = br.readLine();

		} while(line != null);
			br.close();
	}


	//for testing delete
	int findChar(int id) {
		for(int i = 0; i < charArray.size()-1; i++) {
			if(charArray.get(i).getId() == id) return charArray.get(i).getLineNumber();
		}
		return 0;
	}

	//find the dynasty object based on its id
	Dynasty findDynasty(int id) {
		//System.out.println(dynastyArray.size());
		for(int i = 0; i < dynastyArray.size()-1; i++) {
			if(id == dynastyArray.get(i).getId()) return dynastyArray.get(i);
		}
		return null;
	}

	//only returns one result
	public Char findCharbyId(int parseInt) {
		for(int i = 0; i < this.charArray.size()-1; i++) {
			if(parseInt == charArray.get(i).getId()) return this.charArray.get(i);		
		}
		return null;
	}

	//returns everything that contains the output string
	public Char[] findCharbyName(String delimitedInput) {
		// TODO Auto-generated method stub
		ArrayList<Char> returnChars = new ArrayList<Char>();
		for(int i = 0; i < this.charArray.size()-1; i++) {
			String comparedTo = this.charArray.get(i).getName().toLowerCase();
			if(comparedTo.contains(delimitedInput.toLowerCase())) returnChars.add(charArray.get(i));			
		}
		return returnChars.toArray(new Char[0]);
	}
	
	//Gets specific details about the character when the user has selcted to edit/view it 
	public void moreCharInfo(Char Char) throws IOException {
		//System.out.println("hellp");
		try {
			fileLocation = new FileReader(saveLocation);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		br = new BufferedReader(fileLocation);
			
		for(int i = 0; i < Char.getLineNumber(); i++) br.readLine(); //uselessy reads lines until it gets to where it needs to at which point usefull stuff can be recorded 
		String line = "";
		int bracketState = 1;
		ArrayList<CharInfo> charInfoStorage = new ArrayList<CharInfo>();
		
		
		do { //while it hasnt gone through the entire charecter
			line = br.readLine() + "\r\n";
			//System.out.println(line);
			if(line.contains("{")) bracketState++;
			if(line.contains("}")) bracketState--;
			
			//multiline declaration
			if(line.contains("=\r\n")) { //if it has internal brackets embedded
				ArrayList<String> stringArrayList = new ArrayList<String>();
				do {
					stringArrayList.add(line);
					
					line = br.readLine();
					if(line.contains("{")) bracketState++;
					if(line.contains("}")) bracketState--;
					
				}  while(bracketState > 2);
				charInfoStorage.add(new CharInfo(stringArrayList.toArray(new String[0]) ) );
				
			}
			
			else if(line.contains("=")) { // if it just a line setting something with an ='s thats just one chardeclaration
				charInfoStorage.add(new CharInfo(line) );
			}
			
		} while(bracketState > 1);
		charInfoStorage.add(new CharInfo("CharId=\"" + Char.getId())); //adds the 
		Char.addCharInfo(charInfoStorage.toArray(new CharInfo[0]) );  //adds the charinfo array to the charecter object
		
	}
	
	//Writes all changes to a new text file
	public void write(Char Chars) {
		String line = "notnull"; 
		FileWriter fw = null;
		
		try {
			fileLocation = new FileReader(saveLocation);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		br = new BufferedReader(fileLocation);
		
		
		try {
			fw = new FileWriter(System.getProperty("user.home") + "\\Desktop\\UpdatedSaveFile.txt"); //double slash to escape escape charecter
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			//writes all lines up to the modified char 
			for(int i = 0; i < Chars.getLineNumber(); i++) {
				line = br.readLine();
				fw.write(line + "\r\n");
			}
			
			//writes the new modified char to the testfile
			for(int i = 0; i < Chars.getCharInfo().length; i++) {
				if(Chars.getCharInfo()[i].getName() != null ) fw.write("\t\t\t" + Chars.getCharInfo()[i].getName() + "=" );
				fw.write( "\"" + Chars.getCharInfo()[i].getValue() + "\"\r\n" );
				System.out.println(Chars.getCharInfo()[i].getValue());
			}
			
			System.out.println(Chars.getLineNumber());
			
			//writes everything after that to the end of the text file
			while(line != null) {
				line = br.readLine();
				if(line == null) break;
				fw.write(line + "\r\n");	
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}