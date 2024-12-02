package battaglianavale;


public class BattagliaNavale {
    public static void main(String[] args) throws Exception {

        // creo oggetti
        CampoBattaglia cb = new CampoBattaglia(10, 10);
        Nave n1 = new Nave("Vespucci", 4);

        //genero campo
        cb.generaCampo();

        cb.posizionaNave(n1, "NS", 0, 0);
        cb.displayField();
        
        
        
        
        
    }
}
