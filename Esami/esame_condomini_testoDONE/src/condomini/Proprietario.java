package condomini;

public class Proprietario{
	private String proprietario;
	private double millesimi,debito;
	public void setDebito(double debito) {
		this.debito = debito;
	}

	private int interno;
	private String codicecondominio;

	public Proprietario(String codiceCondominio, String cognome, String nome, int interno, double millesimi, double debito) {
		this.proprietario=cognome+" "+nome;
		this.millesimi=millesimi;
		this.debito=debito;
		this.interno=interno;
		this.codicecondominio=codiceCondominio;
	}

	public String getProprietario() {
		return this.proprietario;
	}
	
	public double getMillesimi() {
		return this.millesimi;
	}

	public int getInterno(){
		return this.interno;
	}

	public double getDebito(){
		return this.debito;
	}

	public String getCondominio() {
		return this.codicecondominio;
	}
 
}
