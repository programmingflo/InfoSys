package gdP_Projekt_InfoSys;

class Artikel {
	private final String id;	// ID nach Erstellung des Artikels nicht mehr aenderbar
	private String name;
	private String beschreibung;
	
	public Artikel(String id, String name, String beschreibung) {
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
	}
	
	public void setArtikelBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public String getArtikelBeschreibung() {
		return beschreibung;		// this. ja nicht notwendig
	}
	
	
	// setArtikelName eeventuell Quark weil wir den Namen natuerlich auch als final legen koennten,
	// aaber was solls.
	public void setArtikelName(String name) {
		this.name = name;
	}
	
	public String getArtikelName() {
		return name;
	}
	
	
}
