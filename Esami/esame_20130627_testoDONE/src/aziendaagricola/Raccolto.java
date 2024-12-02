package aziendaagricola;

public class Raccolto {
	private double prezzoIngrosso;
	private String prodotto,data;
	private int quantita;

	public double getPrezzoIngrosso() {

		return this.prezzoIngrosso;
	}

	public String getProdotto() {

		return this.prodotto;
	}
	
	public String getData() {

		return this.data;
	}
	
	public int getQuantita() {

		return this.quantita;
	}
	
	public void setPrezzoIngrosso(int prezzoIngrosso) {
		this.prezzoIngrosso=prezzoIngrosso;

	}
	
	public void setProdotto(String prodotto) {
		this.prodotto=prodotto;

	}

	public void setData(String data) {
		this.data=data;

	}

	public void setQuantita(int quantita) {
		this.quantita=quantita;

	}
	
}
