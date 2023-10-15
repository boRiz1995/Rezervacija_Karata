package application.Modali;

import java.time.LocalDate;
import java.time.LocalTime;

public class Predstava {
	
		private String naziv;
	    private String tip;
	    private String direktor;
	    private LocalTime vrijemeIzvodjenja;
	    private LocalDate datumIzvodjenja;
	    private double cijena;
		
		
		public Predstava() {}
		
		public Predstava(String naziv, String tip, String direktor, LocalTime vrijemeIzvodjenja,
				LocalDate datumIzvodjenja, double cijena) {
			this.naziv = naziv;
			this.tip = tip;
			this.direktor = direktor;
			this.vrijemeIzvodjenja = vrijemeIzvodjenja;
			this.datumIzvodjenja = datumIzvodjenja;
			this.cijena = cijena;
		}
	    
	    // Constructor, getters, and setters
	    public String getNaziv() {
			return naziv;
		}
		public void setNaziv(String naziv) {
			this.naziv = naziv;
		}
		public String getTip() {
			return tip;
		}
		public void setTip(String tip) {
			this.tip = tip;
		}
		public String getDirektor() {
			return direktor;
		}
		public void setDirektor(String direktor) {
			this.direktor = direktor;
		}
		public LocalTime getVrijemeIzvodjenja() {
			return vrijemeIzvodjenja;
		}
		public void setVrijemeIzvodjenja(LocalTime vrijemeIzvodjenja) {
			this.vrijemeIzvodjenja = vrijemeIzvodjenja;
		}
		public LocalDate getDatumIzvodjenja() {
			return datumIzvodjenja;
		}
		public void setDatumIzvodjenja(LocalDate datumIzvodjenja) {
			this.datumIzvodjenja = datumIzvodjenja;
		}
		public double getCijena() {
			return cijena;
		}
		public void setCijena(double cijena) {
			this.cijena = cijena;
		}
	    // Other methods
}
