package aziendaagricola;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Zona {
	private int codice;

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public int getCodice() {
		
		return this.codice;
	}
	
	private int ampiezza;
	public int getAmpiezza() {
		
		return this.ampiezza;
	}

	private int temperaturamedia;
	public int getTemperaturaMedia() {
		
		return this.temperaturamedia;
	}

	private float irragiamentomedio;
	public float getIrraggiamentoMedio() {
		
		return this.irragiamentomedio;
	}
	
	public void setAmpiezza(int ampiezza) {
		this.ampiezza=ampiezza;

	}

	public void setTemperaturaMedia(int temperaturaMedia) {
		this.temperaturamedia=temperaturaMedia;

	}

	public void setIrraggiamentoMedio(float irraggiamentoMedio) {
		this.irragiamentomedio=irraggiamentoMedio;

	}

	List<String> caratteristiche = new ArrayList<>();

	public Collection<String> elencoCaratteristiche(){

		return this.caratteristiche.stream().sorted().collect(Collectors.toList());
	}

	public void aggiungiCaratteristica (String car) {
		this.caratteristiche.add(car);
	}

	List<Coltivazione> cols = new ArrayList<>();
	
	public Coltivazione aggiungiColtivazione(String prodotto, int meseSemina, int meseRaccolto) throws ColtivazioneDuplicataException{
		if (cols.stream().filter(s->s.getProdotto().equals(prodotto) && s.getMeseSemina()==meseSemina).count()>0) {
			throw new ColtivazioneDuplicataException();
		}
		Coltivazione col = new Coltivazione();
		col.setMeseRaccolta(meseRaccolto);
		col.setMeseSemina(meseSemina);
		col.setProdotto(prodotto);
		cols.add(col);
		
		return col;
	}

	public Collection<Coltivazione> elencoColtivazioni(){

		return this.cols;
	}
	

	List<Raccolto> raccolti = new ArrayList<>();
	
	public Raccolto nuovoRaccolto(String prodotto, String data, int quantita){
		Raccolto r = new Raccolto();
		r.setData(data);
		r.setProdotto(prodotto);
		r.setQuantita(quantita);

		raccolti.add(r);

		return r;
	}
	
	public Raccolto cercaRaccolto(String prodotto, String data){
		
		return raccolti.stream().filter(s->s.getData().equals(data) && s.getProdotto().equals(prodotto)).collect(Collectors.toList()).get(0);
	}
	
	public Collection<Raccolto>elencoRaccolti(){
		
		return this.raccolti.stream().sorted(Comparator.comparing((Raccolto s)->s.getData()).thenComparing(s->s.getQuantita())).collect(Collectors.toList());
	}
	
}
