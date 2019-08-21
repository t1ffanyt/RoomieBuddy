package application.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import application.Main;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Controller for the Pick.fxml view
 * 
 * @author Luis Valdes
 * UTSA CS 3443
 * Spring 2019
 */
public class PickViewController implements EventHandler<ActionEvent>  {
	@Override
	/**
	 * Handles when the edit profile button is clicked
	 */
	public void handle(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("../view/Profile.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
	/**
	 * Handle when the results button is clicked
	 * @param event
	 * @throws IOException
	 */
	public void results(ActionEvent event) throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../view/Results.fxml"));
		Parent root = (Parent) loader.load();
				
		ArrayList<User> list = LoginController.userNetwork.getMatches(LoginController.enteredUser);
		HashMap<String, String> matches= LoginController.userNetwork.getMapDifferences(LoginController.enteredUser, list);
		ResultsController resultsController = loader.getController();
		resultsController.loadResults(matches);

		Main.stage.setScene(new Scene(root, 800, 800));
		Main.stage.show();	
		
	}
	
	/**
	 * Handles when the logout button is clicked
	 * @param event
	 * @throws IOException
	 */
	public void logout(ActionEvent event) throws IOException {
		// TODO Auto-generated method stub
		Parent root;
		root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
		Main.stage.setScene(new Scene(root, 800, 800));
		Main.stage.show();	
		LoginController.enteredUser = LoginController.logoutUser;
		
	}

}
