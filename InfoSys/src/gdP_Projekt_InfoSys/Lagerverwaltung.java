package gdP_Projekt_InfoSys;

import java.util.List;
import java.util.Map;
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
		log = new Log("log.txt","|newLog|","|tss|");
	}
	
	/**
	 * Mitarbeiter zu dem HashSet "berechtigter Mitarbeiter" hinzufuegen
	 * 
	 * @param ma = hinzuzufuegender Mitarbeiter
	 */
	public void berechtigungErteilen(Mitarbeiter ma) {
		berechtigteMitarbeiter.add(ma.getID());
		log.write("Berechtigung erteilt fuer: " + ma.getName() + ", ID: " + ma.getID() + ".");
	}
	
	/**
	 * Mitarbeiter aus dem HashSet "berechtigter Mitarbeiter" entfernen
	 * 
	 * @param ma = zu entfernender Mitarbeiter
	 */
	public void berechtigungZurueckziehen(Mitarbeiter ma) {
		// Nur Berechtigung entziehen falls Mitarbeiter auch wirklich Berechtigung hat
		if(berechtigteMitarbeiter.contains(ma.getID())) {
			berechtigteMitarbeiter.remove(ma.getID());
			log.write("Berechtigung zurueckgezogen fuer: " + ma.getName() + ", ID: " + ma.getID() + ".");
		}
	}
	
	public void lagerbestandAusgeben() {
		if (mapLagerposten.isEmpty()) {
			log.write("Es wurden noch keine Lagerposten erstellt.");
		} else  {
			for(Lagerposten lp: mapLagerposten.values())
				log.write(lp.bestandLagerposten());
		}
	}

	
	/**
	 * Wareneingang verbuchen mit Mitarbeiter, Artikel, Anzahl, Preis
	 * 
	 * @param ma = ausfuehrender Mitarbeiter
	 * @param a = Artikel
	 * @param anzahl = Anzahl der hinzuzufuegenden Artikel
	 * @param preis = Preis, ueberschreibt bisherigen Preis
	 */
	public void wareneingangBuchen(Mitarbeiter ma, Artikel a, int anzahl, double preis) {
		// Berechtigung pruefen
		if(berechtigteMitarbeiter.contains(ma.getID())) {
			// Falls fuer Artikel bereits ein Lagerposten besteht, wird die Anzahl und der Preis aktualisiert
			// Falls fuer Artikel noch kein Lagerposten besteht, wird ein neuer angelegt
			if(mapLagerposten.get(a.getID()) != null) {
				Lagerposten lagerposten = mapLagerposten.get(a.getID());
				lagerposten.addToLagerbestand(anzahl);
				lagerposten.setPreis(preis);
				log.write("Mitarbeiter: " + ma.getName() + ", ID: " + ma.getID() + " hat " + anzahl + " Artikel von: " + a.getArtikelName() + " mit dem Preis: " + preis + "€ zum Lager hinzugefuegt.");
			} else {
				Lagerposten lagerposten = new Lagerposten(a, anzahl, preis);
				mapLagerposten.put(a.getID(), lagerposten);
				log.write("Mitarbeiter: " + ma.getName() + ", ID: " + ma.getID() + " hat " + anzahl + " Artikel von: " + a.getArtikelName() + " mit dem Preis: " + preis + "€ als neuen Lagerposten zum Lager hinzugefuegt.");
			}
		} else {
			log.write("Mitarbeiter: " + ma.getName() + ", ID: " + ma.getID() + " hat versucht " + anzahl + " Stueck von Artikel: " + a.getArtikelName() + " mit dem Preis: " + preis + "€ zum Lager hinzuzufuegen, verfuegt jedoch nicht ueber die noetige Berechtigung.");
		}
	}
	
	/**
	 * die Bestellung eines Kunden ausfuehren
	 * 
	 * @param ma = ausfuehrender Mitarbeiter
	 * @param bestellungen = Liste von Bestellposten
	 * @return Bestellbestaetigung = nur true, wenn alle Artikel verfuegbar
	 */
	public Bestellbestaetigung bestellungAusfuehren(Mitarbeiter ma, List<Bestellposten> bestellungen) {
		Bestellbestaetigung output;
		double gesamtpreis = 0;
		if(berechtigteMitarbeiter.contains(ma.getID())) {
			// Ueberpruefung, ob alle gewuenschten Artikel vorhanden
			boolean everythingInStock = true;
			for(Bestellposten bp : bestellungen) {
				Lagerposten lagerposten = mapLagerposten.get(bp.getArtikelID());
				// Lagerposten nicht vorhanden = Artikel ueberhaupt nicht in Lager => automatisch nicht moeglich
				if (lagerposten != null) {
					if(lagerposten.getLagerbestand() < bp.getAnzahl()) {
						everythingInStock = false;
					}
				} else {
					output = new Bestellbestaetigung(false, gesamtpreis);
					log.write("Mitarbeiter: " + ma.getName() + ", ID: " + ma.getID() + " hat versucht die Bestellung durchzufuehren, doch mindestens ein gewuenschter Artikel ist garnicht im Lager angelegt.");					
					return output;
				}
			}
			
			// Ausfuehrung
			if(everythingInStock) {
				for(Bestellposten bp : bestellungen) {
					Lagerposten lagerposten = mapLagerposten.get(bp.getArtikelID());
					lagerposten.addToLagerbestand(-bp.getAnzahl());
					gesamtpreis += (bp.getAnzahl() * lagerposten.getPreis());
				}
				output = new Bestellbestaetigung(true, gesamtpreis);
				log.write("Mitarbeiter: " + ma.getName() + ", ID: " + ma.getID() + " hat die Bestellung erfolgreich durchgefuehrt.");
			}else {
				output = new Bestellbestaetigung(false, gesamtpreis);
				log.write("Mitarbeiter: " + ma.getName() + ", ID: " + ma.getID() + " hat versucht die Bestellung durchzufuehren, doch mindestens ein gewuenschter Artikel ist nicht in der benoetigten Menge im Lager vorhanden.");
			}
		}else {
			output = new Bestellbestaetigung(false, gesamtpreis);
			log.write("Mitarbeiter: " + ma.getName() + ", ID: " + ma.getID() + " hat versucht die Bestellung durchzufuehren, verfuegt jedoch nicht ueber die noetige Berechtigung.");
		}
		return output;
	}
	/**
	 * Schreibt einen gewuenschten String in den log
	 * 
	 * @param nachricht = zu schreibender String
	 */
	public void logTest(String nachricht) {
		log.write(nachricht);
	}


}
