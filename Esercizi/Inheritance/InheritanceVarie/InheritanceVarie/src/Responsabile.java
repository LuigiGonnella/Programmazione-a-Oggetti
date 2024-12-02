public class Responsabile extends Impiegato {

    private String dipartimento;

    public String getDipartimento() {
        return dipartimento;
        // super. accedo alla referenza del parent, così come con this. accedo alla referenza dell'oggetto corrente
    }

    public void setDipartimento(String dipartimento) {
        this.dipartimento = dipartimento;
    }

    public Responsabile(String nome, String cognome, String dipartimento) {
        super(nome, cognome); //con super eredito il metodo dalla classe che estendo con i suoi parametri, qualora avessi altri metodi, ne implemento l'assegnazione:

        this.dipartimento = dipartimento;
        
    }

     

}
