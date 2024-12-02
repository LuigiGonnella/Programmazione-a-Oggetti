package gestioneTaxi;

public class Taxi implements InfoI{
	private TaxiCompany company;
    private Luogo partenza, arrivo;
    private Passeggero passeggero;
    private String id;
    private int numCorse;

	public Taxi(String ids) {
		id = ids;
		numCorse = 0;
	}
	
	public void setCompany(TaxiCompany tc){
		company = tc;
	}
	
	public void chiamata(Passeggero p) {
		partenza = p.getPosizione();
		passeggero = p;
	}
	
	public void inizioCorsa(Luogo destinazione) {
		arrivo = destinazione;
		company.nuovaCorsa(this, new Corsa(partenza, arrivo));
	}
	
	public void fineCorsa(){
		passeggero.setPosizione(arrivo);
		company.corsaTerminata(this, arrivo);
		numCorse++;
	}
	
	public String toString(){
		return id;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public int getValore() {
		return numCorse;
	}

	@Override
	public int compareTo(InfoI o) {
		int d = o.getValore() - numCorse;
		if(d != 0)
			return d;
		return id.compareTo(o.getId());
	}

}
