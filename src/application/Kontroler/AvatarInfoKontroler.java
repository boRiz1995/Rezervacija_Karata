package application.Kontroler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import application.AlertHelper;
import application.Modali.Avatar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AvatarInfoKontroler {
	
	@FXML
	private Button cancelButton;
	@FXML
	private Button logOutButton;
	@FXML
	private Button obrisiButton;
	@FXML
	private Button azurirajButton;
	
	@FXML
	private TextField tfIme;
	@FXML
	private TextField tfPrezime;
	@FXML
	private TextField tfEmail;
	@FXML
	private TextField tfTelefon;
	@FXML
	private TextField tfDatumRodj;
	@FXML
	private RadioButton radioM;
	@FXML
	private RadioButton radioZ;
	@FXML
	private ToggleGroup tgPol;
	@FXML
	private TextField tfPassword;
	@FXML
	private TextField tfPasswordConfirm;
	
	   private Avatar avatar;

	    public void setAvatar(Avatar avatar) {
	        this.avatar = avatar;
	        // Perform additional operations with the avatar object as needed
//	        System.out.println(avatar.getIme()+" , "+avatar.getEmail()+" , "+avatar.getPrezime());
	        tfIme.setText(avatar.getIme());
	        tfPrezime.setText(avatar.getPrezime());
	        tfEmail.setText(avatar.getEmail());
	        tfTelefon.setText(avatar.getTelefon());
	        
	     // Format the date of birth to a string representation
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        tfDatumRodj.setText((String)dateFormat.format(avatar.getDatumRodjenja()));

	        System.out.println(avatar.getDatumRodjenja());
//	        tfDatumRodj.setText((String)avatar.getDatumRodjenja());
	        // Set the appropriate radio button based on the value of avatar.getPol()
	       
	        radioM.setToggleGroup(tgPol);
	        radioZ.setToggleGroup(tgPol);
	        
	        if (avatar.getPol().equalsIgnoreCase("muski")) {
	            radioM.setSelected(true);
	            radioZ.setSelected(false);
	        } else {
	        	radioM.setSelected(false);
	            radioZ.setSelected(true);
	        }
	    }
	
	public void cancel(ActionEvent event)throws IOException {
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/View/PocetnaUpdate.fxml"));
	        Parent root = loader.load();
	        PocetnaUpdateKontroler pocetnaUpdateController = loader.getController();
	        pocetnaUpdateController.setAvatar(avatar); // Pass the avatar object to the PocetnaUpdateKontroler
	        
	        Stage stage = (Stage) cancelButton.getScene().getWindow();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	}
	
	public void logOut(ActionEvent event)throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("/application/View/Pocetna.fxml"));
		Stage stage=(Stage)logOutButton.getScene().getWindow();
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

	public void obrisiAvatar(ActionEvent event)throws IOException {
		
		String url = "jdbc:mysql://localhost:3306/db";
	    String username = "root";
	    String dbPassword = "jbbov123456";
	    try {
	    Connection connection = DriverManager.getConnection(url, username, dbPassword);
	    String query = "DELETE FROM avatar WHERE Email = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, avatar.getEmail());
        statement.executeUpdate();
		
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/View/Pocetna.fxml"));
	        Parent root = loader.load();
	        
	        Stage stage = (Stage) obrisiButton.getScene().getWindow();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	        }
	    catch(SQLException e) {
	    	AlertHelper.showErrorAlert("FAILED DELETE", "CANNOT DELETE USER DUE TO UNKNOWN FACTORS");
	    	System.out.println("Delete kveri fail");
	    	e.printStackTrace();
	    }
	    
	    
	}
	
