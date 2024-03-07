package application.Kontroler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
	
	public void azurirajAvatar() {}
	
}
