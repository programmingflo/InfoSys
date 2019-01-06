package gdP_Projekt_InfoSys;

class Artikel {
	private final String id;	// ID sollte nach Erstellung des Artikels nicht mehr aenderbar sein => final
	private String name;	// Name ist u.U. nochmal zu aendern => nicht final
	private String beschreibung;
	
	public Artikel(String id, String name, String beschreibung) {
		this.id = id;
		this.name = name;
		this.beschreibung = beschreibung;
	}
	
	public String getID() {
		return this.id;
	}
	
	public void setArtikelBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	public String getArtikelBeschreibung() {
		return this.beschreibung;
	}
	
	public void setArtikelName(String name) {
		this.name = name;
	}
	
	public String getArtikelName() {
		return name;
	}
	
	
}
