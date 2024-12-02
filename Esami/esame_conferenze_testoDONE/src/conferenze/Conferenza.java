package conferenze;

import java.util.*;

public class Conferenza {
	private String nome,luogo,datainizio,datafine,acronimo;
	private int anno,quotaiscrizione;

	public Conferenza(String nome, String luogo, int anno, String dataInizio, String dataFine, int quotaIscrizione) {
		this.nome=nome;
		this.luogo=luogo;
		this.anno=anno;
		this.datafine=dataFine;
		this.datainizio=dataInizio;
		this.quotaiscrizione=quotaIscrizione;
		String[] acr = nome.split(" ")
;		String acron="";

		for (String str : acr) {
			acron+=str.charAt(0);
		}
		acron=acron.toUpperCase()+anno;
		this.acronimo=acron;
	}

	public String getNome() {
		return this.nome;
	}

	public String getLuogo() {
		return this.luogo;
	}

	public int getAnno() {
		return this.anno;
	}

	public String getDataInizio() {
		return this.datainizio;
	}

	public String getDataFine() {
		return this.datafine;
	}	

	public int getQuotaIScrizione() {
		return this.quotaiscrizione;
	}	
	
	public String getAcronimo(){
		return this.acronimo;
	}

	List<String> sponsors = new ArrayList<>();
	public void aggiungiSponsor(String sponsor){
		if(!sponsors.contains(sponsor)) {
			sponsors.add(sponsor);
		}
	}
	
	public Collection<String> elencoSponsor(){
		return this.sponsors;
	}

}
