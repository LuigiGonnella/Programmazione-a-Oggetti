package conferenze;

import java.util.*;
import java.util.stream.Collectors;

public class SistemaGestione {

	List<Conferenza> confs = new ArrayList<>();

	public Conferenza nuovaConferenza(String nome, String luogo, int anno, String dataInizio, String dataFine, int quotaIscrizione){
		Conferenza c = new Conferenza(nome, luogo, anno, dataInizio, dataFine, quotaIscrizione);
		confs.add(c);
		return c;
	}
	
	public Collection<Conferenza> elencoConferenze(){
		return this.confs.stream().sorted(Comparator.comparing((Conferenza s)->s.getNome()).thenComparing(s->s.getAnno())).collect(Collectors.toList());
	}

	public Collection<Conferenza> elencoConferenze(int annoDa, int annoA){
		return this.confs.stream().filter(s->s.getAnno()>=annoDa && s.getAnno()<=annoA).sorted(Comparator.comparing((Conferenza s)->s.getNome()).thenComparing(s->s.getAnno())).collect(Collectors.toList());
	}

	List<Utente> utenti = new ArrayList<>();

	public void nuovoUtente(String nome, String cognome, String organizzazione, String email) throws UtenteDuplicatoException{

		if(utenti.stream().filter(s->s.getEmail().equals(email)).count()>0) {
			throw new UtenteDuplicatoException();
		}

		utenti.add(new Utente(nome, cognome, organizzazione, email));
	}
	
	public Utente cercaUtente(String daCercare){
		if(this.utenti.stream().filter(s->s.getCognome().contains(daCercare) || s.getEmail().contains(daCercare) || s.getNome().contains(daCercare) || s.getOrganizzazione().contains(daCercare)).count()==0) {
			return null;
		}
		return this.utenti.stream().filter(s->s.getCognome().contains(daCercare) || s.getEmail().contains(daCercare) || s.getNome().contains(daCercare) || s.getOrganizzazione().contains(daCercare)).collect(Collectors.toList()).get(0);
	}
	
	public Collection<Utente> elencoUtenti(){
		 return this.utenti.stream().sorted(Comparator.comparing((Utente s)->s.getCognome()).thenComparing(s->s.getNome())).collect(Collectors.toList());
	}

	List<Lavoro> lavori = new ArrayList<>();
	
	public String sottomettiLavoro(String acronimo, String titolo, char tipologia, String email){
		if(this.confs.stream().filter(s->s.getAcronimo().equals(acronimo)).count()==0) {
			return null;
		}
		if(this.utenti.stream().filter(s->s.getEmail().equals(email)).count()==0) {
			return null;
		}

		Conferenza c = this.confs.stream().filter(s->s.getAcronimo().equals(acronimo)).collect(Collectors.toList()).get(0);

		Utente u = this.utenti.stream().filter(s->s.getEmail().equals(email)).collect(Collectors.toList()).get(0);

		Long tmp = this.lavori.stream().filter(s->s.getConf().getAcronimo().equals(acronimo)).count();
		Integer num = tmp.intValue()+1;

		String id = acronimo+"-"+num;

		if (tipologia=='A') {
			lavori.add(new Articolo(titolo, id, tipologia, c, u));

		}
		else {
			lavori.add(new Poster(titolo, id, tipologia, c, u));

		}

		


		return id;
	}
	
	public Lavoro cercaLavoro(String id){
		if(this.lavori.stream().filter(s->s.getId().equals(id)).count()==0) {
			return null;
		}
		Lavoro l = this.lavori.stream().filter(s->s.getId().equals(id)).collect(Collectors.toList()).get(0);

		return l;

	}

	public void aggiungiAutore(String id, String email){
		if(this.lavori.stream().filter(s->s.getId().equals(id)).count()==0) {
			return;
		}
		if(this.utenti.stream().filter(s->s.getEmail().equals(email)).count()==0) {
			return;
		}

		Lavoro l = this.lavori.stream().filter(s->s.getId().equals(id)).collect(Collectors.toList()).get(0);

		Utente u = this.utenti.stream().filter(s->s.getEmail().equals(email)).collect(Collectors.toList()).get(0);

		l.AggiungiAutore(u);

	}
	
