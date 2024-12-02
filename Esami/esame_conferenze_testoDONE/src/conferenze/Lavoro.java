package conferenze;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import conferenze.SistemaGestione.Revisione;

public class Lavoro {
	private String titolo,id;
	private char tipo;
	private Conferenza conf;
	public Conferenza getConf() {
		return conf;
	}

	private Utente autore;

	public Utente getAutore() {
		return autore;
	}

	public char getTipo() {
		return tipo;
	}

	public Lavoro(String titolo,String id,char tipo, Conferenza conf, Utente autore) {
		this.tipo=tipo;
		this.titolo=titolo;
		this.id=id;
		this.conf=conf;
		this.autore=autore;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public String getId() {
		return this.id;
	}

	List<Utente> coautori = new ArrayList<>();

	public void AggiungiAutore(Utente u) {
		this.coautori.add(u);
	}

	public Collection<Utente> elencoAutori() {
		List<Utente> l = new ArrayList<>();
		l.add(this.getAutore());
		for (Utente u : this.coautori) {
			l.add(u);
		}
		return l;
	}

	List<Revisione> revisioni = new ArrayList<>();

	public List<Revisione> getRevisioni() {
		return revisioni;
	}


	public void AggiungiRevisione(Revisione r ) {
		revisioni.add(r);
	}
	

	public double calcolaPunteggioMedio(){
		Integer n = revisioni.size();
		return (double)this.revisioni.stream().collect(Collectors.summingDouble(s->s.getPunteggio()))/n;
	}

}
