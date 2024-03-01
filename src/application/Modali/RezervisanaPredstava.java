package application.Modali;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;

public class RezervisanaPredstava extends Predstava {
	
	private Avatar avatar;
	
	private Button otkazi;
	
//	private int idRezPred;

	public RezervisanaPredstava(String naziv, String tip, String direktor, LocalTime vrijemeIzvodjenja,
			LocalDate datumIzvodjenja, double cijena,Avatar avatar) {
		super(naziv, tip, direktor, vrijemeIzvodjenja, datumIzvodjenja, cijena);
		// TODO Auto-generated constructor stub
		this.avatar=avatar;
		
		this.otkazi=new Button("Otkazi");
//		this.otkazi.setOnAction(
//				event->{
//					//delete kveri i try catch konekcije
//					String url = "jdbc:mysql://localhost:3306/db";
//				    String username = "root";
//				    String dbPassword = "jbbov123456";
//				    List <Integer> lista = new ArrayList<>();
//					try {
//				    Connection connection=DriverManager.getConnection(url,username,dbPassword);
//					String query = "SELECT idRezervisanaPredstava from rezervisana_predstava where avatar_id=?";
//			           PreparedStatement statement = connection.prepareStatement(query);
//			           statement.setString(1," 1");
//			           ResultSet resultSet = statement.executeQuery();
//			           while (resultSet.next()) {
//			        	   lista.add(resultSet.getInt(1));
//			        	   break;
//			           }
//			           resultSet.close();
//			           statement.close();
//			           connection.close();
//			           for(int listaL:lista)
//			           System.out.println(listaL+"svaki poslednji");
//			           }
//					catch(Exception e) {
//						System.out.println("NEce da radi za svako dugme ubjedjen a ako proradi onda ovaj dio mora ici u kontroler");
//					}
//			           
//					System.out.println("Proba");
//				}
//				);
	}

	public Avatar getAvatar() {
		return avatar;
	}

	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}

	public Button getOtkazi() {
		return otkazi;
	}

	public void setOtkazi(Button otkazi) {
		this.otkazi = otkazi;
	}
	
	

}
