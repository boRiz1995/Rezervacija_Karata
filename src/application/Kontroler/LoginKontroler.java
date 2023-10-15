package application.Kontroler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.AlertHelper;
import application.Modali.Avatar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginKontroler {

	@FXML
	private Button cancelButton;
	@FXML
	private Hyperlink registerLink;
	@FXML
	private TextField tfEmail;
	@FXML
	private TextField tfPassword;
	
	public void loginQuery() {
	    String url = "jdbc:mysql://localhost:3306/db";
	    String username = "root";
	    String dbPassword = "jbbov123456";
		
	    try (Connection connection = DriverManager.getConnection(url, username, dbPassword)) {
	        System.out.println("Connected to the database!");

	        // Prepare the SQL statement with placeholders for email and password
	        String query = "SELECT * FROM avatar WHERE email = ? AND password = ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            // Retrieve the values from the text fields
	            String email = tfEmail.getText();
	            String password = tfPassword.getText();

	            // Set the values for the placeholders
	            statement.setString(1, email);
	            statement.setString(2, password);

	            // Execute the query
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                // Credentials match, login successful
	                System.out.println("Login successful!");
	                
	             // Retrieve the avatar data from the result set
	                String emailResult = resultSet.getString("email");
	                String passwordResult = resultSet.getString("password");
	                String polResult = resultSet.getString("pol");
	                String imeResult = resultSet.getString("ime");
	                String prezimeResult = resultSet.getString("prezime");
	                String telefonResult = resultSet.getString("telefon");
	                Date datumRodjenjaResult = resultSet.getDate("datumRodjenja");

	                // Create an instance of Avatar with the retrieved data
	                Avatar avatar = new Avatar(emailResult, passwordResult, polResult, imeResult, prezimeResult,
	                        telefonResult, datumRodjenjaResult);
	                if (email.equals("admin@admin.com") && password.equals("admin")) {
	                    // Redirect to the admin.fxml scene
	                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/View/Admin.fxml"));
	                    Parent root = loader.load();
	                    Stage stage = new Stage();
	                    stage.setScene(new Scene(root));
	                    stage.show();

	                    // Close the current login screen (assuming the current screen is a Stage)
	                    Stage currentStage = (Stage) tfEmail.getScene().getWindow();
	                    currentStage.close();
//	                    AlertHelper.showSuccessAlert("Login Successful", "You have successfully logged in as an admin.");
	                } else {
	             // Pass the avatar data to a different screen (assume the target screen is called "Dashboard.fxml")
	                FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/View/PocetnaUpdate.fxml"));
	                Parent root = loader.load();
	                PocetnaUpdateKontroler pocetnaUpdateKontroler = loader.getController();
	                pocetnaUpdateKontroler.setAvatar(avatar);

	                Stage stage = new Stage();
	                stage.setScene(new Scene(root));
	                stage.show();

	                // Close the current login screen (assuming the current screen is a Stage)
	                Stage currentStage = (Stage) tfEmail.getScene().getWindow();
	                currentStage.close();
//	                AlertHelper.showSuccessAlert("Login Successful", "You have successfully logged in.");

	                }
	            } else {
	                // Credentials do not match, login failed
	                System.out.println("Invalid email or password!");
	                AlertHelper.showErrorAlert("Login Failed", "Invalid email or password. Please try again.");
	            }
	        }
	    } catch (SQLException | IOException e) {
	        System.out.println("Failed to connect to the database!");
	        e.printStackTrace();
	    }
	}
	
	public void switchRegister(ActionEvent event)throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("/application/View/Register.fxml"));
		Stage stage=(Stage)registerLink.getScene().getWindow();
		Scene scene=new Scene(root);
		stage.setScene(scene);
	}
	
	public void cancel(ActionEvent event)throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("/application/View/Pocetna.fxml"));
		Stage stage=(Stage)cancelButton.getScene().getWindow();
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
