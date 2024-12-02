import ufficiotecnico.*;

import java.io.IOException;
import java.util.*;

public class Esempio {

	public static void main(String[] args) throws EccezioneElementoTopograficoInesistente, EccezioneIncrocioImpossibile, IOException {

		BaseDatiTopografica bdt=new BaseDatiTopografica();
		ElementoTopografico et;
		List<ElementoTopografico> listaElementiTopografici;
		List<Strada> listaStrade;
		List<Piazza> listaPiazze;
		List<Monumento> listaMonumenti;
		List<String> listaMateriali;

		Monumento m;

		System.out.println("####### R1 - ELEMENTI TOPOGRAFICI #######");
		
		System.out.println("\nAggiungo elemento topografico di tipo strada");
		et = bdt.aggiungiElementoTopografico("strada");
		System.out.println("Aggiunto elemento topografico:");
		System.out.println(" Codice generato: "+et.getCodice());
		System.out.println(" Classe oggetto: "+et.getClass().getSimpleName());
	
		System.out.println("\nImposto nome e lunghezza della strada");
		((Strada)et).setNome("Roma, Via");
		((Strada)et).setLunghezza(1500);
		System.out.println("Impostati nome e lunghezza:");
		System.out.println(" Nome: "+((Strada)et).getNome());
		System.out.println(" Lunghezza: "+((Strada)et).getLunghezza()+" m");
		
		System.out.println("\nAggiungo elemento topografico di tipo piazza");
		et = bdt.aggiungiElementoTopografico("PiAzzA");
		System.out.println("Aggiunto elemento topografico:");
		System.out.println(" Codice generato: "+et.getCodice());
		System.out.println(" Classe oggetto: "+et.getClass().getSimpleName());
	
		System.out.println("\nImposto nome, forma ed estensione della piazza");
		((Piazza)et).setNome("Napoli, Piazza");
		((Piazza)et).setForma("Rettangolare");
		((Piazza)et).setEstensione(3500);
		System.out.println("Impostati nome, forma ed estensione:");
		System.out.println(" Nome: "+((Piazza)et).getNome());
		System.out.println(" Forma: "+((Piazza)et).getForma());
		System.out.println(" Estensione: "+((Piazza)et).getEstensione()+" m^2");

		System.out.println("\nAggiungo elemento topografico di tipo strada");
		et = bdt.aggiungiElementoTopografico("strada");
		System.out.println("Aggiunto elemento topografico:");
		System.out.println(" Codice generato: "+et.getCodice());
		System.out.println(" Classe oggetto: "+et.getClass().getSimpleName());

		System.out.println("\nImposto nome e lunghezza della strada");
		((Strada)et).setNome("Orbassano, Largo");
		((Strada)et).setLunghezza(700);
		System.out.println("Impostati nome e lunghezza:");
		System.out.println(" Nome: "+((Strada)et).getNome());
		System.out.println(" Lunghezza: "+((Strada)et).getLunghezza()+" m");

		System.out.println("\nElenco alfabetico di tutti gli elementi topografici:");
		listaElementiTopografici = new LinkedList<ElementoTopografico>(bdt.elencoElementiTopografici());
		for(ElementoTopografico etemp : listaElementiTopografici){
			System.out.println(" "+etemp.getNome());
		}
		
		System.out.println("\nElenco alfabetico delle piazze:");
		listaPiazze = new LinkedList<Piazza>(bdt.elencoPiazze());
		for(Piazza ptemp : listaPiazze){
			System.out.println(" "+ptemp.getNome());
		}
		
		System.out.println("\nElenco delle strade, ordinate per nome:");
		listaStrade = new LinkedList<Strada>(bdt.elencoStradePerNome());
		for(Strada stemp : listaStrade){
			System.out.println(" "+stemp.getNome()+" ("+stemp.getLunghezza()+" m)");
		}
		
		System.out.println("\nElenco delle strade, ordinate per lunghezza decrescente (dalla piu' lunga alla piu' corta):");
		listaStrade = new LinkedList<Strada>(bdt.elencoStradePerLunghezza());
		for(Strada stemp : listaStrade){
			System.out.println(" "+stemp.getNome()+" ("+stemp.getLunghezza()+" m)");
		}
		
		System.out.println("\nCerco elemento topografico con codice 1");
		et = bdt.cercaElementoTopografico(1);
		if(et!=null && et.getCodice()==1)
		{
			System.out.println("Elemento topografico trovato:");
			System.out.println(" Nome: "+et.getNome());
		}
		else
			System.out.println("Elemento topografico non trovato (codice inesistente)");
					
		System.out.println("\nCerco elemento topografico con codice 10001");
		et = bdt.cercaElementoTopografico(10001);
		if(et!=null && et.getCodice()==10001)
		{
			System.out.println("Elemento topografico trovato:");
			System.out.println(" Nome: "+et.getNome());
		}
		else
			System.out.println("Elemento topografico non trovato (codice inesistente)");

		System.out.println("\nAggiungo un altro elemento topografico di tipo piazza");
		et = bdt.aggiungiElementoTopografico("piazza");
		System.out.println("Aggiunto elemento topografico:");
		System.out.println(" Codice generato: "+et.getCodice());
		System.out.println(" Classe oggetto: "+et.getClass().getSimpleName());
	
		System.out.println("\nImposto nome, forma ed estensione della piazza");
		((Piazza)et).setNome("Capri, Piazza");
		((Piazza)et).setForma("Quadrata");
		((Piazza)et).setEstensione(4000);
		System.out.println("Impostati nome, forma ed estensione:");
		System.out.println(" Nome: "+((Piazza)et).getNome());
		System.out.println(" Forma: "+((Piazza)et).getForma());
		System.out.println(" Estensione: "+((Piazza)et).getEstensione()+" m^2");
		
		System.out.println("\nEstensione complessiva delle piazze della citta':");
		double estensione = bdt.estensionePiazze();
		System.out.println(" "+estensione+" m^2");
		
		
		System.out.println("\n\n####### R2 - MONUMENTI #######");

		et = bdt.cercaElementoTopografico(10001);
		System.out.println("\nAggiungo un monumento alla piazza 10001");
		m=((Piazza)et).aggiungiMonumento("San Gennaro", "1994-06-24", "Mario Bianchi", 'C');
		System.out.println("Monumento aggiunto:");
		System.out.println(" Personaggio (o fatto) rappresentato: "+m.getRappresenta());
		System.out.println(" Data: "+m.getData());
		System.out.println(" Artista: "+m.getArtista());
		System.out.println(" Posizione: "+m.getPosizione());

		System.out.println("\nAggiungo un altro monumento alla piazza 10001");
		m=((Piazza)et).aggiungiMonumento("Quattro giornate di Napoli", "1987-05-12", "Alberto Blu", 'E');
		System.out.println("Monumento aggiunto:");
		System.out.println(" Personaggio (o fatto) rappresentato: "+m.getRappresenta());
		System.out.println(" Data: "+m.getData());
		System.out.println(" Artista: "+m.getArtista());
		System.out.println(" Posizione: "+m.getPosizione());

		System.out.println("\nElenco monumenti della piazza 10001:");
		listaMonumenti = new LinkedList<Monumento>(bdt.elencoMonumenti(10001));
		for(Monumento mtemp : listaMonumenti)
			System.out.println(" "+mtemp.getRappresenta()+" inaugurata il "+mtemp.getData()+" ("+mtemp.getPosizione()+")");

		System.out.println("\nAggiungo materiali al monumento");
		m.aggiungiMateriale("Marmo");
		m.aggiungiMateriale("Oro");
		m.aggiungiMateriale("Legno");
		System.out.println("Elenco materiali aggiunti:");
		listaMateriali = new LinkedList<String>(m.elencoMateriali());
		for(String mtemp : listaMateriali)
			System.out.println(" "+mtemp);
		
		System.out.println("\nVerifica presenza di un monumento nella posizione 'C' della piazza");
		boolean presente = ((Piazza)et).monumentoPresenteInPosizione('C');
		if(presente)
			System.out.println("Monumento presente");
		else
			System.out.println("Monumento assente");

		System.out.println("\nVerifica presenza di un monumento nella posizione 'N' della piazza");
		        presente = ((Piazza)et).monumentoPresenteInPosizione('N');
		if(presente)
			System.out.println("Monumento presente");
		else
			System.out.println("Monumento assente");
				
		
		System.out.println("\n\n####### R3 - INCROCI #######");
		
		System.out.println("\nDefinisco incrocio Roma, Via - Orbassano, Largo (controllato da semafori)");
		bdt.definisciIncrocio(1, 2, true);
		System.out.println("Incrocio aggiunto");

		System.out.println("\nDefinisco incrocio Roma, Via - Napoli, Piazza (non controllato da semafori)");
		bdt.definisciIncrocio(1, 10001, false);
		System.out.println("Incrocio aggiunto");
		
		System.out.println("\nAggiungo un ulteriore elemento topografico di tipo strada");
		et = bdt.aggiungiElementoTopografico("strada");
		System.out.println("Aggiunto elemento topografico:");
		System.out.println(" Codice generato: "+et.getCodice());
		((Strada)et).setNome("Firenze, Via");
		((Strada)et).setLunghezza(1000);
		System.out.println(" Nome: "+((Strada)et).getNome());
		System.out.println(" Lunghezza: "+((Strada)et).getLunghezza()+" m");
		
		System.out.println("\nDefinisco incrocio Roma, Via - Firenze, Via (controllato da semafori)");
		bdt.definisciIncrocio(1, 3, true);
		System.out.println("Incrocio aggiunto");
		
		System.out.println("\nElenco degli incroci con Roma, via (prima le strade in ordine alfabetico, poi le piazze):");
		listaElementiTopografici = new LinkedList<ElementoTopografico>(bdt.elencoIncroci(1));
		for(ElementoTopografico etemp : listaElementiTopografici){
			System.out.println(" "+etemp.getNome());
		}

		System.out.println("\nElimino incrocio Roma, Via - Firenze, Via");
		boolean eliminato = bdt.eliminaIncrocio(1, 3);
		if(eliminato)
			System.out.println("Incrocio eliminato");
		else
			System.out.println("Incrocio non eliminato");
			
		
		System.out.println("\n\n####### R4 - CARICAMENTO DA FILE #######");
		
		System.out.println("\nLeggo altri elementi topografici ed incroci dal file input.txt ed aggiorno la struttura dati");
		bdt.leggi("input.txt");

		System.out.println("\nElenco alfabetico di tutti gli elementi topografici (vecchi e nuovi):");
		listaElementiTopografici = new LinkedList<ElementoTopografico>(bdt.elencoElementiTopografici());
		for(ElementoTopografico etemp : listaElementiTopografici){
			System.out.println(" "+etemp.getNome()+" ("+etemp.getCodice()+")");
		}
		
		System.out.println("\nElenco degli incroci con Napoli, Piazza (prima le strade in ordine alfabetico, poi le piazze):");
		listaElementiTopografici = new LinkedList<ElementoTopografico>(bdt.elencoIncroci(10001));
		for(ElementoTopografico etemp : listaElementiTopografici){
			System.out.println(" "+etemp.getNome());
		}

	}

}