	public Collection<Utente> elencoAutori(String id){
		Lavoro l = this.lavori.stream().filter(s->s.getId().equals(id)).collect(Collectors.toList()).get(0);
		return l.elencoAutori();
	}

	public class Revisione {
		private Utente u;
		public Utente getU() {
			return u;
		}

		private String commento;
		public String getCommento() {
			return commento;
		}

		private int punteggio;

		public int getPunteggio() {
			return punteggio;
		}

		public Revisione(String email, String commento, int punteggio) {
			this.u=utenti.stream().filter(s->s.getEmail().equals(email)).collect(Collectors.toList()).get(0);
			this.commento=commento;
			this.punteggio=punteggio;
		}
	}

	public void nuovaRevisione(String id, String email, String commento, int punteggio) throws RevisioneRifiutataException{

		if(this.lavori.stream().filter(s->s.getId().equals(id)).count()==0) {
			throw new RevisioneRifiutataException();
		}
		if(this.utenti.stream().filter(s->s.getEmail().equals(email)).count()==0) {
			throw new RevisioneRifiutataException();
		}

		if(punteggio<1 || punteggio>5) {
			throw new RevisioneRifiutataException();
		}

		Lavoro l = this.lavori.stream().filter(s->s.getId().equals(id)).collect(Collectors.toList()).get(0);

		l.AggiungiRevisione(new Revisione(email, commento, punteggio));


	}	
	
	public String generaProgramma(String acronimo){
		List<Lavoro> articoli = this.lavori.stream().filter(s->s.getConf().getAcronimo().equals(acronimo) && s.getTipo()=='A').sorted(Comparator.comparing((Lavoro s)->s.calcolaPunteggioMedio()).reversed()).collect(Collectors.toList());

		List<Lavoro> posters = this.lavori.stream().filter(s->s.getConf().getAcronimo().equals(acronimo) && s.getTipo()=='P').sorted(Comparator.comparing((Lavoro s)->s.calcolaPunteggioMedio()).reversed()).collect(Collectors.toList());

		String str="";

		for (Lavoro l : articoli) {
			str+=l.getTitolo()+","+"\n";
		}

		for (Integer i=0;i<posters.size()-1;i++) {
			str+=posters.get(i).getTitolo()+","+"\n";
		}

		str+=posters.get(posters.size()-1).getTitolo()+".";


		return str;
	}

	public class Iscrizione {
		private Utente u;
		public Utente getU() {
			return u;
		}

		private Conferenza c;

		public Conferenza getC() {
			return c;
		}

		public Iscrizione(String email, String acronimo) {
			this.u=utenti.stream().filter(s->s.getEmail().equals(email)).collect(Collectors.toList()).get(0);
			this.c=confs.stream().filter(s->s.getAcronimo().equals(acronimo)).collect(Collectors.toList()).get(0);
		}
	}

	List<Iscrizione> iscritti = new ArrayList<>();
	
	public void iscrivi(String email, String acronimo){
		if(this.utenti.stream().filter(s->s.getEmail().equals(email)).count()==0) {
			return;
		}
		if(this.confs.stream().filter(s->s.getAcronimo().equals(acronimo)).count()==0) {
			return;
		}

		iscritti.add(new Iscrizione(email, acronimo));


	}
	
	public Collection<Utente> elencoIscritti(String acronimo){
		return this.iscritti.stream().filter(s->s.getC().getAcronimo().equals(acronimo)).map(s->s.getU()).collect(Collectors.toList());
	}
	
	public int calcolaIncasso(String acronimo){
		return this.iscritti.stream().filter(s->s.getC().getAcronimo().equals(acronimo)).collect(Collectors.summingInt(s->s.getC().getQuotaIScrizione()));
	}
	
}
