import java.nio.charset.Charset;

import polito.*; // come la 'include' di C, importiamo le classi che non potevamo vedere (cioè le classi dei file in 'polito', a patto che siano 'public'), ciò è utile per gestire tutti i casi in cui non dichiariamo la visibilità in maniera esplicita -> vedi 'Decimale.java', non essendo public non possiamo vederla al di fuori della cartella 'polito' anche se la importiamo tutta

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Intero i = new Intero(); //inizializza variabile, è come la malloc
        // i.
        System.out.println(i.getNumero()); //otteniamo il valore della variabile ritornata dal getter

        Decimale d1 = new Decimale(); //vediamo Decimale perchè è public e abbiamo importato le classi in 'polito'

        Intero j = new Intero();
        // System.out.println(j); //otteniamo errore se non è inizializzato, se mettiamo 'new' otteniamo un indirizzo in memoria
        j.setNumero(10);
        System.out.println(j);

        i.setNumero(-2);
        //System.out.println(i.getNumero()); possiamo evitare che 'numero' assuma un valore negativo tramite dei controlli -> 'if'
        // if (i.getNumero() < 0)
            //System.out.println("NON CORRETTO"); ciò possiamo farlo nella classe direttamente
        System.out.println("num dell'obj i: " + i.getNumero());
        System.out.println("num dell'obj j: " + j.getNumero());

        i = j; //assegnamo ad i il riferimento dell'oggetto j, attributi e metodi di i saranno gli stessi di j
        System.out.println(i);
        System.out.println(j);
        //stampiamo i due indirizzi, essi saranno uguali, dunque se modificheremo un oggetto modifichiamo anche l'altro, NON si crea una copia -> passaggi sempre by reference

        //PACKAGE: se andiamo col cursore sulla System vediamo che appartiene al package java.lang (così come Integer), quando abbiamo installato jdk, abbiamo di fatto importato una libreria

        Flex f = new Flex();

        //f.setPrimo(1).setSecondo(2).setTerzo(3).setQuarto(4); //operazioni in cascata sullo stesso oggetto 'f', setta tutti e quattro gli attributi di f. Non c'è nessuna assegnazione quindi l'ultimo metodo non deve tornare un tipo particolare, se lo assegnassimo ad un oggetto 'String', allora 'setQuarto' dovrebbe ritornare una stringa

        Flex ff = f.setPrimo(1).setSecondo(2).setTerzo(3).setQuarto(4); //ff è ottenuto a partire f, che era già di tipo Flex

        System.out.println(ff.getPrimo());

        int value = 2;
        Integer abvalue = value; //cast automatico detto AUTOBOXING
        float fvalue = value; //AUTOBOXING
        Integer ivalue = new Integer(value); //costruttore 'deprecato', vengono segnati con una cancellazione perchè in futuro questo costruttore non sarà più disponibile
        ivalue = Integer.valueOf(value);

        String s = "20";
        ivalue = Integer.valueOf(s);

        System.out.println(Charset.availableCharsets()); //stampa charset disponibili sul mio calcolatore

    }
}
