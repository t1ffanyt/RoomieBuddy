package application.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import application.Main;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Controller for the Profile.fxml
 * UTSA CS 3443
 * Spring 2019
 * @author Klay Teegardin, Luis Valdes, Anna Aroyo, Tiffany Tabourne
 *
 */
public class ProfileController implements Initializable, EventHandler<ActionEvent> {
	@FXML
	private RadioButton gaming;
	@FXML
	private RadioButton sports;
	@FXML
	private TextField university;
	@FXML
	private RadioButton yesParty;
	@FXML
	private RadioButton noParty;
	@FXML
	private RadioButton hiking;
	@FXML
	private RadioButton reading;
	@FXML
	private TextField bio;
	@FXML
	private RadioButton film;
	@FXML
	private TextField classification;
	@FXML
	private RadioButton music;
	@FXML
	private TextField major;
	@FXML
	private RadioButton veryClean;
	@FXML
	private RadioButton modClean;
	@FXML
	private RadioButton female;
	@FXML
	private RadioButton male;
	@FXML
	private TextField apartment;
	@FXML
	private RadioButton notClean;
	@FXML
	private Text error;
	User entUser; 
	String data[] = new String[14];
	
	/**
	 * handles when the user clicks the submit button.
	 * This method sets a data array and adds it to the userInfo.
	 * It also makes sure a user does not leave a category empty
	 */
	@Override
	public void handle(ActionEvent event) {
		data[0] = classification.getText();
		data[1] = major.getText();
		data[4] = "I'm not into music";
		data[5] = "I don't like film";
		data[6] = "I don't like gaming";
		data[7] = "I don't like sports";
		data[8] = "I don't like to hike";
		data[9] = "I don't like to read";
		if(veryClean.isSelected()) {
			data[2] = "I am very clean";
		}
		else if(modClean.isSelected()) {
			data[2] = "I am moderately clean";
		}
		else if(notClean.isSelected()) {
			data[2] = "I am not clean";
		}
		if(yesParty.isSelected())
			data[3] = "I like to party";
		else if(noParty.isSelected())
			data[3] = "I don't like to arty";
		//hobbies
		if(music.isSelected())
			data[4] = "I like music";
		if(film.isSelected())
			data[5] = "I like film";
		if(gaming.isSelected())
			data[6] = "I like gaming";
		if(sports.isSelected())
			data[7] = "I like sports";
		if(hiking.isSelected())
			data[8] = "I like hiking";
		if(reading.isSelected())
			data[9] = "I like reading";
		if(male.isSelected())
			data[10] = "m";
		else if(female.isSelected())
			data[10] = "f";
		data[11] = university.getText();
		data[12] = apartment.getText();
		data[13] = bio.getText();


		if( classification.getText().equals("") || major.getText().equals("") ||
				(veryClean.isSelected()==false && modClean.isSelected()==false &&
				notClean.isSelected()==false ) || (noParty.isSelected()==false &&
				yesParty.isSelected()==false)||(music.isSelected()==false &&
				film.isSelected()==false && gaming.isSelected()==false &&
				sports.isSelected()==false && hiking.isSelected() == false &&
				sports.isSelected()==false && hiking.isSelected()==false &&
				reading.isSelected()==false) || (male.isSelected()==false &&
				female.isSelected()==false) || university.getText().equals("") ||
				apartment.getText().equals("") || bio.getText().equals("") ) 
		{
			error.setText("Please fill in all fields!"); 
		}
		else {
			//Makes sure the user is not already in the userList
			if(LoginController.userNetwork.getExistingUser(LoginController.enteredUser.getUsername(), LoginController.enteredUser.getPassword()) == -1){						

				LoginController.userNetwork.getUsers().add(LoginController.enteredUser);
			}

			//update the existing user's info
			LoginController.userNetwork.updateExistingUser(LoginController.userNetwork.getExistingUser(LoginController.enteredUser.getUsername(), LoginController.enteredUser.getPassword()),data);

			try {
				LoginController.userNetwork.save();
				Parent root;
				if(LoginController.enteredUser.isSignInFlag()) {
					root = FXMLLoader.load(getClass().getResource("../view/Pick.fxml"));
					LoginController.enteredUser.setData(LoginController.enteredUser.populateData(LoginController.enteredUser.getName()));
					
				}
				else{
					root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
				}
				Main.stage.setScene(new Scene(root, 800, 800));
				Main.stage.show();	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * Handles when the logout button is pushed
	 * Returns the user to the login screen
	 * @param event
	 */
	public void logout(ActionEvent event) {
		try {

			Parent root;
			root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * handles when the go back button is pushed
	 * Checks the signinflag to determine whether
	 * @param event
	 */
	public void goBack(ActionEvent event) {
		try {
			Parent root;
			if(LoginController.enteredUser.isSignInFlag()) {
				root = FXMLLoader.load(getClass().getResource("../view/Pick.fxml"));
			}
			else {
				root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			}
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * Initializes the Profile.fxml
	 */
	public void initialize(URL arg0, ResourceBundle arg1) { 
		try {
			autoPopulate();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Automatically populates the fields with your data if it can find it
	 * @throws FileNotFoundException
	 */
	public void autoPopulate() throws FileNotFoundException {
		Scanner scan = new Scanner(new File("data/userInfo.csv"));
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String tokens[] = line.split(",");
			if(tokens[0].equals(LoginController.enteredUser.getName())) {
				classification.setText(tokens[1]);
				major.setText(tokens[2]);
				if(tokens[3].equals("I am very clean"))
					veryClean.setSelected(true);
				else if(tokens[3].equals("I am moderately clean"))
					modClean.setSelected(true);
				else if(tokens[3].equals("I am not clean"))
					notClean.setSelected(true);

				//default set to false by default
				noParty.setSelected(true);
				female.setSelected(true);
				if(tokens[4].equals("I like to party"))
					yesParty.setSelected(true);
				if(tokens[5].equals("I like music"))
					music.setSelected(true);
				if(tokens[6].equals("I like film"))
					film.setSelected(true);
				if(tokens[7].equals("I like gaming"))
					gaming.setSelected(true);
				if(tokens[8].equals("I like sports"))
					sports.setSelected(true);
				if(tokens[9].equals("I like hiking"))
					hiking.setSelected(true);
				if(tokens[10].equals("I like reading"))
					reading.setSelected(true);
				if(tokens[11].equals("m"))
					male.setSelected(true);
				university.setText(tokens[12]);
				apartment.setText(tokens[13]);
				bio.setText(tokens[14]);
				scan.close();
				break;
			}
		}
		scan.close();
	}

}
