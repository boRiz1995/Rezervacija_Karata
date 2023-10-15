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

public class PocetnaKontroler implements Initializable{
	
	@FXML
	private Button loginButton;
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
	
	public void switchScene()throws IOException {
		Parent root=FXMLLoader.load(getClass().getResource("/application/View/LogIN.fxml"));
		Stage stage=(Stage)loginButton.getScene().getWindow();
		Scene scene=new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	//chat metoda
//	public void filterPredstava() {
//	    String url = "jdbc:mysql://localhost:3306/db";
//	    String username = "root";
//	    String dbPassword = "jbbov123456";
//
//	    ObservableList<Predstava> predstavaList = FXCollections.observableArrayList();
//	    try {
//	        // Connect to the database
//	        Connection connection = DriverManager.getConnection(url, username, dbPassword);
//	        // Execute a query to fetch data from the database
//	        String query = "SELECT Naziv,tip,direktor,vrijemeIzvodjenja,datumIzvodjenja,Cijena FROM predstava";
//	        PreparedStatement statement = connection.prepareStatement(query);
//	        ResultSet resultSet = statement.executeQuery();
//	        // Populate the list with data from the database
//	        while (resultSet.next()) {
//	            String naziv = resultSet.getString("Naziv");
//	            String tip = resultSet.getString("tip");
//	            String direktor = resultSet.getString("direktor");
//	            LocalTime vrijeme = resultSet.getTime("vrijemeIzvodjenja").toLocalTime();
//	            LocalDate datum = resultSet.getDate("datumIzvodjenja").toLocalDate();
//	            double cijena = resultSet.getDouble("Cijena");
//
//	            Predstava predstava = new Predstava(naziv, tip, direktor, vrijeme, datum, cijena);
//	            predstavaList.add(predstava);
//	        }
//
//	        // Close the database connection
//	        resultSet.close();
//	        statement.close();
//	        connection.close();
//
//	        ObservableList<Predstava> filteredList = FXCollections.observableArrayList();
//
//	        if (datumIzvedbe.getValue().isBefore(LocalDate.now())) {
//	            AlertHelper.showErrorAlert("Invalid Date", "Invalid Date please try again");
//	        } else {
//	            if (tip.getValue().equals("Default")) {
//	                // Replicate the behavior from showPredstava
//	                for (Predstava predstava : predstavaList) {
//	                    LocalDate predstavaDate = predstava.getDatumIzvodjenja();
//	                    LocalTime predstavaTime = predstava.getVrijemeIzvodjenja();
//
//	                    if (predstavaDate.isBefore(LocalDate.now()) ||
//	                            (predstavaDate.equals(LocalDate.now()) && predstavaTime.isBefore(LocalTime.now()))) {
//	                        filteredList.remove(predstava);
//	                    } else {
//	                        filteredList.add(predstava);
//	                    }
//	                }
//	            } else {
//	                // Filter based on date, time, and type
//	                for (Predstava predstava : predstavaList) {
//	                    LocalDate predstavaDate = predstava.getDatumIzvodjenja();
//	                    LocalTime predstavaTime = predstava.getVrijemeIzvodjenja();
//	                    String predstavaTip = predstava.getTip();
//
//	                    if (predstavaDate.equals(datumIzvedbe.getValue()) &&
////	                            predstavaTime.isAfter(LocalTime.now()) &&
//	                            predstavaTip.equals(tip.getValue())) {
//	                        filteredList.add(predstava);
//	                    }
//	                }
//	            }
//	        }
//
//	        colNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
//	        colDatum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDatumIzvodjenja().toString()));
//	        colVrijeme.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVrijemeIzvodjenja().toString()));
//	        colCijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
//
//	        if (filteredList.isEmpty()) {
//	            Predstava placeholderPredstava = new Predstava("Nema Predstava na Repertoaru", "", "", LocalTime.of(0, 0), LocalDate.now(), 0.0);
//	            tvPredstava.setItems(FXCollections.observableArrayList(placeholderPredstava));
//	        } else {
//	            tvPredstava.setItems(filteredList);
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	}
	//chatgore
	

//	public void filterPredstava() {
//		
//		 String url = "jdbc:mysql://localhost:3306/db";
//		    String username = "root";
//		    String dbPassword = "jbbov123456";
//
//		    ObservableList<Predstava>predstavaList = FXCollections.observableArrayList();
//		try {
//         // Connect to the database
//         Connection connection = DriverManager.getConnection(url, username, dbPassword);
//         // Execute a query to fetch data from the database
//         String query = "SELECT Naziv,tip,direktor,vrijemeIzvodjenja,datumIzvodjenja,Cijena FROM predstava";
//         PreparedStatement statement = connection.prepareStatement(query);
//         ResultSet resultSet = statement.executeQuery();
//         // Populate the list with data from the database
//         while (resultSet.next()) {
//             String naziv = resultSet.getString("Naziv");
//             String tip=resultSet.getString("tip");
//             String direktor=resultSet.getString("direktor");
//             LocalTime vrijeme = resultSet.getTime("vrijemeIzvodjenja").toLocalTime();
//             LocalDate datum = resultSet.getDate("datumIzvodjenja").toLocalDate();
//             double cijena=resultSet.getDouble("Cijena");
//
//             Predstava predstava = new Predstava(naziv,tip,direktor,vrijeme,datum,cijena);
//             predstavaList.add(predstava);
//             
//         }
//
////          Close the database connection
//         resultSet.close();
//         statement.close();
//         connection.close();
//
//             ObservableList<Predstava> filteredList = FXCollections.observableArrayList();
//            //novo
//             if (datumIzvedbe.getValue().isBefore(LocalDate.now())) {
//            	 AlertHelper.showErrorAlert("Invalid Date", "Invalid Date please try again");
//             }
//             else {
//            //novo
//             for (Predstava predstava : predstavaList) {
//                 LocalDate predstavaDate = predstava.getDatumIzvodjenja();//datum predstave iz DB
//                 LocalTime predstavaTime = predstava.getVrijemeIzvodjenja();//vreme predstave iz db
//                 String predstavaTip=predstava.getTip();//tip predstave iz DB
//                 //filterd stwari iff -_-
////                 if (predstavaDate.equals(LocalDate.now()) && predstavaTime.isAfter(LocalTime.now()) && tip.getValue().equals("Default")) {
////                     filteredList.add(predstava);
////                 }else if(predstavaDate.equals(datumIzvedbe.getValue())&& tip.getValue().equals("Default")){
////                 	filteredList.add(predstava);
////             }
//////                 else if(predstavaDate.equals(datumIzvedbe.getValue())&&
//////                		 predstavaTip.equals(tip.getValue()) ) {
//////                	 filteredList.add(predstava);
//////                 }
//////                 else break;
////                 
////                 else {filteredList.remove(predstava);}
////                 //dio gore gdje treba ispitati sve za filtered stvari
////                 
////                 if(predstavaDate.equals(datumIzvedbe.getValue())&&
////                		 predstavaTip.equals(tip.getValue()) ) {
////                	 filteredList.add(predstava);
////                 }
////                 else {filteredList.remove(predstava);}
//             }
//             }
//             colNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
//             colDatum.setCellValueFactory(cellData -> {
//                 LocalDate datum = cellData.getValue().getDatumIzvodjenja();
//                 return new SimpleStringProperty(datum.toString());
//             });
//             colVrijeme.setCellValueFactory(cellData -> {
//                 LocalTime vrijeme = cellData.getValue().getVrijemeIzvodjenja();
//                 return new SimpleStringProperty(vrijeme.toString());
//             });
//             colCijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));
//             
//
//             if (filteredList.isEmpty()) {
//                 Predstava placeholderPredstava = new Predstava("Nema Predstava na Repertoaru", "", "", LocalTime.of(0, 0), LocalDate.now(), 0.0);
//                 tvPredstava.setItems(FXCollections.observableArrayList(placeholderPredstava));
//             } else {
//             	tvPredstava.setItems(filteredList);
////                 tvPredstava.setItems(predstavaList);
//             }
//     } catch (SQLException e) {
//         e.printStackTrace();
//     }
//		
//	}
	public void filterPredstava() {
	    String url = "jdbc:mysql://localhost:3306/db";
	    String username = "root";
	    String dbPassword = "jbbov123456";

	    ObservableList<Predstava> predstavaList = FXCollections.observableArrayList();
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
	            String tip = resultSet.getString("tip");
	            String direktor = resultSet.getString("direktor");
	            LocalTime vrijeme = resultSet.getTime("vrijemeIzvodjenja").toLocalTime();
	            LocalDate datum = resultSet.getDate("datumIzvodjenja").toLocalDate();
	            double cijena = resultSet.getDouble("Cijena");

	            Predstava predstava = new Predstava(naziv, tip, direktor, vrijeme, datum, cijena);
	            predstavaList.add(predstava);
	        }

