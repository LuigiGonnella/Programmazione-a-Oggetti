package battaglianavale;


public class Nave {
    private String nomeNave;
    public String getName() {
        return nomeNave;
    }

    private int lunghezzaNave;

    public int getLung() {
        return lunghezzaNave;
    }

    // costruttore
    public Nave(String nome, int lunghezza) {
        this.nomeNave = nome;
        this.lunghezzaNave = lunghezza;
    }

}
