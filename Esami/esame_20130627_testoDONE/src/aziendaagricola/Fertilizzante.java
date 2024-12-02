package aziendaagricola;

public class Fertilizzante {
	private String nome;
	private char tipo;
	private float costo;
	private int periodicita,quantitaImpiego;

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return this.nome;
	}
	
	public float getCosto() {
		return this.costo;
	}
	
	public int getPeriodicita() {
		return this.periodicita;
	}
	
	public int getQuantitaImpiego() {
		return this.quantitaImpiego;
	}

	public void setNome(String nome) {
		this.nome=nome;

	}
	
	public void setCosto(float costo) {
		this.costo=costo;

	}
	
	public void setPeriodicita(int periodicita) {
		this.periodicita=periodicita;

	}

	public void setQuantitaImpiego(int quantitaImpiego) {
		this.quantitaImpiego=quantitaImpiego;

	}
	
}
