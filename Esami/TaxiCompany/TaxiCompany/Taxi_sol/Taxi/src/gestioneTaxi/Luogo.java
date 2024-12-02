package gestioneTaxi;

public class Luogo {
	
	private String indirizzo;
	private String quartiere;
	
	public Luogo(String ind, String quart) {
		indirizzo = ind;
		quartiere = quart;
	}
	
	public String getQuartiere(){
		return quartiere;
	}
	
    public String toString() {
        return indirizzo;
    }

}
