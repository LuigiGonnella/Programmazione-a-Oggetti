package gestioneTaxi;

public class Corsa {
	
	private Luogo partenza, arrivo;

	public Corsa(Luogo p, Luogo a) {
		partenza = p;
		arrivo = a;
	}
	
	public String toString() {
		return partenza + "," + arrivo;
	}
}
