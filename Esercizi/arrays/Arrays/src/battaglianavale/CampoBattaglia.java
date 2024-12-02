package battaglianavale;
import java.util.Arrays;

public class CampoBattaglia {
    //Attributi
    private int lungCampoBattaglia;
    private int largCampoBattaglia;
    String[][] campo;

    //Costruttore, richiede nome e dimensioni campo
    public CampoBattaglia(int lunghezza, int larghezza) {
        this.lungCampoBattaglia = lunghezza;
        this.largCampoBattaglia = larghezza;
    }

    public String[][] generaCampo() {
        this.campo = new String[this.lungCampoBattaglia][this.largCampoBattaglia];

        return this.campo;
    }

    //stampo campo battaglia
    public void displayField() {
        //usiamo classe arrays
        for (String[] line:this.campo) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println("\n");

    }

    //posiziono nave nel campo
    public void posizionaNave(Nave n, String orientamento, int x, int y) {
        int l = n.getLung();
        String nome = n.getName();
        switch (orientamento) {
            case "NS":
                if (x+l > this.lungCampoBattaglia) {
                    System.out.println("Nave troppo lunga");
                    return;
                }
                for (int i=x;i<x+l;i++) {
                    if (this.campo[i][y]!=null) {
                        System.out.println("Casella occupata");
                        return;
                    }
                }
                while (l>0) {
                    this.campo[x++][y] = nome;
                    l--;
                }
                break;
            case "SN":
                if (x-l < 0) {
                    System.out.println("Nave troppo lunga");
                    return;
                }
                for (int i=x;i>x-l;i--) {
                    if (this.campo[i][y]!=null) {
                        System.out.println("Casella occupata");
                        return;
                    }
                }
                while (l>0) {
                    this.campo[x--][y] = nome;
                    l--;
                }
                break;
            case "EO":
            for (int i=x;i<y+l;i++) {
                if (this.campo[x][i]!=null) {
                    System.out.println("Casella occupata");
                    return;
                }
            }
                if (y+l > this.largCampoBattaglia) {
                    System.out.println("Nave troppo larga");
                    return;
                }
                while (l>0) {
                    this.campo[x][y++] = nome;
                    l--;
                }
                break;
            case "OE":
                if (y-l < 0) {
                    System.out.println("Nave troppo larga");
                    return;
                }
                for (int i=y;i>y-l;i--) {
                    if (this.campo[x][i]!=null) {
                        System.out.println("Casella occupata");
                        return;
                    }
                }
                while (l>0) {
                    this.campo[x][y--] = nome;
                    l--;
                }
                break;
            default:
                System.out.println("orientamento non valido.\n");

        }
        return;
        
    }

}
