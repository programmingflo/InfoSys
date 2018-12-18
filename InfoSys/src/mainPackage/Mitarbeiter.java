package gdP_Projekt_InfoSys;

class Mitarbeiter {
	private final String id;
	private final String name;	// Name vom Mitarbeiter auch als "final"
	
	public Mitarbeiter(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
