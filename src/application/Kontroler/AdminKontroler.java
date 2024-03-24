package application.Kontroler;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import application.AlertHelper;
import application.Modali.PomocnaPredstava;
import application.Modali.PomocnaPredstava2;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminKontroler implements Initializable{

	@FXML
	private Button ExitAdmin;
	@FXML
	private Button UnesiPredstavu;
	@FXML
	private TableView<PomocnaPredstava2> tvPredstava;
	@FXML
	private TableColumn<PomocnaPredstava2,String> colNaziv;
	
	@FXML
	private TableColumn<PomocnaPredstava2,String> colTip;
	@FXML
	private TableColumn<PomocnaPredstava2,String> colDirektor;
	
	@FXML
	private TableColumn<PomocnaPredstava2,String> colDatum;
	@FXML
	private TableColumn<PomocnaPredstava2,String> colVrijeme;
	@FXML
	private TableColumn<PomocnaPredstava2,Double> colCijena;
	@FXML
	private TableColumn<PomocnaPredstava2,Button> colAkcija;
	
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showPredstava();
	}
	
	public void showPredstava() {
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String dbPassword = "jbbov123456";

        ObservableList<PomocnaPredstava2> Pom_predstavaList = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection(url, username, dbPassword);
            String query = "SELECT idPredstava,Naziv,tip,direktor,vrijemeIzvodjenja,datumIzvodjenja,Cijena FROM predstava";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idPredstava = resultSet.getInt("idPredstava");
                String naziv = resultSet.getString("Naziv");
                String tip=resultSet.getString("tip");
                String direktor=resultSet.getString("direktor");
                LocalTime vrijeme = resultSet.getTime("vrijemeIzvodjenja").toLocalTime();
                LocalDate datum = resultSet.getDate("datumIzvodjenja").toLocalDate();
                double cijena=resultSet.getDouble("Cijena");

                PomocnaPredstava2 Pom_predstava = new PomocnaPredstava2(naziv,tip,direktor,vrijeme,datum,cijena);
                
                Pom_predstava.getObrisi().setOnAction(event -> {
                	try {
                		Connection connection1 = DriverManager.getConnection(url, username, dbPassword);
                		String query1="DELETE FROM predstava where idPredstava=?";
                		PreparedStatement statement1 = connection1.prepareStatement(query1);
                		statement1.setInt(1, idPredstava);
                		AlertHelper.showSuccessAlert("Sucess", "Uspjesno Uklonjena Predstava sa Repertoara");
                		
                		Pom_predstavaList.remove(Pom_predstava);
                		
                		statement1.executeUpdate();
                		statement1.close();
 			           connection1.close();
                	}
                	catch(SQLException e) {
                		AlertHelper.showErrorAlert("Failiure", "Nemoguce_Obrisati");
                	}
                });
                
                Pom_predstavaList.add(Pom_predstava);
            }

            colNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
            colTip.setCellValueFactory(new PropertyValueFactory<>("tip"));
            colDirektor.setCellValueFactory(new PropertyValueFactory<>("direktor"));
            colDatum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDatumIzvodjenja().toString()));
            colVrijeme.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVrijemeIzvodjenja().toString()));
            colCijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
            colAkcija.setCellValueFactory(new PropertyValueFactory<>("Obrisi"));

            if (Pom_predstavaList.isEmpty()) {
                PomocnaPredstava2 placeholderPomPredstava = new PomocnaPredstava2("Nema Predstava na Repertoaru", "", "", LocalTime.of(0, 0), LocalDate.now(), 0.0);;
                tvPredstava.setItems(FXCollections.observableArrayList(placeholderPomPredstava));
            } else {
                tvPredstava.setItems(Pom_predstavaList);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}
