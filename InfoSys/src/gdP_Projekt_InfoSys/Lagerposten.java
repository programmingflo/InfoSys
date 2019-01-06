package gdP_Projekt_InfoSys;

class Lagerposten {
	private int lagerbestand;
	private double preis;
	private final Artikel artikel;	//Artikel auf den sich der Lagerposten bezieht sollte nicht mehr aenderbar sein nachdem der Lagerposten erstellt wurde
	
	public Lagerposten(Artikel artikel, int lagerbestand, double preis) {
		this.artikel = artikel;
		this.lagerbestand = lagerbestand;
		this.preis = preis;
	}
	
	public int getLagerbestand() {
		return lagerbestand;
	}
	
	public Artikel getArtikel() {
		return artikel;
	}
	
	public String getArtikelName() {
		return artikel.getArtikelName();
	}

	// Pruefung ob Lagerbestand - gewuenschte Differenz >= 0 wird in der Methode "bestellungAusfuehren" gemacht
	public void addToLagerbestand(int differenz) {
		lagerbestand += differenz;
	}
	
	public void setPreis(double preis) {
		this.preis = preis;
	}
	
	public double getPreis() {
		return preis;
	}
	/**
	 * Rueckgabe aktueller Stand des Lagerpostens
	 * Name + Bestand + Preis
	 */
	public String bestandLagerposten() {
		return ("Artikel: " + artikel.getArtikelName() + ", momentaner Bestand: " + lagerbestand + ", Preis: " + preis + "€.");
	}
	
}
