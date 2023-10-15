package application.Modali;

import java.time.LocalDate;
import java.time.LocalTime;

public class RezervisanaPredstava extends Predstava {
	
	private Avatar avatar;

	public RezervisanaPredstava(String naziv, String tip, String direktor, LocalTime vrijemeIzvodjenja,
			LocalDate datumIzvodjenja, double cijena,Avatar avatar) {
		super(naziv, tip, direktor, vrijemeIzvodjenja, datumIzvodjenja, cijena);
		// TODO Auto-generated constructor stub
		this.avatar=avatar;
	}

}
