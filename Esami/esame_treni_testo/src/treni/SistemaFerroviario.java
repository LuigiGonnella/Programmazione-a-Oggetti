package treni;

import java.util.*;

public class SistemaFerroviario {

	public void nuovaLinea(String nomeLinea){
	}
	
	public Stazione nuovaStazione(String nomeStazione, String nomeLinea, String chilometro){
		return null;
	}
	
	public String lunghezzaLinea(String nomeLinea){
		return null;
	}

	public Collection<Stazione> elencoStazioni(){
		return null;
	}

	public Collection<Stazione> elencoStazioni(String direzione){
		return null;
	}
	
	public void nuovoTreno(int numero, String tipologia, String nomeLinea){
	}
	
	public Treno treno(int numeroTreno, String tipologia){
		return null;
	}
	
	public void aggiungiFermata(int numeroTreno, String tipologia, String nomeStazione, String ora){
	}

	public Collection<Treno> cerca(String nomeStazionePartenza, String nomeStazioneArrivo) {
		return null;
	}
	
	public Biglietto emettiBiglietto(String nomeStazionePartenza, String nomeStazioneArrivo, String data, int numeroTreno, String tipologia) throws PostiEsauritiException{
		return null;
	}
}
