package gdP_Projekt_InfoSys;

class Start {

	public static void main(String[] args) {
		Lagerverwaltung lw1 = new Lagerverwaltung();
		// 9 Mitarbeiter anlegen, jeder Mitarbeiter bekommt Berechtigung
		Mitarbeiter ma1 = new Mitarbeiter("1", "Max Mustermann");
		lw1.berechtigungErteilen(ma1);
		Mitarbeiter ma2 = new Mitarbeiter("2", "Peter Pan");
		lw1.berechtigungErteilen(ma2);
		Mitarbeiter ma3 = new Mitarbeiter("3", "Karsten Kohl");
		lw1.berechtigungErteilen(ma3);
		Mitarbeiter ma4 = new Mitarbeiter("4", "Alina Alba");
		lw1.berechtigungErteilen(ma4);
		Mitarbeiter ma5 = new Mitarbeiter("5", "Bertram Bertold");
		lw1.berechtigungErteilen(ma5);
		Mitarbeiter ma6 = new Mitarbeiter("6", "Christina Christlich");
		lw1.berechtigungErteilen(ma6);
		Mitarbeiter ma7 = new Mitarbeiter("7", "Erhardt Ehrlich");
		lw1.berechtigungErteilen(ma7);
		Mitarbeiter ma8 = new Mitarbeiter("8", "Saskia Suske");
		lw1.berechtigungErteilen(ma8);
		Mitarbeiter ma9 = new Mitarbeiter("9", "Tatajana Tesko");
		lw1.berechtigungErteilen(ma9);		
		// Mitarbeiter 3, 4, 7 und 9 wird Berechtigung wieder entzogen
		lw1.berechtigungZurueckziehen(ma3);
		lw1.berechtigungZurueckziehen(ma4);
		lw1.berechtigungZurueckziehen(ma7);
		lw1.berechtigungZurueckziehen(ma9);
		







	}

}
