package condomini;

public class SpesaStraordinaria extends Spesa{
	private double perc;
	private String tipo="St";
	private double importo;
	
	
	public String getTipo() {
		return tipo;
	}
	public SpesaStraordinaria(String desc, double importo, String data, boolean pagata, double perc) {
		super(desc,importo,data,pagata);
		this.perc=perc;
		this.importo=this.importo+this.importo*perc/100;
	}
	public void setPercentualeAmministratore(double perc) {
		this.perc = perc;
	}

	public double getPercentualeAmministratore() {
		return this.perc;
	}
}
