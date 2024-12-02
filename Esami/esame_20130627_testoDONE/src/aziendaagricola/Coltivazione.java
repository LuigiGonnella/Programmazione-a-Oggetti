package aziendaagricola;

import java.util.ArrayList;
import java.util.List;

public class Coltivazione {
	private String prodotto;
	private int MeseSemina, MeseRaccolta;

	public String getProdotto() {
		return this.prodotto;
	}

	public int getMeseSemina() {
		return this.MeseSemina;
	}

	public int getMeseRaccolta() {
		return this.MeseRaccolta;
	}
	
	public void setProdotto(String prodotto) {
		this.prodotto=prodotto;
		
	}

	public void setMeseSemina(int meseSemina) {
		this.MeseSemina=meseSemina;
		
	}

	public void setMeseRaccolta(int meseRaccolta) {
		this.MeseRaccolta=meseRaccolta;
		
	}

	List<Fertilizzante> fers = new ArrayList<>();
	public Fertilizzante richiedeFertilizzante(char tipo, String nome, float costo, int periodicita, int quantitaImpiego){
		if (tipo=='A') {
			FertilizzanteArtificiale f = new FertilizzanteArtificiale();
			f.setCosto(costo);
			f.setNome(nome);
			f.setTipo(tipo);
			f.setPeriodicita(periodicita);
			f.setQuantitaImpiego(quantitaImpiego);
			fers.add(f);
			return f;

		}
		else {
			FertilizzanteNaturale f = new FertilizzanteNaturale();
			f.setCosto(costo);
			f.setNome(nome);
			f.setTipo(tipo);
			f.setPeriodicita(periodicita);
			f.setQuantitaImpiego(quantitaImpiego);
			fers.add(f);
			return f;

		}
	}
	
}
