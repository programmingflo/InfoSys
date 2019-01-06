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
		//log = new Log("C:\\Users\\Apfelkuchenbemme\\Desktop\\Verbuchung Lagerverwaltungsgelumpe20181218_124907.txt","|newLog|","|tss|"); //<= So muss der Pfad aussehen damits aufm Desktop landet
		log = new Log("log.txt","|newLog|","|tss|");	//<= Hier landet der Spass in dem Ordner von Eclipse selber
	}
	/**
	 * Mitarbeiter zu dem HashSet "berechtigter Mitarbeiter" hinzufuegen
	 * @param ma hinzuzufuegender Mitarbeiter
	 */
	public void berechtigungErteilen(Mitarbeiter ma) {
		berechtigteMitarbeiter.add(ma.getName());
		log.write("Berechtigung erteilt für: " + ma.getName() + ", ID: " + ma.getID() + ".");
	}
	
	/**
	 * Mitarbeiter aus dem HashSet "berechtigter Mitarbeiter" entfernen
	 * @param ma zu entfernender Mitarbeiter
	 */
	public void berechtigungZurueckziehen(Mitarbeiter ma) {
		// Nur Berechtigung entziehen falls Mitarbeiter auch wirklich Berechtigung hat
		if(berechtigteMitarbeiter.contains(ma.getName())) {
			berechtigteMitarbeiter.remove(ma);
			log.write("Berechtigung zurückgezogen für: " + ma.getName() + ", ID: " + ma.getID() + ".");
		}
	}
	
	public void lagerbestandAusgeben() {
		if (mapLagerposten.isEmpty()) {
			log.write("Es wurden noch keine Lagerposten erstellt.");
		} else  {
			//for(Map.Entry<String, Lagerposten> lp: mapLagerposten.entrySet()) {
			for(Lagerposten lp: mapLagerposten.values())
				log.write(lp.bestandLagerposten());
			}
	}

	
	/**
	 * Wareneingang verbuchen mit Mitarbeiter, Artikel, Anzahl, Preis
	 * @param ma Mitarbeiter
	 * @param a Artikel
	 * @param anzahl Anzahl
	 * @param preis Preis
	 */
	public void wareneingangBuchen(Mitarbeiter ma, Artikel a, int anzahl, double preis) {
		// Berechtigung pruefen
		if(berechtigteMitarbeiter.contains(ma)) {
			// Pruefen ob Artikel bereits Lagerposten besitzt
			boolean lpExists = false;
			for(Lagerposten lp: mapLagerposten.values()) {
				if(lp.getArtikel().equals(a)) {
					lpExists = true;
					break;
				}
			}
			// Falls Artikel noch keinen Lagerposten besitzt, Lagerposten erstellen
			if (!lpExists) {
				log.write("Jetzt müsste ein neuer Lagerposten erstellt werden.");
				/**
				 * Problem: Was ist der String als Key für die Map überhaupt? Isses der Name?
				 */
				//mapLagerposten.put(key, value)
			}
			
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
