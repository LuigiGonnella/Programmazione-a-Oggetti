package nested;

public class ListaInn {

    class Elemento { //INNER CLASS così dal main creiamo più oggetti di tipo Lista

        int valore;
        Elemento prossimo; //come una lista

        Elemento (int v, Elemento p) {
            valore = v;
            prossimo = p;
        }

    }

    class Iteratore { //INNER CLASS
        Elemento corrente;

        Iteratore(Elemento c) {
            corrente = c;
        }

        public void prossimo() {
            corrente = corrente.prossimo;
        }

        public boolean esisteCorrente() {
            return corrente != null; //true se cond. vera, false se falsa
        }

        public boolean esisteProssimo() {
            if (corrente.prossimo != null) {
                return true;
            }
            return false;
        }

        public int getValore() {
            return corrente.valore;
        }

    }



    Elemento testa = null;

    public void aggiungi (int v) {
        testa = new Elemento(v, testa);

    }

    public void stampa() {
        Elemento e = testa;
        while (e!=null) {
            System.out.println(e.valore);
            e = e.prossimo;
        }
    }

    public Iteratore iteratore() {
        return new Iteratore(testa);
    };
}
