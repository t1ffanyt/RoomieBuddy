package application.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;



/**
 * This class handles the Results.fxml
 * UTSA CS 3443
 * Spring 2019  
 * @author Tiffany Tabourne, Anna Arroyo
 *
 */
public class ResultsController implements EventHandler<ActionEvent> {

	@FXML 
	private ListView<String> results;
	@FXML 
	private TextArea roommateInfo;
	@FXML 
	private Button logout; 
	@FXML
	private HashMap<String,String> map;

	/**
	 * Handles when go back button is pushed. Sends user to the Pick View
	 * @param event
	 */
	public void goBack(ActionEvent event) {
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("../view/Pick.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();	

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * load matches on ListView
	 * @throws IOException
	 */
	public void loadResults(HashMap<String,String> matches) throws IOException{
		map = matches;
		ArrayList<String> listOfNames = new ArrayList<String>();
		for(String name : matches.keySet()) {
			listOfNames.add(name);
		}
		ObservableList<String> items = FXCollections.observableArrayList( listOfNames );
		results.setItems( items );
	}

	/**
	 * Handles when the names are clicked
	 * @param event
	 */
	public void handleClick(javafx.scene.input.MouseEvent event) {

		try {
			String name = results.getSelectionModel().getSelectedItem();
			for (Entry<String, String>  entry : map.entrySet()) {
				if(name.equals(entry.getKey())) {
					String value = entry.getValue();
					roommateInfo.setText(value);
				}
			}
		}
		catch(Exception e) {
			System.out.println("Select a name!");
		}
	}

	/**
	 * Handles when the logout button is pushed
	 * @param event
	 */
	@Override
	public void handle(ActionEvent arg0) {
 
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Main.stage.setScene(new Scene(root, 800, 800));
			Main.stage.show();	

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
