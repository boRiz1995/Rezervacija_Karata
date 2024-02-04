package application.Modali;

import java.time.LocalDate;
import java.time.LocalTime;

import application.AlertHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class PomocnaPredstava extends Predstava{
	
	private Button info;
	private Button rezervisi;
	
	public PomocnaPredstava() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//dodan avatar u konstruktor
	public PomocnaPredstava(String naziv, String tip, String direktor, LocalTime vrijemeIzvodjenja,
			LocalDate datumIzvodjenja, double cijena) {
		super(naziv, tip, direktor, vrijemeIzvodjenja, datumIzvodjenja, cijena);
		// TODO Auto-generated constructor stub
		this.info=new Button("Info");
//		this.info.addEventHandler(null, null);
		this.rezervisi=new Button("Rezervisi");
//		this.rezervisi.addEventHandler(null, null);
		 // Setting event handlers for info and rezervisi buttons
        this.info.setOnAction(event -> {
//            System.out.println("Info button clicked!");
        	displayTotalInfo();
            // Perform actions specific to the info button click
        });
//        this.rezervisi.setOnAction(event -> {
//          System.out.println("reservisi button clicked!");
////      	displayTotalInfo();
//          // Perform actions specific to the info button click
//      });
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
	public void displayTotalInfo() {
        // Craft the total information about the performance
        String totalInfo = "Naziv: " + getNaziv() + "\n" +
                           "Tip: " + getTip() + "\n" +
                           "Direktor: " + getDirektor() + "\n" +
                           "Vrijeme izvodjenja: " + getVrijemeIzvodjenja() + "\n" +
                           "Datum izvodjenja: " + getDatumIzvodjenja() + "\n" +
                           "Cijena: " + getCijena();

        // Assuming you have an AlertHelper class with a showAlert method
        AlertHelper.showInfoAlert("Predstava Info",totalInfo); // Display the total information in an alert dialog
    }
	
	
	
//	private void createAndFillRezervisanaPredstava() {
//        // Create a RezervisanaPredstava object and fill parameters using available data
//        RezervisanaPredstava rezervisanaPredstava = new RezervisanaPredstava(
//            getNaziv(), getTip(), getDirektor(), getVrijemeIzvodjenja(), getDatumIzvodjenja(), getCijena(), avatar
//        );
	
//set on action overrriden
//	public void setActioninfo(EventHandler<ActionEvent> eventHandler) {
//       info.setOnAction(eventHandler);
//    }
//	public void setActionrezervisi(EventHandler<ActionEvent> eventHandler) {
//	       rezervisi.setOnAction(eventHandler);
//	    }
	//
}
