package gdP_Projekt_InfoSys;

class Lagerposten {
	private int lagerbestand;
	private double preis;
	private final Artikel artikel;	// Artikel auf den sich der Lagerposten bezieht sollte nicht mehr aenderbar sein nachdem der Lagerposten erstellt wurde
	
	public Lagerposten(Artikel artikel, int lagerbestand, double preis) {
		this.artikel = artikel;
		this.lagerbestand = lagerbestand;
		this.preis = preis;
	}
	
	public int getLagerbestand() {
		return lagerbestand;
	}
	
	// Pruefung ob Lagerbestand - gewuenschte Differenz >= 0 ist wird nicht hier gemacht
	public void addToLagerbestand(int differenz) {
		lagerbestand += differenz;
	}
	
	public void setPreis(double preis) {
		this.preis = preis;
	}
	
	public double getPreis() {
		return preis;
	}
	
}
