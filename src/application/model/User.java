package application.model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Luis Valdes, Klay Teegardin
 *UTSA CS 3443
 * Spring 2019
 *this class requires 2 arguments to validate if the user's credentials match
 *the username, and the password
 */

public class User {
	private String name; 
	private String phoneNumber; 
	private String email; 
	private String username;
	private String password;
	private boolean signInFlag = false;
	private String data[]; 

	/**
	 * Creates a user with a username and password
	 * @param user
	 * @param pass
	 */
	public User(String user, String pass){

		this.username = user; 
		this.password = pass; 
		data = new String[14];
		for(int x = 0; x < data.length; x++){
			data[x] = " "; 
		}

	}

	/**
	 * Creates a more detailed username
	 * @param name
	 * @param user
	 * @param pass
	 * @param phoneNumber
	 * @param email
	 * @throws IOException
	 */
	public User(String name, String user, String pass, String phoneNumber, String email) throws IOException{

		this.name = name; 
		this.username = user;
		this.password = pass; 
		this.phoneNumber = phoneNumber; 
		this.email = email; 
		data = new String[14];		
	}

	/**
	 * Sets the dataArray (synonymous to setData)
	 * @param dataParam
	 */
	public void populateArray(String dataParam[]) {
		this.data = dataParam;

	}
	/**
	 * 
	 * @param User user
	 * @return user
	 * @throws IOException
	 * the validate method checks if what the user entered is in the system and returns true if they are, 
	 * and false if they are not
	 */

	public static User validate(User user)throws IOException {
		try{
			Scanner scan = new Scanner(new File("data/loginUPDATED.csv"));
			while(scan.hasNextLine()) {
				String line = scan.nextLine();

				if(!line.equals("")) {
					String[] userSplit = line.split(",");
					if(userSplit[1].equals(user.getUsername()) && userSplit[2].equals(user.getPassword())){
						user.populate(userSplit[0], userSplit[4], userSplit[3]);
						scan.close();
						return user;						
					}
				}
			}
			scan.close();
			return null;

		}catch( IOException e ) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * populates this name, phonenumber, and email.
	 * Good for turning users with only user and password to a full user
	 * @param name
	 * @param phoneNumber
	 * @param email
	 */
	public void populate(String name, String phoneNumber, String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	/**
	 * populates the data array with userInfo.csv information
	 * @param fullName
	 * @return
	 */
	public String[] populateData(String fullName) {

		String [] getData = new String[14];
		try{
			Scanner scan = new Scanner(new File("data/userInfo.csv"));
			while(scan.hasNextLine()) {
				String line = scan.nextLine();
				String [] tokens = line.split(",");

				if(tokens[0].toLowerCase().equals( fullName.toLowerCase() )) {
					for(int i = 0; i < 14; i++) {
						int j = i+1;
						getData[i] = tokens[j];
					}
				}
			}

			scan.close();

		}catch( IOException e ) {
			e.printStackTrace();
		}

		return getData;

	}


	/**
	 * Gets the username
	 * @return
	 */
	public String getUsername(){
		return username; 
	}

	/**
	 * gets the password
	 * @return
	 */
	public String getPassword(){
		return password; 
	}
	
	/**
	 * Sets the name
	 * @param n
	 */
	public void setName(String n){
		this.name = n; 
	}

	/**
	 * Sets the email
	 * @param e
	 */
	public void setEmail(String e){ 
		this.email = e; 
	}
	
	/**
	 * Sets the username
	 * @param u
	 */
	public void setUsername(String u){
		this.username = u; 
	}

	/**
	 * Sets the password
	 * @param p
	 */
	public void setPassword(String p){
		this.password= p; 
	}

	/**
	 * Gets the phone number
	 * @return
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets the phone number
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the signInflag
	 * @return
	 */
	public boolean isSignInFlag() {
		return signInFlag;
	}

	/**
	 * Sets the signInFlag
	 * @param signInFlag
	 */
	public void setSignInFlag(boolean signInFlag) {
		this.signInFlag = signInFlag;
	}

	/**
	 * Gets the data
	 * @return
	 */
	public String[] getData() {
		return data;
	}

	/**
	 * Sets the data
	 * @param data
	 */
	public void setData(String[] data) {
		this.data = data;
	}
	
	/**
	 * Gets the name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the email
	 * @return
	 */
	public String getEmail() {
		return email;
	}



}