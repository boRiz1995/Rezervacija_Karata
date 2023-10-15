package application.Kontroler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import application.AlertHelper;
import application.Modali.Avatar;
import application.Modali.Predstava;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NovaPredstavaKontroler implements Initializable {
	
	@FXML
	private Button cancelButton;
	@FXML
	private Button Kreiraj;
	@FXML
	private DatePicker datumIzvedbe;
	@FXML
	private ChoiceBox<String> tip;
	@FXML
	private TextField nazivPredstaveTf;
	@FXML
	private TextField direktorTf;
	@FXML
	private TextField vrijemeIzvedbeTf;
	@FXML
	private TextField cijenaPredstaveTf;
	
	public void cancel(ActionEvent event)throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("/application/View/Admin.fxml"));
		Stage stage=(Stage)cancelButton.getScene().getWindow();
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void kreirajPredstavuQuery(ActionEvent event)throws IOException {
		 String nazivPredstave = nazivPredstaveTf.getText();
	        String direktor = direktorTf.getText();
	        String tipPredstave = tip.getValue();
	        LocalDate datum = datumIzvedbe.getValue();
	        LocalTime vrijemeIzvedbe =LocalTime.parse(vrijemeIzvedbeTf.getText());
	   
	        double cijenaPredstave = Double.parseDouble(cijenaPredstaveTf.getText());
	        
	        // Create a Predstava object with the entered values
	        Predstava novaPredstava = new Predstava(nazivPredstave,tipPredstave,direktor, vrijemeIzvedbe, datum, cijenaPredstave);
//	        System.out.println(novaPredstava.getCijena()+" "+" \n" +novaPredstava.getDatumIzvodjenja()+"\n"+novaPredstava.getVrijemeIzvodjenja());
	        String url = "jdbc:mysql://localhost:3306/db";
		    String username = "root";
		    String dbPassword = "jbbov123456";
			
		    try (Connection connection = DriverManager.getConnection(url, username, dbPassword)) {
		        System.out.println("Connected to the database!");

		        // Prepare the SQL statement with placeholders for email and password
		        String query = "INSERT INTO predstava (naziv, direktor, tip, datumIzvodjenja, vrijemeIzvodjenja, cijena) VALUES (?, ?, ?, ?, ?, ?)";
		        try (PreparedStatement statement = connection.prepareStatement(query)) {
		            
		            // Set the values for the placeholders
		        	statement.setString(1, novaPredstava.getNaziv());
		            statement.setString(2, novaPredstava.getDirektor());
		            statement.setString(3, novaPredstava.getTip());
		            statement.setDate(4, Date.valueOf(novaPredstava.getDatumIzvodjenja()));
		            statement.setTime(5, Time.valueOf(novaPredstava.getVrijemeIzvodjenja()));
		            statement.setDouble(6, novaPredstava.getCijena());


		            // Execute the query
		            statement.executeUpdate();
		            
		            //Switching back to Admin.fxml
		            Parent root=FXMLLoader.load(getClass().getResource("/application/View/Admin.fxml"));
		    		Stage stage=(Stage)cancelButton.getScene().getWindow();
		    		Scene scene=new Scene(root);
		    		stage.setScene(scene);
		    		stage.show();
		            
		        }
		    } catch (SQLException e) {
		        System.out.println("Failed to connect to the database!");
		        e.printStackTrace();
		    }
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		datumIzvedbe.setValue(LocalDate.now());
		tip.getItems().addAll("Mjuzikl", "Drama", "Tragedija","Komedija","Avangarda","Dječija");
//		tip.setOnAction(this::);
	}
}
