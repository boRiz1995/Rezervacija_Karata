package application.Kontroler;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminKontroler {

	@FXML
	private Button ExitAdmin;
	@FXML
	private Button UnesiPredstavu;
	
	public void kreirajPredstavuQuery(ActionEvent event) throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("/application/View/NovaPredstava.fxml"));
		Stage stage=(Stage)ExitAdmin.getScene().getWindow();
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void exitAdmin(ActionEvent event)throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("/application/View/Pocetna.fxml"));
		Stage stage=(Stage)ExitAdmin.getScene().getWindow();
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
