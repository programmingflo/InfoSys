package gdP_Projekt_InfoSys;


import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Lagerverwaltung {
	/**
	 * To-Do:
	 * - Private "berechtigterMitarbeiter: Set<String> = new HashSet<>()" erstellen
	 * Key wird ein String
	 */
	//private static Set<String> berechtigteMitarbeiter = new HashSet<>(); 
	private static Set<String> berechtigteMitarbeiter;
	//private static Map<String, Lagerposten> mapLagerposten = new Map<String, Lagerposten>();
	private static Map<String, Lagerposten> mapLagerposten;
	//private static Map<String, Lagerposten> Lagerposten = new HashMap<>();
	private static Map<String, Lagerposten> listeLagerposten;
	public Lagerverwaltung() {
		berechtigteMitarbeiter = new HashSet<String>();
		mapLagerposten = new HashMap<String, Lagerposten>();
	}
	
	public void berechtigungErteilen(Mitarbeiter ma) {
		berechtigteMitarbeiter.add(ma.getName());
	}
	
	public void berechtigungZurueckziehen(Mitarbeiter ma) {
		// Mitarbeiter aus dem HashSet "berechtigter Mitarbeiter" entfernen
	}
	
	public void lagerbestandAusgeben() {
		// gesamten Lagerbestand ausgeben
	}
	
	public void wareneingangBuchen(Mitarbeiter ma, Artikel a, int anzahl, double preis) {
		// Wareneingang verbuchen mit
		// Mitarbeiter, Artikel, Anzahl, Preis
	}
	
	public Bestellbestaetigung bestellungAusfuehren(Mitarbeiter ma, List<Bestellposten> bestellung) {
		// Bestellbestaetigung zurueckgeben
	}
	
	
	
	
	
}
