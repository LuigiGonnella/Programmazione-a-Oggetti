package gestioneTaxi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class TaxiCompany {
	private Map<String,Taxi> elencoTaxi;
	private Map<String,Quartiere> elencoQuartieri;
	private Queue<Taxi> codaTaxi;
	private Map<Taxi, ArrayList<Corsa>> corseTaxi;
	private int corsePerse = 0;

	public TaxiCompany() {
		elencoTaxi = new HashMap<>();
		elencoQuartieri = new HashMap<>();
		codaTaxi = new LinkedList<>();
		corseTaxi = new HashMap<>();
	}
		
	public void addTaxi(String id) throws InvalidTaxiName {		
		if(elencoTaxi.containsKey(id))
			throw (new InvalidTaxiName());
		Taxi t = new Taxi(id);
		elencoTaxi.put(id, t);
		t.setCompany(this);
		corseTaxi.put(t, new ArrayList<Corsa>());
		codaTaxi.add(t);
	}
	
	public Queue<Taxi> getLiberi() {
		return codaTaxi;
	}

	public Taxi chiamataTaxi(Passeggero p) {
		if(codaTaxi.isEmpty()){
			corsePerse++;
			return null;
		}
		else {
			Taxi t = codaTaxi.remove();
			t.chiamata(p);
			return t;
		}
	}
	
	public void nuovaCorsa(Taxi t, Corsa c){
		ArrayList<Corsa> corse = corseTaxi.get(t);
		corse.add(c);
		corseTaxi.put(t, corse);
	}
	
	public void corsaTerminata (Taxi t, Luogo arrivo) {
		codaTaxi.add(t);
		String qid = arrivo.getQuartiere();
		Quartiere q = elencoQuartieri.get(qid);
		if(q == null){
			q = new Quartiere(qid);
			elencoQuartieri.put(qid, q);
		}
		q.addCorsa();
	}
	
	public List<Corsa> getCorse(String id) throws InvalidTaxiName {
		Taxi t = elencoTaxi.get(id);
		if(t == null)
			throw (new InvalidTaxiName());
		return corseTaxi.get(t);
	}
	
	public int getCorsePerse(){
		return corsePerse;
	}
	
	public ArrayList<InfoI> statisticheTaxi() {
		ArrayList<InfoI> list = new ArrayList<InfoI>(elencoTaxi.values());
		Collections.sort(list);
		return list;
	}
	
	public ArrayList<InfoI> statisticheQuartieri() {
		ArrayList<InfoI> list = new ArrayList<InfoI>(elencoQuartieri.values());
		Collections.sort(list);
		return list;
	}
}
