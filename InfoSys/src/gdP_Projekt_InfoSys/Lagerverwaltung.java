package gdP_Projekt_InfoSys;


import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Lagerverwaltung {
	private static Set<String> berechtigteMitarbeiter;
	private static Map<String, Lagerposten> mapLagerposten;
	private static Log log;
	
	public Lagerverwaltung() {
		berechtigteMitarbeiter = new HashSet<String>();
		mapLagerposten = new HashMap<String, Lagerposten>();
		log = new Log("C:\\Users\\Florian Mansfeld\\Desktop\\log.txt","|newLog|","|tss|");
	}
	
	public void berechtigungErteilen(Mitarbeiter ma) {
		//berechtigteMitarbeiter.add(ma.getName());
		log.write("Berechtigung erteilt für: " + ma);
	}
	
	/**
	 * Mitarbeiter aus dem HashSet "berechtigter Mitarbeiter" entfernen
	 * @param ma zu entfernender Mitarbeiter
	 */
	public void berechtigungZurueckziehen(Mitarbeiter ma) {
		berechtigteMitarbeiter.remove(ma);
	}
	
	public void lagerbestandAusgeben() {
		// gesamten Lagerbestand ausgeben
	}
	
	/**
	 * Wareneingang verbuchen mit Mitarbeiter, Artikel, Anzahl, Preis
	 * @param ma Mitarbeiter
	 * @param a Artikel
	 * @param anzahl Anzahl
	 * @param preis Preis
	 */
	public void wareneingangBuchen(Mitarbeiter ma, Artikel a, int anzahl, double preis) {
		if(berechtigteMitarbeiter.contains(ma)) {
			Lagerposten lagerposten = mapLagerposten.get(a.getId());
			lagerposten.addToLagerbestand(anzahl);
			lagerposten.setPreis(preis);
		}
	}
	
	/**
	 * die Bestellung eines Kunden ausführen
	 * @param ma ausführender Mitarbeiter
	 * @param bestellungen Liste von Bestellposten
	 * @return Bestellbestätigung, nur true, wenn alle Artikel verfügbar
	 */
	public Bestellbestaetigung bestellungAusfuehren(Mitarbeiter ma, List<Bestellposten> bestellungen) {
		Bestellbestaetigung output;
		double gesamtpreis = 0;
		if(berechtigteMitarbeiter.contains(ma)) {
			//Überprüfung, ob alle vorhanden
			boolean everythingInStock = true;
			for(Bestellposten bp : bestellungen) {
				Lagerposten lagerposten = mapLagerposten.get(bp.getArtikelID());
				if(lagerposten.getLagerbestand() < bp.getAnzahl()) {
					everythingInStock = false;
				}
			}
			
			//Ausführung
			if(everythingInStock) {
				for(Bestellposten bp : bestellungen) {
					Lagerposten lagerposten = mapLagerposten.get(bp.getArtikelID());
					lagerposten.addToLagerbestand(-bp.getAnzahl());
					gesamtpreis += bp.getAnzahl() * lagerposten.getPreis();
				}
				
				output = new Bestellbestaetigung(true, gesamtpreis);
			}else {
				output = new Bestellbestaetigung(false, gesamtpreis);
			}
		}else {
			output = new Bestellbestaetigung(false, gesamtpreis);
		}
		return output;
	}
	
	
	
	
	
}