//	public void azurirajAvatar(ActionEvent event)throws IOException {
//		
//		 if (tfIme.getText().isEmpty() || tfPrezime.getText().isEmpty() || tfEmail.getText().isEmpty() ||
//		            tfTelefon.getText().isEmpty() || tfDatumRodj.getText().isEmpty() || tgPol.getSelectedToggle() == null) {
//		        AlertHelper.showErrorAlert("INVALID INPUT", "Please fill in all required fields.");
//		        return;
//		    }
//
//		    // Get the updated values from the text fields and radio buttons
//		    String novoIme = tfIme.getText();
//		    String novoPrezime = tfPrezime.getText();
//		    String noviEmail = tfEmail.getText();
//		    String noviTelefon = tfTelefon.getText();
//		    String noviDatumRodj = tfDatumRodj.getText();  // You might need to parse this into a Date object
//
//		    RadioButton selectedRadioButton = (RadioButton) tgPol.getSelectedToggle();
//		    String noviPol = selectedRadioButton.getText();  // Assuming the text of the radio button is the gender
//
//		
//		String url = "jdbc:mysql://localhost:3306/db";
//	    String username = "root";
//	    String dbPassword = "jbbov123456";
//	    try {
//	    Connection connection = DriverManager.getConnection(url, username, dbPassword);
//	    String query = "UPDATE avatar SET Ime=?, Prezime=?, Email=?, Telefon=?, DatumRodjenja=?, Pol=? WHERE idAvatar=?";
//        PreparedStatement statement = connection.prepareStatement(query);
//        
//        statement.setString(1, novoIme);
//        statement.setString(2, novoPrezime);
//        statement.setString(3, noviEmail);
//        statement.setString(4, noviTelefon);
//        statement.setString(5, noviDatumRodj);  // You might need to set the Date object here
//        statement.setString(6, noviPol);
//        statement.setInt(7,0 );
//        
//        statement.executeUpdate();
//		
//		 FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/View/PocetnaUpdate.fxml"));
//	        Parent root = loader.load();
//	        
//	        PocetnaUpdateKontroler pocetnaUpdateController = loader.getController();
//            pocetnaUpdateController.setAvatar(avatar); // Pass the updated avatar object to PocetnaUpdateKontroler
//
//	        
//	        Stage stage = (Stage) azurirajButton.getScene().getWindow();
//	        Scene scene = new Scene(root);
//	        stage.setScene(scene);
//	        stage.show();
//	        }
//	    catch(SQLException e) {
//	    	AlertHelper.showErrorAlert("FAILED Update ", "CANNOT UPDATE USER DUE TO UNKNOWN FACTORS");
//	    	System.out.println("Update kveri fail");
//	    	e.printStackTrace();
//	    }
//	}
	public void azurirajAvatar(ActionEvent event) throws IOException {
	    if (tfIme.getText().isEmpty() || tfPrezime.getText().isEmpty() || tfEmail.getText().isEmpty() ||
	            tfTelefon.getText().isEmpty() || tfDatumRodj.getText().isEmpty() || tgPol.getSelectedToggle() == null
	            ||tfPassword.getText().isEmpty() || tfPasswordConfirm.getText().isEmpty()) {
	        AlertHelper.showErrorAlert("INVALID INPUT", "Please fill in all required fields.");
	        return;
	    }

	    // Get the updated values from the text fields and radio buttons
	    String novoIme = tfIme.getText();
	    String novoPrezime = tfPrezime.getText();
	    String noviEmail = tfEmail.getText();
	    String noviTelefon = tfTelefon.getText();
	    String noviDatumRodj = tfDatumRodj.getText();  // You might need to parse this into a Date object
	    
	 // Parse the date string into a Date object
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate;
        try {
            java.util.Date utilDate = dateFormat.parse(noviDatumRodj);
            parsedDate = new java.sql.Date(utilDate.getTime()); // Convert to java.sql.Date
        } catch (ParseException e) {
            AlertHelper.showErrorAlert("Date Parsing Error", "Error occurred while parsing the date.");
            e.printStackTrace();
            return;
        }

	    RadioButton selectedRadioButton = (RadioButton) tgPol.getSelectedToggle();
	    String noviPol = selectedRadioButton.getText();  // Assuming the text of the radio button is the gender

	    String password = tfPassword.getText();
	    String passwordConfirm = tfPasswordConfirm.getText();
	    
	    String url = "jdbc:mysql://localhost:3306/db";
	    String username = "root";
	    String dbPassword = "jbbov123456";
	    
	    if (!password.equals(passwordConfirm)) {
	        AlertHelper.showErrorAlert("Password Mismatch", "Password and confirm password fields do not match.");
	        return;
	    }

	    try {
	        // New connection to obtain avatar id
	        Connection connectionId = DriverManager.getConnection(url, username, dbPassword);
	        String queryId = "SELECT idAvatar FROM avatar WHERE Email=?";
	        PreparedStatement statementId = connectionId.prepareStatement(queryId);
	        statementId.setString(1, avatar.getEmail());
	        statementId.execute();
	        int avatarId = -1;
	        if (statementId.getResultSet().next()) {
	            avatarId = statementId.getResultSet().getInt("idAvatar");
	        }
	        statementId.close();
	        connectionId.close();

	        // Check if the new email is unique
	        Connection connectionEmailCheck = DriverManager.getConnection(url, username, dbPassword);
	        String queryEmailCheck = "SELECT COUNT(*) AS count FROM avatar WHERE Email=?";
	        PreparedStatement statementEmailCheck = connectionEmailCheck.prepareStatement(queryEmailCheck);
	        statementEmailCheck.setString(1, noviEmail);
	        statementEmailCheck.execute();
	        int emailCount = 0;
	        if (statementEmailCheck.getResultSet().next()) {
	            emailCount = statementEmailCheck.getResultSet().getInt("count");
	        }
	        statementEmailCheck.close();
	        connectionEmailCheck.close();

	        if (emailCount > 0) {
	            AlertHelper.showErrorAlert("DUPLICATE EMAIL", "The email already exists in the database.");
	            return;
	        }

	        // Update the avatar with new values
	        Connection connection = DriverManager.getConnection(url, username, dbPassword);
//	        String query = "UPDATE avatar SET Ime=?, Prezime=?, Email=?, Telefon=?, Pol=?, Password=? WHERE idAvatar=?";
	        String query = "UPDATE avatar SET Ime=?, Prezime=?, Email=?, Telefon=?, DatumRodjenja=?, Pol=?, Password=? WHERE idAvatar=?";
	        PreparedStatement statement = connection.prepareStatement(query);

	        statement.setString(1, novoIme);
	        statement.setString(2, novoPrezime);
	        statement.setString(3, noviEmail);
	        statement.setString(4, noviTelefon);
	        statement.setDate(5, parsedDate);  // You might need to set the Date object here
	        statement.setString(6, noviPol);
	        statement.setString(7, password);
	        statement.setInt(8, avatarId);

	        statement.executeUpdate();
	        statement.close();
	        connection.close();

	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/View/PocetnaUpdate.fxml"));
	        Parent root = loader.load();

	        PocetnaUpdateKontroler pocetnaUpdateController = loader.getController();
	        Avatar updatedAvatar = new Avatar();
	        updatedAvatar.setIme(novoIme);
	        updatedAvatar.setPrezime(novoPrezime);
	        updatedAvatar.setEmail(noviEmail);
	        updatedAvatar.setTelefon(noviTelefon);
	        updatedAvatar.setDatumRodjenja(parsedDate); // Set the parsed date
	        updatedAvatar.setPol(noviPol);
	        pocetnaUpdateController.setAvatar(updatedAvatar); // Pass the updated avatar object to PocetnaUpdateKontroler

	        Stage stage = (Stage) azurirajButton.getScene().getWindow();
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	    } catch (SQLException e) {
	        AlertHelper.showErrorAlert("FAILED Update ", "CANNOT UPDATE USER DUE TO UNKNOWN FACTORS");
	        System.out.println("Update kveri fail");
	        e.printStackTrace();
	    }
	}
	
}
