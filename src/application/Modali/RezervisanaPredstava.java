package application.Modali;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.scene.control.Button;

public class RezervisanaPredstava extends Predstava {
	
	private Avatar avatar;
	
	private Button otkazi;

	public RezervisanaPredstava(String naziv, String tip, String direktor, LocalTime vrijemeIzvodjenja,
			LocalDate datumIzvodjenja, double cijena,Avatar avatar) {
		super(naziv, tip, direktor, vrijemeIzvodjenja, datumIzvodjenja, cijena);
		// TODO Auto-generated constructor stub
		this.avatar=avatar;
		
		this.otkazi=new Button("Otkazi");
		this.otkazi.setOnAction(
				event->{
					//delete kveri i try catch konekcije
					System.out.println("Proba");
				}
				);
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
