package application.Modali;

import java.time.LocalDate;
import java.time.LocalTime;

import application.AlertHelper;
import javafx.scene.control.Button;

public class PomocnaPredstava2 extends Predstava{

	private Button obrisi;
	
	public PomocnaPredstava2() {
		super();
	}
	
	public PomocnaPredstava2(String naziv, String tip, String direktor, LocalTime vrijemeIzvodjenja,
			LocalDate datumIzvodjenja, double cijena) {
		super(naziv, tip, direktor, vrijemeIzvodjenja, datumIzvodjenja, cijena);
		this.obrisi=new Button("Obrisi");
//		 this.obrisi.setOnAction(event -> {
//			 //to be delete kveri za predstaves
//			 AlertHelper.showSuccessAlert("USPEH", "USPESNO OBRISANO");
//		 });
	}

	public Button getObrisi() {
		return obrisi;
	}

	public void setObrisi(Button obrisi) {
		this.obrisi = obrisi;
	}
	
}
