public class ArrayExcercises {
    public static void main(String[] args) throws Exception {
        
        int[] numeri = new int[10]; //dichiaro e inizializzo array con dati primitivi int

        int numeri2[]; //dichiaro array con dati primitivi int

        String[] nomi1 = {new String("Jhon")}; //dichiaro array di oggetti String e inizializzo staticamente

        String[] nomi = {"Jhon", "Mary", "Anthony"}; //equivalente al caso precedente ma con scrittura compatta

        String nome1 = nomi[1];
        System.out.println(nome1);

        Person[] persone = {new Person("Mary"), new Person("John")}; //vettore inizializz.

        Person[] persone2 = new Person[10]; //vettore non inizializz.

        for (Person p : persone) {
            System.out.println(p.getName());
        }

        for (Person p : persone2) {
            System.out.println(p.getName()); //da errore perchè gli oggetti in questo vettopre non sono inizializz. -> non hanno campo name -> metodo getName da errore
        }



    }
}
