import aziendaagricola.*;

import java.io.IOException;
import java.util.*;

public class Esempio {

	public static void main(String[] args) throws ColtivazioneDuplicataException, ProdottoNonCompatibileException {

		Azienda a = new Azienda();

		System.out.println("############# R1 - Zone #############");
		
		Zona za = a.aggiungiZona();

		System.out.println("\nAggiunta zona "+za.getCodice());
		
		za.setAmpiezza(500);
		za.setTemperaturaMedia(25);
		za.setIrraggiamentoMedio(9);

		System.out.println(" Ampiezza: "+za.getAmpiezza()+" m^2");
		System.out.println(" Temperatura media: "+za.getTemperaturaMedia()+" C");
		System.out.println(" Irraggiamento medio: "+za.getIrraggiamentoMedio()+ " ore");

		
	    a.specificaCaratteristicheZona(za.getCodice(), "Debolmente acido");
	    a.specificaCaratteristicheZona(za.getCodice(), "Assorbimento meccanico alto");
	    a.specificaCaratteristicheZona(za.getCodice(), "Potere tampone nella media");
	    a.specificaCaratteristicheZona(za.getCodice(), "Assorbimento meccanico alto");

		System.out.println("\nDefinite caratteristiche per la zona "+za.getCodice());
	    	
		List<String> caratteristiche = new LinkedList<String>(za.elencoCaratteristiche());
		
		
		for (String s : caratteristiche)
		{
			System.out.println(" "+s);
		}
		
		Zona zb = a.aggiungiZona();
		zb.setAmpiezza(100);
		System.out.println("\nAggiunta zona "+zb.getCodice());
		Zona zc = a.aggiungiZona();
		zc.setAmpiezza(800);
		System.out.println("\nAggiunta zona "+zc.getCodice());
		
		System.out.println("\nElenco zone (tutte)");

		List<Zona> zone = new LinkedList<Zona>(a.elencoZone());
		
		for (Zona ztemp : zone)
		{
			System.out.println(" "+ztemp.getCodice());
		}

		System.out.println("\nElenco zone (solo quelle ampiezza maggiore di 200 m^2)");

		           zone = new LinkedList<Zona>(a.elencoZone(200));
		
		for (Zona ztemp : zone)
		{
			System.out.println(" "+ztemp.getCodice());
		}

		System.out.println("\nCercata la zona 1003");
		Zona zonaCercata = a.cercaZona(1002);
		if(zonaCercata.getCodice()==1002)
			System.out.println(" Zona trovata o aggiunta");
		
		
		System.out.println("\n############# R2 - Coltivazioni #############");

		System.out.println("\nAggiunte coltivazioni alla zona "+za.getCodice());
		
		Coltivazione c = za.aggiungiColtivazione("Zucchine", 4, 6);
		                 za.aggiungiColtivazione("Cavolo", 10, 12);
		                 za.aggiungiColtivazione("Fagiolini", 7, 9);
		                 
		List<Coltivazione> coltivazioni = new LinkedList<Coltivazione>(za.elencoColtivazioni());
		
		for (Coltivazione ctemp : coltivazioni)
		{
			System.out.println(" "+ctemp.getProdotto()+" semina nel mese "+ctemp.getMeseSemina()+" raccolta nel mese "+ctemp.getMeseRaccolta());
		}

		System.out.println("\nColtivazione di "+c.getProdotto()+" richiede");
		
		Fertilizzante f1 = c.richiedeFertilizzante('N', "Letame", 20, 4, 60);
		FertilizzanteArtificiale f2 = (FertilizzanteArtificiale)c.richiedeFertilizzante('A', "Nitrato potassico", 120, 1, 20);

		f2.setRischio(99);
		
		System.out.println(" Richiede: "+f1.getNome());
		System.out.println(" Richiede: "+f2.getNome()+" (rischio "+f2.getRischio()+")");
		
		
		System.out.println("\n############# R3 - Raccolti #############");
		
		System.out.println("\nCreati raccolti per la zona "+za.getCodice());
		
		Raccolto ra = za.nuovoRaccolto("Fagiolini", "2012-08-28", 900);
 		Raccolto rb = za.nuovoRaccolto("Fagiolini", "2012-09-02", 200);	
 		Raccolto rc	= za.nuovoRaccolto("Zucchine", "2012-06-12", 500);
					   
		ra.setPrezzoIngrosso(200);
		
		System.out.println("\nCercato raccolto di Fagiolini del 2012-08-25 per la zona "+za.getCodice());
		
		ra = za.cercaRaccolto("Fagiolini", "2012-08-28");
		System.out.println(" Quantita' raccolta: "+ra.getQuantita());
		System.out.println(" Prezzo all'ingrosso: "+ra.getPrezzoIngrosso());
		
		System.out.println("\nElenco raccolti per la zona "+za.getCodice());

		List<Raccolto> raccolti = new LinkedList<Raccolto>(za.elencoRaccolti());
		
		for (Raccolto rtemp : raccolti)
		{
			System.out.println(" "+rtemp.getProdotto()+" "+rtemp.getData());
		}
		
		System.out.println("\nAggiunto magazzino");
		Magazzino m = a.aggiungiMagazzino("Deposito centrale", "Fagiolini", 1000);
		System.out.println(" "+m.getNome()+" di "+m.getProdotto()+" (quantita' stoccabile iniziale "+m.getQuantitaStoccabile()+")");
		
		System.out.println("\nTentativo di stoccaggio del raccolto di Fagiolini del 2012-08-25");
		
		int q = m.stocca(ra);
		System.out.println(" Quantita ancora stoccabile (o eccesso, se negativo): "+q);

		System.out.println("\nPrelievo di una quantita' 30 di prodotto dal magazzino");
		m.preleva(30);
		
		System.out.println("\nTentativo di stoccaggio del raccolto di Fagiolini del 2012-08-28");
		
		    q = m.stocca(rb);
		System.out.println(" Quantita ancora stoccabile (o eccesso non stoccato, se negativo): "+q);

		System.out.println("\nQuantita' di prodotto complessivamente stoccata nei vari magazzini");

		m = a.aggiungiMagazzino("Deposito secondario", "Zucchine", 700);
		m.stocca(rc);
			
		int t = a.totaleMagazzino();
		System.out.println(" "+t);
		
		System.out.println("\n############# R4 - Caricamento da file #############");
		
		System.out.println("\nLettura delle informazioni sui raccolti dal file input.txt");
		try {
			a.leggi("input.txt");
			
			Zona zf = a.cercaZona(1001); 
			
			System.out.println("\nColtivazioni lette da file per la zona 1001");
			
			coltivazioni = new LinkedList<Coltivazione>(zf.elencoColtivazioni());
			
			for (Coltivazione ctemp : coltivazioni)
			{
				System.out.println(" "+ctemp.getProdotto()+" semina nel mese "+ctemp.getMeseSemina()+" raccolta nel mese "+ctemp.getMeseRaccolta());
			}

			System.out.println("\nRaccolti letti da file per la zona 1001");
			
			raccolti = new LinkedList<Raccolto>(zf.elencoRaccolti());
			
			for (Raccolto rtemp : raccolti)
			{
				System.out.println(" "+rtemp.getProdotto()+" "+rtemp.getData());
			}
	
			
			
		} catch (IOException e) {
			System.out.println("Errore nella lettura da file!\n");
			e.printStackTrace();
		}

	}

}
