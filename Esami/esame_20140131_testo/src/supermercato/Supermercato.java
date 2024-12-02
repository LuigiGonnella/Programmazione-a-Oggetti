package supermercato;

import java.util.*;

public class Supermercato {

	public void aggiungiCorsia(String nome, int capienzaMassima){

	}
	
	public Prodotto catalogaProdotto(String codiceProdotto, String nomeProdotto, int volume, boolean daFrigo){
		return null;
	}	
	
	public Collection<Prodotto> elencoProdotti(){
		return null; 
	}
	
	public Prodotto cercaProdotto(String codiceProdotto){
		return null;
	}
	
	public void esponiProdotto(String nomeCorsia, Prodotto prodotto, int quantita){
		
	}

	public int calcolaPercentualeDiOccupazione(String nomeCorsia){
		return -1;
	}
	
	public Collection<String> elencoCodiciProdottoPerCorsia(String nomeCorsia){
		return null;
	}

	public int quantitaProdottoEsposto(Prodotto prodotto, String nomeCorsia){
		return -1;
	}
	
	public int quantitaProdottoEsposto(Prodotto prodotto){
		return -1;
	}
	
	public int apriScontrino(){
		return -1;
	}
	
	public void acquistaProdotto(int codiceScontrino, Prodotto prodotto, String nomeCorsia, int quantita){

	}
	
	public String dettagliScontrino(int codiceScontrino){
		return null;
	}
	
	public double chiudiScontrino(int codiceScontrino) throws ProdottoInesistenteException{
		return -1.0;
	}
	
	public void leggiFile(String nomeFile) {

	}	
	
}
