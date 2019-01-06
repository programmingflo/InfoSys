package gdP_Projekt_InfoSys;

class Bestellbestaetigung {
	private boolean ausgefuehrt;
	private double gesamtpreis;
	
	public Bestellbestaetigung(boolean ausgefuehrt, double gesamtpreis) {
		this.ausgefuehrt = ausgefuehrt;
		this.gesamtpreis = gesamtpreis;
	}
	
	public boolean getAusgefuehrt() {
		return ausgefuehrt;
	}
	
	public double getGesamtpreis() {
		return gesamtpreis;
	}
	
}
