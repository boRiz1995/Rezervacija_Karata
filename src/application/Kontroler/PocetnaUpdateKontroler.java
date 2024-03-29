package application.Kontroler;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import application.AlertHelper;
import application.Modali.Avatar;
import application.Modali.PomocnaPredstava;
import application.Modali.Predstava;
import application.Modali.RezervisanaPredstava;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PocetnaUpdateKontroler implements Initializable {
	
	@FXML
    private Button avatarInfoButton;
	@FXML
	private TableView<PomocnaPredstava> tvPredstava;
	@FXML
	private TableColumn<PomocnaPredstava,String> colNaziv;
	@FXML
	private TableColumn<PomocnaPredstava,String> colDatum;
	@FXML
	private TableColumn<PomocnaPredstava,String> colVrijeme;
	@FXML
	private TableColumn<PomocnaPredstava,Double> colCijena;
	@FXML
	private TableColumn<PomocnaPredstava,Button> colAkcija1;
	@FXML
	private TableColumn<PomocnaPredstava,Button> colAkcija2;
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
    
    public void rezervacije()throws IOException {
   	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/View/Rezervacije.fxml"));
        Parent root = loader.load();

        RezervisanaPredstavaKontroler rezervisanePred = loader.getController();
        rezervisanePred.setAvatar(avatar); // Pass the Avatar object

        Stage stage = (Stage) rezervacije.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
	}
    
    public void showPredstava() {
		 String url = "jdbc:mysql://localhost:3306/db";
		    String username = "root";
		    String dbPassword = "jbbov123456";
		
		    
		    ObservableList<PomocnaPredstava>Pom_predstavaList = FXCollections.observableArrayList();
		try {    
		
			//2gi kveri
//		    int someData=0;
		    //2gikveri
			 // Connect to the database
	           Connection connection = DriverManager.getConnection(url, username, dbPassword);
		
           // Connect to the database
//	           System.out.println(avatar.getEmail());
	           //2gi // Second Query
//	           System.out.println(" oi crnac "+avatarInfoButton.getText());
//	           String query2 = "SELECT idAvatar FROM avatar WHERE Ime=?";
//	           PreparedStatement statement2 = connection.prepareStatement(query2);
////	           statement2.setString(1, "Boris"); // Set the value for the placeholder
//	           statement2.setString(1,avatarInfoButton.getText() ); // Set the value for the placeholder
//	           ResultSet resultSet2 = statement2.executeQuery();
//
//	           // Use the results from the second query later in your code
////	           System.out.println(resultSet2.next()+avatarInfoButton.getText());
//	           while (resultSet2.next()) {
//	               // Process the results of the second query
//	               // For example:
//	               someData = resultSet2.getInt("idAvatar");
//	               // Use someData as needed
//	               System.out.println("BOKULJA");
//	           }
//System.out.println(someData +" posle bokulje");
//	           resultSet2.close();
//	           statement2.close();
//	           
//	           
	           //2gi //second query
	           
	           
//           Connection connection = DriverManager.getConnection(url, username, dbPassword);
           // Execute a query to fetch data from the database
           String query = "SELECT idPredstava,Naziv,tip,direktor,vrijemeIzvodjenja,datumIzvodjenja,Cijena FROM predstava";
           PreparedStatement statement = connection.prepareStatement(query);
           ResultSet resultSet = statement.executeQuery();
           // Populate the list with data from the database
           while (resultSet.next()) {
        	   
      	   int idpredstava=resultSet.getInt("idPredstava");
        	   
               String naziv = resultSet.getString("Naziv");
               String tip=resultSet.getString("tip");
               String direktor=resultSet.getString("direktor");
               LocalTime vrijeme = resultSet.getTime("vrijemeIzvodjenja").toLocalTime();
               LocalDate datum = resultSet.getDate("datumIzvodjenja").toLocalDate();
               double cijena=resultSet.getDouble("Cijena");

               PomocnaPredstava Pom_predstava = new PomocnaPredstava(naziv,tip,direktor,vrijeme,datum,cijena);
              
               Pom_predstava.getRezervisi().setOnAction(event -> {


				try {
					int someData=0;
					Connection connection2 = DriverManager.getConnection(url, username, dbPassword);
					System.out.println(" oi crnac "+avatarInfoButton.getText());
			           String query2 = "SELECT idAvatar FROM avatar WHERE Ime=?";
			           PreparedStatement statement2 = connection2.prepareStatement(query2);
//			           statement2.setString(1, "Boris"); // Set the value for the placeholder
			           statement2.setString(1,avatarInfoButton.getText() ); // Set the value for the placeholder
			           ResultSet resultSet2 = statement2.executeQuery();
		
			           // Use the results from the second query later in your code
//			           System.out.println(resultSet2.next()+avatarInfoButton.getText());
			           while (resultSet2.next()) {
			               // Process the results of the second query
			               // For example:
			               someData = resultSet2.getInt("idAvatar");
			               // Use someData as needed
			               System.out.println("BOKULJA");
			           }
//		System.out.println(someData +" posle bokulje");
		System.out.println("ALERT REZERVISI "+avatarInfoButton.getText()+ idpredstava+"id avatara" +someData);
			           resultSet2.close();
			           statement2.close();
			           connection2.close();
			           System.out.println(someData +" posle bokulje");
			           
			           //insertquery za rezervisanu predstavu
			           try {
			        	   Connection connection3 = DriverManager.getConnection(url, username, dbPassword);
			           String query3="INSERT INTO rezervisana_predstava (avatar_id,idPredstava) VALUES (?,?);";
			           PreparedStatement statement3=connection3.prepareStatement(query3);
			           statement3.setInt(1, someData);
			           statement3.setInt(2, idpredstava);
			           System.out.println("nakon izvrsavanja 3ceg kverija "+statement3.executeUpdate());
			           statement3.close();
			           connection3.close();
			           }
			           catch(SQLException exp1) {
			        	   System.out.println("insert izuzetak za 3cu konekciju");
			        	   exp1.printStackTrace();
			           }
			           //potencijonalno kreiranje rezervisi predstava objekat zbog prenosa na sledecu scenu ???
			           //insertquery za rez predstavu
				}
				catch(SQLException exp) {
					System.out.println("Konekcija 2 exception pokusaj ");
					exp.printStackTrace();
				}

               });
               
               Pom_predstavaList.add(Pom_predstava);
               
           } 
           
           resultSet.close();
           statement.close();
           connection.close();

               ObservableList<PomocnaPredstava> Pom_filteredList = FXCollections.observableArrayList();
               
               for (PomocnaPredstava Pom_predstava : Pom_predstavaList) {
                   LocalDate predstavaDate = Pom_predstava.getDatumIzvodjenja();
                   LocalTime predstavaTime = Pom_predstava.getVrijemeIzvodjenja();
                   
                   if (predstavaDate.isBefore(LocalDate.now()) || 
                       (predstavaDate.equals(LocalDate.now()) && predstavaTime.isBefore(LocalTime.now()))) {
                       Pom_filteredList.remove(Pom_predstava);
                   }else {
                   	Pom_filteredList.add(Pom_predstava);
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
              //dugmad za info i rezervisi
               colAkcija1.setCellValueFactory(new PropertyValueFactory<>("info"));
               colAkcija2.setCellValueFactory(new PropertyValueFactory<>("rezervisi"));
//
               if (Pom_filteredList.isEmpty()) {
                   PomocnaPredstava placeholderPomPredstava = new PomocnaPredstava("Nema Predstava na Repertoaru", "", "", LocalTime.of(0, 0), LocalDate.now(), 0.0);
                   tvPredstava.setItems(FXCollections.observableArrayList(placeholderPomPredstava));
               } else {
               	tvPredstava.setItems(Pom_filteredList);
               }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
    
    public void filterPredstava() {
	    String url = "jdbc:mysql://localhost:3306/db";
	    String username = "root";
	    String dbPassword = "jbbov123456";

	    ObservableList<PomocnaPredstava> Pom_predstavaList = FXCollections.observableArrayList();
	    try {
	        // Connect to the database
	        Connection connection = DriverManager.getConnection(url, username, dbPassword);
	        // Execute a query to fetch data from the database
	        String query = "SELECT idPredstava,Naziv,tip,direktor,vrijemeIzvodjenja,datumIzvodjenja,Cijena FROM predstava";
	        PreparedStatement statement = connection.prepareStatement(query);
	        ResultSet resultSet = statement.executeQuery();
	        // Populate the list with data from the database
	        while (resultSet.next()) {
	        	
	        	int idpredstava=resultSet.getInt("idPredstava");
	        	
	            String naziv = resultSet.getString("Naziv");
	            String tip = resultSet.getString("tip");
	            String direktor = resultSet.getString("direktor");
	            LocalTime vrijeme = resultSet.getTime("vrijemeIzvodjenja").toLocalTime();
	            LocalDate datum = resultSet.getDate("datumIzvodjenja").toLocalDate();
	            double cijena = resultSet.getDouble("Cijena");

	            PomocnaPredstava Pom_predstava = new PomocnaPredstava(naziv, tip, direktor, vrijeme, datum, cijena);
	            
	            //rezervisi klik nakon filtriranja
	            Pom_predstava.getRezervisi().setOnAction(event -> {


					try {
						int someData=0;
						Connection connection2 = DriverManager.getConnection(url, username, dbPassword);
						System.out.println(" oi crnac "+avatarInfoButton.getText());
				           String query2 = "SELECT idAvatar FROM avatar WHERE Ime=?";
				           PreparedStatement statement2 = connection2.prepareStatement(query2);
//				           statement2.setString(1, "Boris"); // Set the value for the placeholder
				           statement2.setString(1,avatarInfoButton.getText() ); // Set the value for the placeholder
				           ResultSet resultSet2 = statement2.executeQuery();
			
				           // Use the results from the second query later in your code
//				           System.out.println(resultSet2.next()+avatarInfoButton.getText());
				           while (resultSet2.next()) {
				               // Process the results of the second query
				               // For example:
				               someData = resultSet2.getInt("idAvatar");
				               // Use someData as needed
				               System.out.println("BOKULJA");
				           }
//			System.out.println(someData +" posle bokulje");
			System.out.println("ALERT REZERVISI "+avatarInfoButton.getText()+ idpredstava+"id avatara" +someData);
				           resultSet2.close();
				           statement2.close();
				           connection2.close();
				           System.out.println(someData +" posle bokulje");
				           
				           //insertquery za rezervisanu predstavu
				           try {
				        	   Connection connection3 = DriverManager.getConnection(url, username, dbPassword);
				           String query3="INSERT INTO rezervisana_predstava (avatar_id,idPredstava) VALUES (?,?);";
				           PreparedStatement statement3=connection3.prepareStatement(query3);
				           statement3.setInt(1, someData);
				           statement3.setInt(2, idpredstava);
				           System.out.println("nakon izvrsavanja 3ceg kverija "+statement3.executeUpdate());
				           statement3.close();
				           connection3.close();
				           }
				           catch(SQLException exp1) {
				        	   System.out.println("insert izuzetak za 3cu konekciju");
				        	   exp1.printStackTrace();
				           }
				           //potencijonalno kreiranje rezervisi predstava objekat zbog prenosa na sledecu scenu ???
				           //insertquery za rez predstavu
					}
					catch(SQLException exp) {
						System.out.println("Konekcija 2 exception pokusaj ");
						exp.printStackTrace();
					}

	               });
	            //
	            
	            
	            Pom_predstavaList.add(Pom_predstava);
	        }

	        // Close the database connection
	        resultSet.close();
	        statement.close();
	        connection.close();

	        ObservableList<PomocnaPredstava> Pom_filteredList = FXCollections.observableArrayList();

	        LocalDate userSelectedDate = datumIzvedbe.getValue();
	        LocalTime currentTime = LocalTime.now();

	        for (PomocnaPredstava Pom_predstava : Pom_predstavaList) {
	            LocalDate predstavaDate = Pom_predstava.getDatumIzvodjenja();
	            LocalTime predstavaTime = Pom_predstava.getVrijemeIzvodjenja();
	            String predstavaTip = Pom_predstava.getTip();

	            if (userSelectedDate.isEqual(LocalDate.now()) && predstavaDate.isEqual(LocalDate.now()) && predstavaTime.isAfter(currentTime) && tip.getValue().equals("Default")) {
	                Pom_filteredList.add(Pom_predstava);
	            } else if (userSelectedDate.isAfter(LocalDate.now()) && predstavaDate.isEqual(userSelectedDate) && tip.getValue().equals("Default")) {
	                Pom_filteredList.add(Pom_predstava);
	            } else if (predstavaDate.isEqual(userSelectedDate) && predstavaTip.equals(tip.getValue())) {
	                Pom_filteredList.add(Pom_predstava);
	            }
	        }

	        colNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
	        colDatum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDatumIzvodjenja().toString()));
	        colVrijeme.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVrijemeIzvodjenja().toString()));
	        colCijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
	        //dugmad za info i rezervisi nakon filter
	        colAkcija1.setCellValueFactory(new PropertyValueFactory<>("info"));
	        colAkcija2.setCellValueFactory(new PropertyValueFactory<>("rezervisi"));
	      
	        if (Pom_filteredList.isEmpty()) {
	            PomocnaPredstava placeholderPomPredstava = new PomocnaPredstava("Nema Predstava na Repertoaru", "", "", LocalTime.of(0, 0), LocalDate.now(), 0.0);
	            tvPredstava.setItems(FXCollections.observableArrayList(placeholderPomPredstava));
	            AlertHelper.showInfoAlert("No Valid Predstava", "There are no valid predstava for the selected criteria.");
	        } else {
	            tvPredstava.setItems(Pom_filteredList);
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
