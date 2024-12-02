package polito; //abbiamo dichiarato un pacchetto, forziamo il file corrente all'interno della cartella 'polito' in 'src', abbiamo quindi creato un vincolo alla visibilità delle classi -> App.java non sa più dove si trova 'Intero' perchè App è fuori da 'polito', per risolvere ciò dobbiamo eseguire un'importazione nel file App.java

public class Intero {
    //definisco gli oggetti, ovvero gli attributi, della classe
    private int numero;
    private Integer integerNumero; //classe Integer di jdk, di default, NON ESSENDO PRIMITIVO, è NULL. E' anch'essa una wrapper class perchè parte da un dato primitivo e ci aggiunge dei metodi
    public Decimale d; //vediamo la classe Decimale perchè è public e appartiene a 'polito'

    //definisco metodi e costruttori della classe
    public Intero() {
     // integerNumero = 10; //inizializzazione della classe senza passare dal costrutto 'new', possibile perchè passa dal tipo primitivo 'int'
     numero = 5; //è una wrapper class perchè contiene un dato primitivo
        

    } //è un COSTRUTTORE della classe corrispondente, utile per inizializzarne 'staticamente' gli attributi, simile al SETTER che però setta dei valori presi come parametro

    public void setNumero(int numero) {
        //this.numero = numero; int, essendo PRIMITIVO, di default è inizializzato a 0
        if (numero < 0) {
            this.numero = 0; //senza il 'this' selezioneremmo il parametro, se il parametro si chiamasse diversamente potremmo omettere il this, e cambiare il nome nell'if con quello del parametro
            System.out.println("NON CORRETTO");
        } else
            this.numero = numero;
    
    }

    public int getNumero() {
        return numero;
    }

    public Integer getIntegerNumero() {
        return integerNumero;
    }

}
