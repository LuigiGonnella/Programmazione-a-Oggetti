
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class App {
    public static void main(String[] args) throws Exception {
        List<String> lista = new ArrayList<>(); //possiamo scegliere arraylist o linkedlist, la prima distrugge e rigenera l'oggetto dopo ogni modifica, la seconda modifica semplicemente. Scegliamo una o 'altra a piacere, i metodi sono gli stessi, derivano da java.util.List.

        lista.add("primo"); //metodo per aggiungere alla lista
        lista.add("secondo"); //aggiunto dopo primo
        lista.add("terzo");
        lista.add("quarto");
        lista.add("quinto"); 
        lista.add("sesto"); 
        lista.add("settimo");
        lista.add("ottavo");

        lista.contains("secondo"); //metodo per cercare un oggetto, ritorna true se c'è e false se non c'è. Qui torna true.

        Clazz c = new Clazz();
        lista.contains(c); //torna false

        int contatore=0;
        for(String s : lista) {
            System.out.println(s);
            contatore++;
        }

        lista.forEach(
            s -> {System.out.println(s); //lambda function
                    // contatore += s.length(); errpre di sintassi, nel body della lambda non vedo contatore che ho definito fuori.
            }
        ); 

        lista.remove(0); //metodo per eliminare oggetto, metto indice dell'elemento da eliminare. Da errore se l'indice è out of bound.

        lista.remove("quarto"); //come prima ma gli passiamo direttamente l'elemento da eliminare. In caso di successo ritrona true, se non c'è l'elemento da false, non errore.

        lista.removeIf((String s) -> s.length()> 6); //elimina gli oggetti che soddisfano la condizione scritta all'interno. Se è un generic bisogna eseguire il cast dell'oggeto, in questo caso potremmo non farlo.

        Collections.sort(lista); //metodo per ordinare la lista, la ordina in senso CRESCENTE (ha senso per gli interi ma non per le stringhe)
        System.out.println("---------------");

        Collections.sort(lista, (s1,s2)->s1.length()-s2.length()); //ordino in senso crescente di lunghezza per le stringhe
        lista.forEach(s->{System.out.println(s);});

        //Collections.sort(lista,);
        System.out.println("---------------");

        Comparator<String> lun1 = comparaString(s->s.length());
        Comparator<String> lun2 = comparaString(String::length);
        lista.sort(lun1);
        lista.forEach(s->{System.out.println(s);});


        Collections.sort(lista, Comparator.comparing(String::length).reversed().thenComparing(s->s));

        Map<String, String> vocabolario = new HashMap<>(); //definisco dizionario in cui la key è una stringa (prima istanza) così come il value (seconda istanza)
        vocabolario.put("unito", "Univ di Torino"); //metodo per aggiungere oggetto
        vocabolario.put("polito", "Polit di Torino");

        
        System.out.println("---------------");

        for (String s : vocabolario.keySet()) { //keyset mi da tutte le chiavi del dizionario
            System.out.println(s);

        }

        System.out.println("---------------");

        for (String s : vocabolario.values()) {//values mi da tutti i value del dizionario
            System.out.println(s);
        }

        Map<String, List<String>> anagrafica = new HashMap<String, List<String>>();
        anagrafica.put("primo", new ArrayList<>());


        System.out.println("---------------");

        List<Integer> li = new ArrayList<>();
        li.add(2);
        li.add(3);
        li.add(1);

        li.remove(1); //elimina oggetto all'indice 1
        li.remove((Integer) 1); //elimina oggetto 1

        li.forEach(e -> {System.out.println(e);});
    }

    static <T>Comparator<T> comparaString( 
        Function<T,Integer> intA) {

            return (a,b) -> (intA.apply(a) - intA.apply(b));
    }
}
