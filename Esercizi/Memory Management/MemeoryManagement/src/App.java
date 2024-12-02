public class App {
    public static void main(String[] args) throws Exception {
        String s1 = new String();

        String s2 = new String();

        s2 = s1; //in automatico si cancella l'area di memoria che precedentemente era affidata ad s2, questo meccanismo è detto GARBAGE COLLECTOR
    }
}
