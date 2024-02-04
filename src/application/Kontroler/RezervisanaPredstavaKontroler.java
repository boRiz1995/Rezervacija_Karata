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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Modali.Avatar;
import application.Modali.PomocnaPredstava;
import application.Modali.RezervisanaPredstava;
import javafx.application.Platform;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RezervisanaPredstavaKontroler implements Initializable{
	
	@FXML
	private Button cancelButton;
	@FXML
	private TableView<RezervisanaPredstava> tvRezervacije;
	@FXML
	private TableColumn<RezervisanaPredstava,String> colNaziv;
	@FXML
	private TableColumn<RezervisanaPredstava,String> colDatum;
	@FXML
	private TableColumn<RezervisanaPredstava,String> colVrijeme;
	@FXML
	private TableColumn<RezervisanaPredstava,Double> colCijena;
	@FXML
	private TableColumn<RezervisanaPredstava,Button> colAkcija1;
	
	
	private Avatar avatar;
	
	 public void setAvatar(Avatar avatar) {
	        this.avatar = avatar;
	       
//	        System.out.println(avatar.getEmail()+2);
//	        System.out.println(hiddenLabel.getText()+3);
	        
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/db";
	    String username = "root";
	    String dbPassword = "jbbov123456";

	    Platform.runLater(()->{
	    	//populacija rezervacije table fx 
	    	ObservableList<RezervisanaPredstava>RezervisanaPredstavaList = FXCollections.observableArrayList();
	    	//populacija rezervacije table fx 
	    	List <Integer> lista = new ArrayList<>();
	    	int idavatar=0;
	    	try {
				Connection connection = DriverManager.getConnection(url, username, dbPassword);
				String query = "SELECT idAvatar from avatar where Email=?";
		           PreparedStatement statement = connection.prepareStatement(query);
		           statement.setString(1, avatar.getEmail());
		           ResultSet resultSet = statement.executeQuery();
		           while (resultSet.next()) {
		        	   idavatar = resultSet.getInt("idAvatar");
		           }
		           resultSet.close();
		           statement.close();
		           connection.close();
		           try {
		        	   /*
//select id_predstava,timestamp from rezervisana predstava where avatar_id=?
//		        	   statement2.setStrring(idavatar)
//		        	   ResultSet resultSet = statement2.executeQuery();
//		        	   while(resultset.next()){
//		        	   u neki list pakujem to i posle radim jos jedan kveri
 *  koji za svaki idpredstave kreira rezervisan predstava objekat i popunjava tablefx
 *  i onda zavrsiti sa delete kverijem unutar rezervisana predstava konstruktora
//		        	   }
		        */	  
		        	   
		        	   System.out.println(idavatar+" Proba da vidimo jel vrace idavatara sto bi trebao");
		        	    Connection connection2=DriverManager.getConnection(url,username,dbPassword);
		        	    String query2="SELECT idPredstava from rezervisana_predstava where avatar_id="+idavatar;
		        	    PreparedStatement statement2=connection2.prepareStatement(query2);
		        	    ResultSet resultSet1=statement2.executeQuery();
		        	    while(resultSet1.next()){
		        	    lista.add(resultSet1.getInt("idPredstava"));
		        	    System.out.println("mee");
		        	    }
		        	    resultSet1.close();
		           		statement2.close();
		           		connection2.close();
		           		System.out.println(lista.size()+" welicina nakon 2 querija druge konekcije koja je zavrsila");
		           		try {
		           		Connection connection3=DriverManager.getConnection(url,username,dbPassword);
		           		String query3="SELECT Naziv,tip,direktor,vrijemeIzvodjenja,datumIzvodjenja,Cijena FROM predstava where idPredstava="+lista.get(0);
		        	    PreparedStatement statement3=connection3.prepareStatement(query3);
		        	    ResultSet resultSet2=statement3.executeQuery();
//		        	    int j=0;
		        	    while (resultSet2.next()) {
        	   
		        	    	System.out.println("meep"+lista.get(0));
               
		        	    	String naziv = resultSet2.getString("Naziv");
               String tip=resultSet2.getString("tip");
               String direktor=resultSet2.getString("direktor");
               LocalTime vrijeme = resultSet2.getTime("vrijemeIzvodjenja").toLocalTime();
               LocalDate datum = resultSet2.getDate("datumIzvodjenja").toLocalDate();
               double cijena=resultSet2.getDouble("Cijena");

               RezervisanaPredstava RezPre= new RezervisanaPredstava(naziv,tip,direktor,vrijeme,datum,cijena,avatar);
              System.out.println(" pakovanje u listu "+RezPre.getNaziv());
//              RezPre.getOtkazi().setOnAction(event -> {
//              //to be delete query uppon clicking this 
//              }
               //takodje dio populacije table fx za rezervacije
               RezervisanaPredstavaList.add(RezPre);
		       //takodje dio populacije table fx za rezervacije
		        	     } 
		        	     
		        	     //zatvaranje konekcije 3
		        	   statement3.close();
		        	   connection3.close();
		        	     //zatvaranje konekcije 3
		        	     
		        	     }
		           		catch(Exception e){
		           		 System.out.println("izuzetak 3 je fail");
		           		 }
		           		
		        	    
		        	   
//		        	   System.out.println(idavatar);
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("izuzetak 2 je fail");
				}
//		           System.out.println(idavatar);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("izuzetak 1 fail");
				e.printStackTrace();
			}
	    	
	    	//populacija table fx
	    	
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
	        colAkcija1.setCellValueFactory(new PropertyValueFactory<>("otkazi"));
			
//	        for (RezervisanaPredstava rezPredstava : RezervisanaPredstavaList) {
//	            System.out.println(rezPredstava);
//	        }
//	        
	        tvRezervacije.setItems(RezervisanaPredstavaList);
	        //populacija table fx
			}
		);
	}
	
}
