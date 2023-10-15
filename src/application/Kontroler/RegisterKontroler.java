package application.Kontroler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.AlertHelper;
import application.Modali.Avatar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class RegisterKontroler {

	@FXML
	private Button cancelButton;
	@FXML
	private ToggleGroup tgPol;
	@FXML
	private Button registerButton;
	@FXML
	private RadioButton radioM;
	@FXML
	private RadioButton radioZ;
	@FXML 
	private TextField tfIme;
	@FXML 
	private TextField tfPrezime;
	@FXML 
	private TextField tfEmail;
	@FXML 
	private TextField tfTelefon;
	@FXML 
	private PasswordField pfPassword;
	@FXML 
	private DatePicker dpDatumRodj;
	
	public void cancel(ActionEvent event)throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("/application/View/Pocetna.fxml"));
		Stage stage=(Stage)cancelButton.getScene().getWindow();
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void registerQuery(ActionEvent event)throws IOException {
		
		String url = "jdbc:mysql://localhost:3306/db";
	    String username = "root";
	    String dbPassword = "jbbov123456";
		
		 // Get the values from the form fields or wherever you have them stored
        String email = tfEmail.getText();
        String password = pfPassword.getText();
       
        RadioButton selectedRadioButton = (RadioButton) tgPol.getSelectedToggle();
        
        String pol = selectedRadioButton.getText();
        String ime = tfIme.getText();
        String prezime = tfPrezime.getText();
        String telefon = tfPrezime.getText();
        Date datumRodjenja = Date.valueOf(dpDatumRodj.getValue());

        // Create a new Avatar object
        Avatar avatar = new Avatar(email, password, pol, ime, prezime, telefon, datumRodjenja);

        // Check if the Avatar already exists in the database
        if (avatar.CheckAvatarDB()) {
            System.out.println("Avatar already exists in the database.");
            AlertHelper.showWarningAlert("Avatar already exists", "The avatar already exists in the database.");

//            showAlertDialog("Avatar already exists", "The avatar already exists in the database.");
            //iimprove with gui visualisation
        } else {
            // Open a new connection to the database
            try (Connection connection = DriverManager.getConnection(url, username, dbPassword)) {
                // Store the avatar in the database
                // ... (code for storing avatar in the database)
            	 String sql = "INSERT INTO avatar (email, password, pol, ime, prezime, telefon, datumRodjenja) VALUES (?, ?, ?, ?, ?, ?, ?)";
                 PreparedStatement statement = connection.prepareStatement(sql);
                 statement.setString(1, avatar.getEmail());
                 statement.setString(2, avatar.getPassword());
                 statement.setString(3, avatar.getPol());
                 statement.setString(4, avatar.getIme());
                 statement.setString(5, avatar.getPrezime());
                 statement.setString(6, avatar.getTelefon());
                 statement.setDate(7, avatar.getDatumRodjenja());

                 // Execute the SQL statement
                 statement.executeUpdate();
                 
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
//	                showAlertDialog("Avatar Created", "Avatar successfully stored in the database");
	                AlertHelper.showSuccessAlert("Success", "Avatar successfully stored in the database.");
                System.out.println("Avatar successfully stored in the database.");
            } catch (SQLException e) {
                e.printStackTrace();
                AlertHelper.showErrorAlert("Error", "Failed to store the avatar in the database.");
                System.out.println("AVATAR FLUNK");
            }
        }
    }
	
//	private void showAlertDialog(String title, String content) {
//	    Alert alert = new Alert(AlertType.INFORMATION);
//	    alert.setTitle(title);
//	    alert.setHeaderText(null);
//	    alert.setContentText(content);
//	    alert.showAndWait();
//	}

    // ...
		
	
	
}