	        // Close the database connection
	        resultSet.close();
	        statement.close();
	        connection.close();

	        ObservableList<Predstava> filteredList = FXCollections.observableArrayList();

	        LocalDate userSelectedDate = datumIzvedbe.getValue();
	        LocalTime currentTime = LocalTime.now();

	        for (Predstava predstava : predstavaList) {
	            LocalDate predstavaDate = predstava.getDatumIzvodjenja();
	            LocalTime predstavaTime = predstava.getVrijemeIzvodjenja();
	            String predstavaTip = predstava.getTip();

	            if (userSelectedDate.isEqual(LocalDate.now()) && predstavaDate.isEqual(LocalDate.now()) && predstavaTime.isAfter(currentTime) && tip.getValue().equals("Default")) {
	                filteredList.add(predstava);
	            } else if (userSelectedDate.isAfter(LocalDate.now()) && predstavaDate.isEqual(userSelectedDate) && tip.getValue().equals("Default")) {
	                filteredList.add(predstava);
	            } else if (predstavaDate.isEqual(userSelectedDate) && predstavaTip.equals(tip.getValue())) {
	                filteredList.add(predstava);
	            }
	        }

	        colNaziv.setCellValueFactory(new PropertyValueFactory<>("naziv"));
	        colDatum.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDatumIzvodjenja().toString()));
	        colVrijeme.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVrijemeIzvodjenja().toString()));
	        colCijena.setCellValueFactory(new PropertyValueFactory<>("cijena"));

	        if (filteredList.isEmpty()) {
	            Predstava placeholderPredstava = new Predstava("Nema Predstava na Repertoaru", "", "", LocalTime.of(0, 0), LocalDate.now(), 0.0);
	            tvPredstava.setItems(FXCollections.observableArrayList(placeholderPredstava));
	            AlertHelper.showInfoAlert("No Valid Predstava", "There are no valid predstava for the selected criteria.");
	        } else {
	            tvPredstava.setItems(filteredList);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		datumIzvedbe.setValue(LocalDate.now());
		tip.getItems().addAll("Default","Mjuzikl", "Drama", "Tragedija","Komedija","Avangarda","Dječija");
		tip.getSelectionModel().selectFirst();
		showPredstava();
//		scheduleShowPredstava();
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

//             Close the database connection
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
}
