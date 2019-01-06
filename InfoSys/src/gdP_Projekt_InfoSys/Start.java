package gdP_Projekt_InfoSys;

import java.util.ArrayList;
import java.util.List;

class Start {

	public static void main(String[] args) {
		Lagerverwaltung lw1 = new Lagerverwaltung();
		
		// 9 Mitarbeiter anlegen, jeder Mitarbeiter bekommt Berechtigung
		lw1.logTest("TESTTEST: Anlegen von 9 Mitarbeitern, alle bekommen die Berechtigung:");
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
		lw1.logTest("TESTTEST: Entziehen der Berechtigung fuer MAs 3, 4, 7 und 9:");
		lw1.berechtigungZurueckziehen(ma3);
		lw1.berechtigungZurueckziehen(ma4);
		lw1.berechtigungZurueckziehen(ma7);
		lw1.berechtigungZurueckziehen(ma9);
		
		// Wareneingang fuer fuenf neue Artikel buchen, Ausgabe Bestand vor und nach Anlegen der Artikel
		lw1.logTest("TESTTEST: Wareneingang fuer fuenf neue Artikel buchen; Ausgabe Bestand davor und danach:");
		lw1.lagerbestandAusgeben();
		Artikel a1 = new Artikel("0001", "Tasse 0,3 Liter", "Eine Keramiktasse mit 0,3 Liter Fassungsvermoegen");
		Artikel a2 = new Artikel("0002", "Saure Gurken Ogorki", "Ein Glas saurer Gurken, Marke Ogorki");
		Artikel a3 = new Artikel("0003", "Stilles Wasser 0,7 Liter", "Eine PET-Flasche mit stillem Wasser mit 0,7 Liter Fassungsvermoegen");
		Artikel a4 = new Artikel("0004", "Zahnpasta 150g", "Eine Tube Zahnpasta mit 150g Inhalt");
		Artikel a5 = new Artikel("0005", "Kugelschreiber", "Ein einfacher Kugelschreiber");
		lw1.wareneingangBuchen(ma1, a1, 1000, 4.95);
		lw1.wareneingangBuchen(ma2, a2, 3000, 2.44);
		lw1.wareneingangBuchen(ma1, a3, 5000, 0.39);
		lw1.wareneingangBuchen(ma5, a4, 500, 1.69);
		lw1.wareneingangBuchen(ma5, a5, 2500, 2.19);
		lw1.lagerbestandAusgeben();
		
		// Versuch einen Wareneingang mit Preisaenderung mit einem Mitarbeiter zu buchen, der nicht ueber die Berechtigung verfuegt
		lw1.logTest("TESTTEST: Wareneingang mit Preisaenderung mit einem Mitarbeiter versuchen zu buchen, der nicht ueber die Berechtigung verfuegt:");
		lw1.wareneingangBuchen(ma3, a2, 1000, 2.34);
		
		// Berechtigung fuer den Mitarbeiter wieder erteilen und erneuter Versuch der Buchung des Wareneingangs mit Preisaenderung
		lw1.logTest("TESTTEST: Berechtigung fuer Mitarbeiter wieder erteilen und selben Wareneingang erneut buchen:");
		lw1.berechtigungErteilen(ma3);
		lw1.wareneingangBuchen(ma3, a2, 1000, 2.34);
		
		// Versuch mehrere Wareneingaenge ohne Preisaenderung und eines neuen Artikels zu buchen
		lw1.logTest("TESTTEST: Mehrere Wareneingaenge ohne Preisanderung und einen neuen Artikel buchen:");
		lw1.wareneingangBuchen(ma1, a2, 1250, 2.44);
		lw1.wareneingangBuchen(ma3, a3, 254, 0.39);
		lw1.wareneingangBuchen(ma2, a3, 999, 0.39);
		Artikel a6 = new Artikel("0006", "Schubfach Typ B", "Ein Schubfach fuer einen Schrank, Masse 40x40x40mm");
		lw1.wareneingangBuchen(ma5, a6, 1500, 7.89);
		
		// Ausgabe Lagerbestand
		lw1.logTest("TESTTEST: Ausgabe kompletter Lagebestand:");
		lw1.lagerbestandAusgeben();
		
		// Erstellung und Durchfuehren einer Bestellung, fuer die genuegend Artikel vorhanden sein sollten, danach erneute Ausgabe Lagerbestand
		lw1.logTest("TESTTEST: Durchfuehrung einer Bestellung, fuer die genuegend Artikel vorhanden sein sollten, danach erneute Ausgabe Lagebestand:");
		List<Bestellposten> bestellung1 = new ArrayList<>();
		bestellung1.add(new Bestellposten(a1.getID(), 250));
		bestellung1.add(new Bestellposten(a2.getID(), 250));
		bestellung1.add(new Bestellposten(a3.getID(), 250));
		bestellung1.add(new Bestellposten(a4.getID(), 250));
		bestellung1.add(new Bestellposten(a5.getID(), 250));
		Bestellbestaetigung bestellbestaetigung1 = lw1.bestellungAusfuehren(ma1, bestellung1);
		lw1.lagerbestandAusgeben();
		
		// Erstellung und Versuch der Durchfuehrung einer Bestellung, fuer die nicht genuegend Atrikel vorhanden sein sollten
		lw1.logTest("TESTTEST: Durchfuehrung einer Bestellung, fuer die nicht genuegend Artikel vorhanden sein sollten:");
		List<Bestellposten> bestellung2 = new ArrayList<>();
		bestellung2.add(new Bestellposten(a1.getID(), 1000000));
		Bestellbestaetigung bestellbestaetigung2 = lw1.bestellungAusfuehren(ma1, bestellung2);
		
		// Erstellung und Versuch der Durchfuehrung einer Bestellung, deren gewuenschter Artikel gar nicht existiert
		lw1.logTest("TESTTEST: Durchfuehrung einer Bestellung, deren gewuenschter Artikel gar nicht exisitiert:");
		List<Bestellposten> bestellung3 = new ArrayList<>();
		bestellung3.add(new Bestellposten("abzwetj2", 1000));
		Bestellbestaetigung bestellbestaetigung3 = lw1.bestellungAusfuehren(ma1, bestellung3);


	}

}
