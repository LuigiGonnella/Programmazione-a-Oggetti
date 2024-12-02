public class Impiegato {

    private String nome;
    private String cognome;

public Impiegato(){} //aggiungiamo nuovamente il costruttore implicito senza parametri che avevamo perso scrivendo quello esplicito sotto, posso quindi usare la dicitura Impiegato i = new Impiegato()

    public Impiegato(String nome, String cognome) {
       this.nome=nome;
       this.cognome=cognome;
    } 
    
    //costruttore esplicito elimina il costruttore implicito, questo fa sì che tutte le classi che estendono impiegato non ne ereditano il costruttore e devono implementarlo

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
   
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String toString() {
        return "toString";
    }

}
