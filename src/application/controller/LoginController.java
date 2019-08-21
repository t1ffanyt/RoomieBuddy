package application.controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.Main;
import application.model.User;
import application.model.UserNetwork;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
/**
 * @author Luis Valdes (qbw322)
 * UTSA CS 3443
 * Spring 2019
 * Login controller handles the login credentials
 */
public class LoginController implements Initializable, EventHandler<ActionEvent> {
	public static final String foo = null;
	
	public static User enteredUser = new User(" " , " "); 
	public static User logoutUser = new User(" " , " "); 
	
	public static UserNetwork userNetwork = new UserNetwork();
	@FXML
	private TextField userText;
	@FXML
	private TextField passText;  
	@FXML
	private Text errorMsg;
	
	ArrayList<User> users = new ArrayList<User>(); 
	
	@Override
	/**
	 * get the user's username and password, then pass that into the validate method inside User.java 
	 * and check if it exists. Next read in the crew members that belong to same ship register as the user
	 * and pass them in to the ship through the second controller's texts and then load the Personnel.fxml
	 */
	public void handle( ActionEvent event) {

		try {
			
			String username = userText.getText();
			String password = passText.getText();
		
			enteredUser.setUsername(username);
			enteredUser.setPassword(password);
			
			
			
				User returnedUser = User.validate(enteredUser);				
				if(returnedUser==null) {
 
					errorMsg.setText("Invalid username or password, please enter valid credentials");
				}
				else {
					try {
						Parent root;
						root = FXMLLoader.load(getClass().getResource("../view/Pick.fxml"));
						enteredUser=returnedUser;
						enteredUser.setData(enteredUser.populateData(enteredUser.getName()));
						enteredUser.setSignInFlag(true);
						Main.stage.setScene(new Scene(root, 800, 800));
						Main.stage.show();	
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				
					    
			
				
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * handles when the signup button is pushed
	 * @param event
	 */
	public void signup(ActionEvent event){
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("../view/SignUp.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * clears the errorMsg field text
	 */
	public void clearText( ) {
		errorMsg.setText("");
	}
	

	/**
	 * Initializes the Login.fxml view
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
		

}
