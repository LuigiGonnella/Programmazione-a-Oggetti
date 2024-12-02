package aziendaagricola;

public class Magazzino {

	private String nome,prodotto;
	private int quantitaStoccabile,quantitaStoccata;

	public String getNome() {
		return this.nome;
	}

	public String getProdotto() {
		return this.prodotto;
	}
	
	public int getQuantitaStoccabile() {
		return this.quantitaStoccabile;
	}

	public int getQuantitaStoccata() {
		return this.quantitaStoccata;
	}

	public void setNome(String nome) {
		this.nome=nome;

	}

	public void setProdotto(String prodotto) {
		this.prodotto=prodotto;

	}

	public void setQuantitaStoccabile(int quantitaStoccabile) {
		this.quantitaStoccabile=quantitaStoccabile;

	}

	public void setQuantitaStoccata(int quantitaStoccata) {
		this.quantitaStoccata=quantitaStoccata;

	}	
	
	public int stocca(Raccolto raccolto) throws ProdottoNonCompatibileException{

		if (!raccolto.getProdotto().equals(this.getProdotto())) {
			throw new ProdottoNonCompatibileException();
		}

		if (this.getQuantitaStoccabile()>=raccolto.getQuantita()) {
			this.setQuantitaStoccabile(this.getQuantitaStoccabile()-raccolto.getQuantita());
			this.setQuantitaStoccata(this.getQuantitaStoccata()+raccolto.getQuantita());
			return this.getQuantitaStoccabile();

		}

		this.setQuantitaStoccata(this.getQuantitaStoccata()+this.getQuantitaStoccabile());
		int q = this.getQuantitaStoccabile();
		this.setQuantitaStoccabile(0);
		

		return q-raccolto.getQuantita();
	}
	
	public void preleva(int quantita){
		this.setQuantitaStoccabile(this.getQuantitaStoccabile()+quantita);


	}
	
}
