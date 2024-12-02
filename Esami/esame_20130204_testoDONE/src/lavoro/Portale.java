package lavoro;

import java.util.*;

public class Portale {

	public int aggiungiOffertaDiLavoro(String datoreDiLavoro, String figura, String sede, String dataInizioValidita, String dataFineValidita, int numPosizioniAperte){
		return -1;
	}

	public int aggiungiCurriculum(String codiceFiscale, String nome, String cognome, String dataNascita, String residenza){
		return -1;
	}

	public Inserzione getInserzione(int numInserzione){
		return null;
	}
	
	public Inserzione rimuoviInserzione(int numInserzione){
		return null;
	}

	public Curriculum getCurriculum(String codiceFiscale){
		return null;
	}
	
	public Collection<Inserzione> elencoInserzioni(){
		return null;
	}
	
	public Collection<OffertaDiLavoro> elencoOfferteDiLavoro(){
		return null;
	}
	
	public Collection<Curriculum> elencoCurriculum(){
		return null;
	}
	
	public String[] elencoCompetenze(int numInserzione){
		return null;
	}

	public int getLivelloCompetenza(int numInserzione, String competenza){
		return -1;
	}
	
	public Collection<Inserzione> cerca(int numInserzione){
		return null;
	}
	
	public void leggiFile(String nomeFile){
	}
	
}
