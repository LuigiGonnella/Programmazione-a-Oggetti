package conferenze;

public class Utente {

	private String nome,cognome,email,organizzazione;

	public Utente(String nome, String cognome, String organizzazione, String email) {
		this.nome=nome;
		this.cognome=cognome;
		this.email=email;
		this.organizzazione=organizzazione;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public String getOrganizzazione() {
		return this.organizzazione;
	}

	public String getEmail() {
		return this.email;
	}

}
