package gdP_Projekt_InfoSys;

import java.util.ArrayList;
import java.util.List;

class Start {

	public static void main(String[] args) {
		Lagerverwaltung lw1 = new Lagerverwaltung();
		
		// 9 Mitarbeiter anlegen, jeder Mitarbeiter bekommt Berechtigung
		Mitarbeiter ma1 = new Mitarbeiter("1", "Max Mustermann");
		lw1.berechtigungErteilen(ma1);
		Mitarbeiter ma2 = new Mitarbeiter("2", "Peter Pan");
		lw1.berechtigungErteilen(ma2);
		Mitarbeiter ma3 = new Mitarbeiter("3", "Karsten Kohl");
		lw1.berechtigungErteilen(ma3);
		Mitarbeiter ma4 = new Mitarbeiter("4", "Alina Alba");
		lw1.berechtigungErteilen(ma4);
		Mitarbeiter ma5 = new Mitarbeiter("5", "Bertram Bertold");
		lw1.berechtigungErteilen(ma5);
		Mitarbeiter ma6 = new Mitarbeiter("6", "Christina Christlich");
		lw1.berechtigungErteilen(ma6);
		Mitarbeiter ma7 = new Mitarbeiter("7", "Erhardt Ehrlich");
		lw1.berechtigungErteilen(ma7);
		Mitarbeiter ma8 = new Mitarbeiter("8", "Saskia Suske");
		lw1.berechtigungErteilen(ma8);
		Mitarbeiter ma9 = new Mitarbeiter("9", "Tatajana Tesko");
		lw1.berechtigungErteilen(ma9);
		
		// Mitarbeiter 3, 4, 7 und 9 wird Berechtigung wieder entzogen
		lw1.berechtigungZurueckziehen(ma3);
		lw1.berechtigungZurueckziehen(ma4);
		lw1.berechtigungZurueckziehen(ma7);
		lw1.berechtigungZurueckziehen(ma9);
		
		// Wareneingang fuer fuenf neue Artikel buchen
		Artikel a1 = new Artikel("0001", "Tasse 0,3 Liter", "Eine Keramiktasse mit 0,3 Liter Fassungsvermögen");
		Artikel a2 = new Artikel("0002", "Saure Gurken Ogorki", "Ein Glas saurer Gurken, Marke Ogorki");
		Artikel a3 = new Artikel("0003", "Stilles Wasser 0,7 Liter", "Eine PET-Flasche mit stillem Wasser mit 0,7 Liter Fassungsvermögen");
		Artikel a4 = new Artikel("0004", "Zahnpasta 150g", "Eine Tube Zahnpasta mit 150g Inhalt");
		Artikel a5 = new Artikel("0005", "Kugelschreiber", "Ein einfacher Kugelschreiber");
		lw1.wareneingangBuchen(ma1, a1, 1000, 4.95);
		lw1.wareneingangBuchen(ma2, a2, 3000, 2.44);
		lw1.wareneingangBuchen(ma1, a3, 5000, 0.39);
		lw1.wareneingangBuchen(ma5, a4, 500, 1.69);
		lw1.wareneingangBuchen(ma5, a5, 2500, 2.19);
		
		// Versuch einen Wareneingang mit einem Mitarbeiter zu buchen, der nicht ueber die Berechtigung verfuegt
		lw1.wareneingangBuchen(ma3, a2, 1000, 2.34);
		
		// Berechtigung fuer den Mitarbeiter wieder erteilen und erneuter Versuch der Buchung des Wareneingangs
		lw1.berechtigungErteilen(ma3);
		lw1.wareneingangBuchen(ma3, a2, 1000, 2.34);
		
		// Ausgabe Lagerbestand
		lw1.lagerbestandAusgeben();
		
		// Erstellen und Durchfuehren einer Bestellung, fuer die genuegend Artikel vorhanden sein sollten, danach erneute Ausgabe Lagerbestand
		List<Bestellposten> bestellung1 = new ArrayList<>();
		bestellung1.add(new Bestellposten("0001", 250));
		bestellung1.add(new Bestellposten("0002", 250));
		bestellung1.add(new Bestellposten("0003", 250));
		bestellung1.add(new Bestellposten("0004", 250));
		bestellung1.add(new Bestellposten("0005", 250));
		Bestellbestaetigung bestellbestaetigung1 = lw1.bestellungAusfuehren(ma1, bestellung1);
		/**
		 * TO-DO:
		 * Irgendwie hinkriegen dass log.write auch hier funzt
		 * ==> Es sollte moeglich sein die Bestellbestätigung ausgeben zu könnnen
		 * 
		 */
		lw1.lagerbestandAusgeben();
		
		// Erstellen und Versuch der Durchfuehrung einer Bestellung, fuer die nicht genuegend Atrikel vorhanden sein sollten
		List<Bestellposten> bestellung2 = new ArrayList<>();
		bestellung2.add(new Bestellposten("0001", 1000000));
		lw1.bestellungAusfuehren(ma1, bestellung2);
		




	}

}
