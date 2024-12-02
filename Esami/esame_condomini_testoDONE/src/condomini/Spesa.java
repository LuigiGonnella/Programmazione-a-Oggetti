package condomini;

public class Spesa {
	private String tipo="D";
	public String getTipo() {
		return tipo;
	}

	private String desc;
	private double importo;
	private String data;
	private boolean pagata;

	public boolean isPagata() {
		return pagata;
	}

	public void setPagata(boolean pagata) {
		this.pagata = pagata;
	}

	public Spesa(String desc, double importo, String data, boolean pagata) {
		this.data=data;
		this.desc=desc;
		this.importo=importo;
		this.pagata=pagata;
	}

	public String getDescrizione() {
		return this.desc;
	}

	public double getImporto() {
		return this.importo;
	}

	public String getData() {
		return this.data;
	}

}
