package condomini;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Condominio {
	private String indirizzo;
	private int numeroCivico;
	private double saldoin;
	public double getSaldoin() {
		return saldoin;
	}

	private double saldo;
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	private String codice;

	public Condominio(String indirizzo, int numeroCivico, double saldo) {
		this.indirizzo=indirizzo;
		this.numeroCivico=numeroCivico;
		this.saldo=saldo;
		this.saldoin=saldo;
		this.codice=indirizzo.split(" ")[1].toUpperCase()+numeroCivico;

	}
	public String getCodice() {
		return this.codice;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public int getNumeroCivico(){
		return this.numeroCivico;
	}
	
	public double getSaldo() {
		return this.saldo;
	}

	List<Proprietario> props = new ArrayList<>();

	public void aggiungiProp(Proprietario p) {
		this.props=this.props.stream().filter(s->!(s.getCondominio().equals(p.getCondominio()) && s.getInterno()==p.getInterno())).collect(Collectors.toList());
		props.add(p);
	}
	
	public Collection<Proprietario> elencoProprietari(){
		return this.props.stream().sorted(Comparator.comparing(s->s.getProprietario())).collect(Collectors.toList());
	}

	List<Spesa> spese = new ArrayList<>();

	public void aggiungiSpesa(Spesa s) {
		spese.add(s);
	}
	
	public Collection<Spesa> elencoSpeseCondominioPerDataCrescente(){
		return this.spese.stream().sorted(Comparator.comparing(s->s.getData())).collect(Collectors.toList());
	}
	
	public Collection<Spesa> elencoSpeseCondominioAncoraDaPagare(){
		return this.spese.stream().filter(s->s.isPagata()==false).sorted(Comparator.comparing(s->s.getData())).collect(Collectors.toList());
	}

	public Collection<Spesa> elencoSpeseCondominioPerImportoDecrescente(){
		return this.spese.stream().sorted(Comparator.comparing((Spesa s)->s.getImporto()).reversed()).collect(Collectors.toList());
	}
}
