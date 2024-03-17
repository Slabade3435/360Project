package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Staff {

	public Scene staffLookUp(Scene previousScene) {

		BorderPane root = new BorderPane();

		// Staff Label
		Label staffLabel = new Label("Staff Portal");
		staffLabel.setFont(Font.font("Arial", 40));
		staffLabel.setTextFill(Color.web("#0844a4"));

		// Back Button
		Button backToMain = new Button("Back");
		backToMain.setStyle("-fx-background-color: #0844a4; -fx-text-fill: white; -fx-font-size: 20px;");
		backToMain.setOnAction(e -> {
			Stage stage = (Stage) backToMain.getScene().getWindow();
			stage.setScene(previousScene);
		});

		// Top Menu
		HBox topMenu = new HBox();
		topMenu.getChildren().addAll(backToMain, staffLabel);
		topMenu.setAlignment(Pos.TOP_LEFT);
		topMenu.setSpacing(270.0);
		topMenu.setPadding(new Insets(10));

		// Set the menu at the top
		root.setTop(topMenu);
		root.setAlignment(topMenu, Pos.TOP_CENTER);

		// Username Label and input field
		Label staffUserName = new Label("Username");
		staffUserName.setFont(Font.font("Arial", 20));
		staffUserName.setTextFill(Color.BLACK);

		TextField staffUserNameField = new TextField();

		HBox userNameHolder = new HBox();
		userNameHolder.getChildren().addAll(staffUserName, staffUserNameField);
		userNameHolder.setAlignment(Pos.CENTER);
		userNameHolder.setSpacing(10);

		// password Label and input field
		Label passwordLabel = new Label("Password");
		passwordLabel.setFont(Font.font("Arial", 20));
		passwordLabel.setTextFill(Color.BLACK);

		TextField passwordField = new TextField();

		HBox passwordHolder = new HBox();
		passwordHolder.getChildren().addAll(passwordLabel, passwordField);
		passwordHolder.setAlignment(Pos.CENTER);
		passwordHolder.setSpacing(10);

		//Login button
		Button staffLoginButton = new Button("Login");
		staffLoginButton.setStyle("-fx-background-color: #0844a4; -fx-text-fill: white; -fx-font-size: 20px;");
		staffLoginButton.setOnAction(e->{
			String userNameInput = staffUserNameField.getText();
			String passwordInput = passwordField.getText();
			if(checkLogin(userNameInput, passwordInput)) {
				System.out.println("User exists within the DB");
			}
			else {
				System.out.println("User DOES NOT exist");
			}
			
		});
		
		
		// adding everything to a VBox
		VBox staffPortalVbox = new VBox();
		staffPortalVbox.getChildren().addAll(userNameHolder, passwordHolder, staffLoginButton);
		staffPortalVbox.setAlignment(Pos.CENTER);
		staffPortalVbox.setSpacing(50);
		staffPortalVbox.setPadding(new Insets(-100, 0, 0, 0));
		
		//setting the vbox to the center
		root.setCenter(staffPortalVbox);

		Scene scene = new Scene(root, 900, 600);

		return scene;
	}
	
	private boolean checkLogin(String userName, String password) {
		
		boolean foundUser = false;

		try(BufferedReader reader = new BufferedReader(new FileReader("staffLogins"))){
			String userLogin = userName + "," + password;
			System.out.println(userLogin);
			String line;
			boolean isFirstLine = true;
			while((line = reader.readLine()) != null) {
				if(isFirstLine == true) {
					isFirstLine = false;
				}
				else {
					if(line.equals(userLogin)) {
						foundUser = true;
						System.out.println("Do we come here?");
						break;
					}	
				}
			}
			reader.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return foundUser;
	}
}
