package nested;

import nested.Lista.Iteratore;

public class App {
    public static void main(String[] args) throws Exception {

        AddLista();
        AddListaOrdinata();
    }

    private static void AddListaOrdinata() {
        ListaOrdinata l = new ListaOrdinata();
        l.aggiungi(5);
        l.aggiungi(6);
        l.aggiungi(3);

        l.stampa();

    }

    private static void AddLista() {
        Lista l = new Lista();
        l.aggiungi(10);
        l.aggiungi(20);
        l.aggiungi(30);

        l.stampa();

        Iteratore i = l.iteratore();

        while (i.esisteCorrente()) {
            System.out.println(i.getValore());
            i.prossimo();
        }

        //while (i.esisteProssimo()) {
          //  System.out.println(i.getValore());
            //i.prossimo();
        //} non stamperebbe l'ultimo che punta a null

    }

    
}
