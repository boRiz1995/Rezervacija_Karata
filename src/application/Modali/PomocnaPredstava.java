package application.Modali;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.scene.control.Button;

public class PomocnaPredstava extends Predstava{
	
	private Button info;
	private Button rezervisi;
	public PomocnaPredstava() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PomocnaPredstava(String naziv, String tip, String direktor, LocalTime vrijemeIzvodjenja,
			LocalDate datumIzvodjenja, double cijena) {
		super(naziv, tip, direktor, vrijemeIzvodjenja, datumIzvodjenja, cijena);
		// TODO Auto-generated constructor stub
		this.info=new Button("Info");
		this.rezervisi=new Button("Rezervisi");
	}
	public Button getInfo() {
		return info;
	}
	public void setInfo(Button info) {
		this.info = info;
	}
	public Button getRezervisi() {
		return rezervisi;
	}
	public void setRezervisi(Button rezervisi) {
		this.rezervisi = rezervisi;
	}

}
