package application.controller;

import java.util.Scanner;
import javafx.scene.control.Label;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import application.Main;
import application.model.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
/**|
 * UTSA CS 3443
 * Spring 2019 
 * @author Dylan Mall, Klay Teegardin
 *
 */
public class SignUpController implements EventHandler<ActionEvent>{
	@FXML
	private TextField passField;
	@FXML
	private TextField userField;
	@FXML
	private TextField confirmPassField;
	@FXML
	private TextField nameField;
	@FXML
	private TextField emailField;
	@FXML
	private TextField phoneField;
	@FXML
	private Label passNotMatch;
	@FXML 
	private Label emailInUse;
	@FXML 
	private Label userInUse;


	/**
	 * Handles when the create account button is pushed
	 * Validates input , making sure that the username is not already taken
	 * Makes sure email is unique
	 * Appends the new user info to the loginUPDATED.csv file
	 */
	@Override
	public void handle(ActionEvent arg0) {

		try {
			if(passField.getText().equals(confirmPassField.getText())) {
				passNotMatch.setText("");
				userInUse.setText("");
				emailInUse.setText("");
				// Make sure username or email are not in use already.
				// open the file for reading
				Scanner scan = new Scanner ( new File("data/loginUPDATED.csv") );
				int errors = 0;

				while( scan.hasNextLine() ) {
					String line = scan.nextLine();
					String[] tokens = line.split(",");
					if(tokens[1].equals(userField.getText())){
						userInUse.setText("Username taken.");
						errors++;
					}
					if(tokens[3].equals(emailField.getText())){
						emailInUse.setText("Email already in use.");
						errors++;
					}
				}
				scan.close();
				if(errors == 0) {
					FileWriter writer = new FileWriter("data/loginUPDATED.csv",true);
					String str = nameField.getText() + "," + userField.getText() + "," + passField.getText() + "," + emailField.getText() + "," + phoneField.getText() + "\n"  ;
					LoginController.enteredUser = new User( nameField.getText(),userField.getText(),passField.getText(), phoneField.getText(), emailField.getText());
					writer.write(str);
					writer.close();

					// Goes to the Profile page.
					Parent root = FXMLLoader.load(getClass().getResource("../view/Profile.fxml"));
					Main.stage.setScene(new Scene(root, 800, 800));
					Main.stage.show();
				}

			} else {
				passNotMatch.setText("Passwords do not match. Please try again.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handles when the goBack button is pushed.
	 * Sends the user back to Login.fxml
	 * @param event
	 */
	public void goBack(ActionEvent event){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
