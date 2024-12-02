package nested;

public class ListaOrdinata extends ListaInn { //INHERITANCE

    public void aggiungi (int v) { //OVERRIDE per aggiungere in modo ordinato, in un override posso CAMBIARE i PARAMETRI (numero, nome) e TIPO di RITORNO; per sovrascrivere mi basta chiamare il metodo allo stesso modo
        if (testa == null || testa.valore > v) {
            testa = new Elemento(v, testa);
            return;
        }
        
        Elemento next = testa;
        Elemento prev = null;
        while(next != null && next.valore < v ) {
            prev = next;
            next = next.prossimo;
        }
        prev.prossimo = new Elemento(v, next);



    }
}
