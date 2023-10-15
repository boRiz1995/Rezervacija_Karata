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

import application.Modali.Avatar;
import application.Modali.Predstava;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PocetnaUpdateKontroler implements Initializable {
	
	@FXML
    private Button avatarInfoButton;
	@FXML
	private TableView<Predstava> tvPredstava;
	@FXML
	private TableColumn<Predstava,String> colNaziv;
	@FXML
	private TableColumn<Predstava,String> colDatum;
	@FXML
	private TableColumn<Predstava,String> colVrijeme;
	@FXML
	private TableColumn<Predstava,Double> colCijena;
	@FXML
	private DatePicker datumIzvedbe;
	@FXML
	private ChoiceBox<String> tip;
	@FXML
	private Button filter;
	@FXML
	private Button refresh;
	@FXML
	private Button rezervacije;

	
	private Avatar avatar;

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
        // Use the avatar data in the dashboard scene
        // ...
        
        avatarInfoButton.setText(avatar.getIme());
    }
    public void avatarInfo() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/View/AvatarInfo.fxml"));
        Parent root = loader.load();

        AvatarInfoKontroler avatarInfoKontroler = loader.getController();
        avatarInfoKontroler.setAvatar(avatar); // Pass the Avatar object

        Stage stage = (Stage) avatarInfoButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void showPredstava() {
		 String url = "jdbc:mysql://localhost:3306/db";
		    String username = "root";
		    String dbPassword = "jbbov123456";

		    ObservableList<Predstava>predstavaList = FXCollections.observableArrayList();
		try {
           // Connect to the database
           Connection connection = DriverManager.getConnection(url, username, dbPassword);
           // Execute a query to fetch data from the database
           String query = "SELECT Naziv,tip,direktor,vrijemeIzvodjenja,datumIzvodjenja,Cijena FROM predstava";
           PreparedStatement statement = connection.prepareStatement(query);
           ResultSet resultSet = statement.executeQuery();
           // Populate the list with data from the database
           while (resultSet.next()) {
               String naziv = resultSet.getString("Naziv");
               String tip=resultSet.getString("tip");
               String direktor=resultSet.getString("direktor");
               LocalTime vrijeme = resultSet.getTime("vrijemeIzvodjenja").toLocalTime();
               LocalDate datum = resultSet.getDate("datumIzvodjenja").toLocalDate();
               double cijena=resultSet.getDouble("Cijena");

               Predstava predstava = new Predstava(naziv,tip,direktor,vrijeme,datum,cijena);
               predstavaList.add(predstava);
               
           }

//            Close the database connection
           resultSet.close();
           statement.close();
           connection.close();
               
               ObservableList<Predstava> filteredList = FXCollections.observableArrayList();
               
               for (Predstava predstava : predstavaList) {
                   LocalDate predstavaDate = predstava.getDatumIzvodjenja();
                   LocalTime predstavaTime = predstava.getVrijemeIzvodjenja();
                   
                   if (predstavaDate.isBefore(LocalDate.now()) || 
                       (predstavaDate.equals(LocalDate.now()) && predstavaTime.isBefore(LocalTime.now()))) {
                       filteredList.remove(predstava);
                   }else {
                   	filteredList.add(predstava);
                   }
               }
               colNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
               colDatum.setCellValueFactory(cellData -> {
                   LocalDate datum = cellData.getValue().getDatumIzvodjenja();
                   return new SimpleStringProperty(datum.toString());
               });
               colVrijeme.setCellValueFactory(cellData -> {
                   LocalTime vrijeme = cellData.getValue().getVrijemeIzvodjenja();
                   return new SimpleStringProperty(vrijeme.toString());
               });
               colCijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
               

               if (filteredList.isEmpty()) {
                   Predstava placeholderPredstava = new Predstava("Nema Predstava na Repertoaru", "", "", LocalTime.of(0, 0), LocalDate.now(), 0.0);
                   tvPredstava.setItems(FXCollections.observableArrayList(placeholderPredstava));
               } else {
               	tvPredstava.setItems(filteredList);
               }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		datumIzvedbe.setValue(LocalDate.now());
		tip.getItems().addAll("Default","Mjuzikl", "Drama", "Tragedija","Komedija","Avangarda","Dječija");
		tip.getSelectionModel().selectFirst();
		showPredstava();
	}
}
