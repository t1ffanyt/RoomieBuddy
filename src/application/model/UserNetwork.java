package application.model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import application.controller.LoginController;
/**
 * 
 * @author Tiffany Tabourne, Anna Arroyo, Klay Teegardin, Luis Valdes
 *
 */
public class UserNetwork {

	private ArrayList<User> users; 
	private ArrayList<String> matchedusernames; 

	public UserNetwork(){ 


		users = new ArrayList<User>(); 
		matchedusernames = new ArrayList<String>(); 
	}
	
	public HashMap<String,String> getMapDifferences(User use, ArrayList<User> matched){ 
		
		HashMap<String,String> diffUsers = new HashMap<String,String>(); 
		String[] userQualities; 
		userQualities = use.getData(); 
		String[] matchedQualities; 
		for(int x = 0; x < matched.size(); x++){	
			matchedQualities = matched.get(x).getData();
			diffUsers.put(matched.get(x).getName(), "" );
			String append = "Contact Information:  " + matched.get(x).getPhoneNumber() + "  " + matched.get(x).getEmail() + "\n" + "Differences: \n"; 
			int y;
			for(y = 2; y < matchedQualities.length - 1; y++ ){
				if(!(userQualities[y].equals(matchedQualities[y]))){
					append+=matchedQualities[y] + "\n";	
				}
			}
			append += "\nBio: " + matchedQualities[y];
			diffUsers.put(matched.get(x).getName(),append );
		}

		return diffUsers;
				
	}


	public ArrayList<User> getMatches(User currUser){
		
		String classification = currUser.getData()[0];
		String major = currUser.getData()[1];
		String clean = currUser.getData()[2];
		String party = currUser.getData()[3];
		String music = currUser.getData()[4];
		String film = currUser.getData()[5];
		String gaming = currUser.getData()[6];
		String sports = currUser.getData()[7];
		String hiking = currUser.getData()[8];
		String reading = currUser.getData()[9];
		String gender = currUser.getData()[10];
		String university = currUser.getData()[11];
		String apartment = currUser.getData()[12];

		ArrayList<User> matchedUsers = new ArrayList<User>();
		
		int threshold = 8;
		int count;
		
		try {
			Scanner scan = new Scanner ( new File("data/userInfo.csv") );

			while( scan.hasNextLine() ) {
				count = 0;
				String line = scan.nextLine();
				String[] tokens = line.split(",");
				if(!tokens[0].toLowerCase().equals(currUser.getName().toLowerCase())) {
					

					if(tokens[1].equalsIgnoreCase(classification)){
						count++;
					}
					if(tokens[2].equals(major)){
						count++;
					}
					if(tokens[3].equals(clean)){
						count++;
					}
					if(tokens[4].equals(party)){
						count++;
					}
					if(tokens[5].equals(music)){
						count++;
					}
					if(tokens[6].equals(film)){
						count++;
					}
					if(tokens[7].equals(gaming)){
						count++;
					}
					if(tokens[8].equals(sports)){
						count++;
					}
					if(tokens[9].equals(hiking)){
						count++;
					}
					if(tokens[10].equals(reading)){
						count++;
					}
					if(tokens[11].equals(gender)){
						count++;
					}
					if(tokens[12].equals(university)){
						count++;
					}
					if(tokens[13].equals(apartment)){
						count++;
					}
				
					if(count >= threshold) {
						User user = findUserByName( tokens[0] );
						matchedUsers.add(user);
					}
				}
			}
			scan.close();

		}catch(IOException e) {
			e.printStackTrace();
		}
		return matchedUsers;
	}
	

	public User findUserByName(String name) {
		User found = null;
		
		for(int i = 0; i < this.users.size(); i++)
		{
			if(this.users.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
				found = users.get(i);
			}
		}
		return found;
		
	}
	public void save() throws IOException{ 
		FileWriter f = new FileWriter("data/userInfo.csv", false);
		
		String s = "";  
		for( int x = 0; x < LoginController.userNetwork.getUsers().size(); x++)
		{
			s += users.get(x).getName()+ ",";
			String[] data = users.get(x).getData();			
			for(int y = 0; y < data.length; y++){
				if(y+1!=data.length)
					s += data[y] + "," ;
				else {
					s+=data[y];
				}
			}
			s += "\n"; 
				
			
		}
		
		f.write(s);
		f.close();
	}
	
	
	 
	
	public void loadUsers(String file) throws IOException{ 
		File f = new File(file); 
		Scanner scan = new Scanner(f); 

		while(scan.hasNextLine()){
			String line = scan.nextLine(); 
			String[] tokens;
			tokens = line.split(",");
			
			String fullName = tokens[0];
			String username = tokens[1];
			String password = tokens[2];
			String phone = tokens[3];
			String email = tokens[4];
			
			User user = new User( fullName, username, password, phone, email );
		
			String[] data = user.populateData(fullName);
			user.setData(data);
			
			
			LoginController.userNetwork.getUsers().add( user );
	
		}
		scan.close();
	}
	

	

	public ArrayList<User> getUsers() {
		return users;
	}


	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}


	public ArrayList<String> getMatchedusernames() {
		return matchedusernames;
	}


	public void setMatchedusernames(ArrayList<String> matchedusernames) {
		this.matchedusernames = matchedusernames;
	}

	public int getExistingUser(String user, String pass){
		for(int x = 0; x < users.size(); x++){
			if((users.get(x).getUsername()).equals(user) && (users.get(x).getPassword()).equals(pass)){
				return x; 
			}
		}

		return -1; 
	}

	public void updateExistingUser(int index,String[] updated){

		users.get(index).setData(updated); 


	}




}
