package gestioneTaxi;

public class Passeggero {
	
	private Luogo posizione;

	public Passeggero(Luogo pos) {
		posizione = pos;
	}
	
	public Luogo getPosizione() {
		return posizione;
	}
	
	public void setPosizione(Luogo pos) {
		posizione = pos;
	}

}
