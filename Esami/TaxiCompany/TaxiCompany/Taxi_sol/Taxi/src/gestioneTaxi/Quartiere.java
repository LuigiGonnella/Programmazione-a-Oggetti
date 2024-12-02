package gestioneTaxi;

public class Quartiere implements InfoI {
	
	private String id;
	private int numCorse;
	
	public Quartiere (String ids){
		id = ids;
		numCorse = 0;	
	}
	
	public void addCorsa(){
		numCorse++;
	}

	@Override
	public int compareTo(InfoI o) {
		int d = o.getValore() - numCorse;
		if(d != 0)
			return d;
		return id.compareTo(o.getId());
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public int getValore() {
		return numCorse;
	}

}
