
public class Flex {

    private Integer primo;
    public Integer getPrimo() {
        return primo;
    }

    private Integer secondo;
    public Integer getSecondo() {
        return secondo;
    }

    private Integer terzo;
    public Integer getTerzo() {
        return terzo;
    }

    private Integer quarto;

    public Integer getQuarto() {
        return quarto;
    }

    public Flex setPrimo(Integer i) {
        this.primo = i; //assegnamo all'oggetto 'primo' di questa classe il valore passato come parametro
        return this; //ritorniamo il valore
    }

    public Flex setSecondo(Integer i) {
        this.secondo = i; //assegnamo all'oggetto 'secondo' di questa classe il valore passato come parametro
        return this; //ritorniamo il valore
    }

    public Flex setTerzo(Integer i) { //non darebbe errore anche se il metodo fosse riferito alla classe 'Object' questo perchè la classe Object è una classe di default ed è PADRE di tutte le altre classi in java. Perderemmo però tutti i metodi di Flex -> non li possiamo usare sull'oggetto 'terzo', volendo si può 'trasformare' in 'Flex'

        this.terzo = i; //assegnamo all'oggetto 'terzo' di questa classe il valore passato come parametro
        return this; //ritorniamo il valore
    }

    public Flex setQuarto(Integer i) {
        this.quarto = i; //assegnamo all'oggetto 'quarto' di questa classe il valore passato come parametro
        return this; //ritorniamo il valore
    }

}
