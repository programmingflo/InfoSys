package gdP_Projekt_InfoSys;

class Start {

	public static void main(String[] args) {
		Lagerverwaltung lw1 = new Lagerverwaltung();
		lw1.berechtigungErteilen(new Mitarbeiter("1", "Max Mustermann"));

	}

}
