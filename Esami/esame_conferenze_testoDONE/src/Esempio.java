

import conferenze.*;
import java.util.*;

public class Esempio {

	public static void main(String[] args) throws UtenteDuplicatoException, RevisioneRifiutataException {

		SistemaGestione sg = new SistemaGestione();

		System.out.println("******************************************");
		System.out.println("*             R1. CONFERENZE             *");
		System.out.println("******************************************");
		
		System.out.println("\nDefinita nuova conferenza");
		Conferenza c = sg.nuovaConferenza("Learning Technologies", "Los Angeles, USA", 2016, "0301", "0304", 500);

		System.out.println("\nDettagli conferenza:");
		System.out.println(" Acronimo: "+c.getAcronimo());
		System.out.println(" Nome: "+c.getNome());
		System.out.println(" Anno: "+c.getAnno());
		System.out.println(" Luogo: "+c.getLuogo());
		System.out.println(" Dal: "+c.getDataInizio());
		System.out.println(" Al: "+c.getDataFine());

		System.out.println("\nAggiungi sponsor");
		c.aggiungiSponsor("IEEE Education Society");
		c.aggiungiSponsor("McGraw-Hill");
		c.aggiungiSponsor("IEEE Education Society");

		System.out.println("\nElenco sponsor (ordine di inserimento):");
		LinkedList<String> listaSponsor = new LinkedList<String>(c.elencoSponsor());
		for(String stemp : listaSponsor){
			System.out.println(" "+stemp);
		}

		System.out.println("\nDefinite altre conferenze");
		sg.nuovaConferenza("Advanced User Interaction", "Paris, France", 2017, "0515", "0520", 600);
        sg.nuovaConferenza("Learning Technologies", "Rome, Italy", 2017, "0802", "0807", 10000000);
        sg.nuovaConferenza("Learning Technologies", "Rome, Italy", 2017, "0802", "0807", 1000);
		               
		System.out.println("\nElenco conferenze (ordinate per nome e anno):");
		LinkedList<Conferenza> listaConferenze = new LinkedList<Conferenza>(sg.elencoConferenze());
		for(Conferenza ctemp : listaConferenze){
			System.out.println(" "+ctemp.getNome()+" "+ctemp.getAnno()+" ("+ctemp.getAcronimo()+")");
		}

		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("*               R2. UTENTI               *");
		System.out.println("******************************************");
		
		System.out.println("\nDefinito nuovo utente");
		sg.nuovoUtente("John", "Doe", "University of California, Berkeley", "doe@berkeley.edu");

		System.out.println("\nRicerca utente John");
		Utente u = sg.cercaUtente("John");

		System.out.println("\nRicerca utente doe@berkeley.edu");
		u = sg.cercaUtente("doe@berkeley.edu");

		System.out.println("\nDettagli utente:");
		System.out.println(" Nome: "+u.getNome());
		System.out.println(" Cognome: "+u.getCognome());
		System.out.println(" Organizzazione: "+u.getOrganizzazione());
		System.out.println(" Email: "+u.getEmail());
		
		System.out.println("\nDefiniti altri utenti");
		sg.nuovoUtente("Lin", "Le", "Columbia University", "lin.le@columbia.edu");
		sg.nuovoUtente("Mario", "Rossi", "Politecnico di Milano", "m.rossi@polimi.it");
		sg.nuovoUtente("Anna", "Verdi", "Università di Torino", "anna.verdi@unito.it");
		sg.nuovoUtente("Theresa", "Laugh", "University of Toronto", "tl@utoronto.ca");
		
		System.out.println("\nElenco utenti (ordine arbitrario):");
		LinkedList<Utente> listaUtenti = new LinkedList<Utente>(sg.elencoUtenti());
		for(Utente utemp : listaUtenti){
			System.out.println(" "+utemp.getNome()+" "+utemp.getCognome());
		}

		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("*         R3. LAVORI ED AUTORI           *");
		System.out.println("******************************************");

		
		System.out.println("\nSottomesso lavoro alla conferenza LT2016 da doe@berkeley.edu");
		String id = sg.sottomettiLavoro("LT2016", "On the use of Augmented Reality for learning", 'A', "doe@berkeley.edu");
		
		System.out.println("\nId assegnato al lavoro:");
		System.out.println(" "+id);

		System.out.println("\nCerca lavoro LT2016-1");
		Lavoro l = sg.cercaLavoro("LT2016-1");

		System.out.println("\nDettagli lavoro:");
		System.out.println(" Titolo: "+l.getTitolo());
		if(l instanceof Articolo)
			System.out.println(" Tipologia: articolo");
		else
			System.out.println(" Tipologia: poster");
		
		System.out.println("\nAggiungi autori al lavoro");
		sg.aggiungiAutore("LT2016-1", "m.rossi@polimi.it");
		sg.aggiungiAutore("LT2016-1", "lin.le@columbia.edu");
		
		System.out.println("\nElenco autori lavoro (ordine di inserimento):");
		LinkedList<Utente> listaAutori = new LinkedList<Utente>(sg.elencoAutori("LT2016-1"));
		for(Utente utemp : listaAutori){
			System.out.println(" "+utemp.getNome()+" "+utemp.getCognome());
		}

		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("*       R4. REVISIONI E PROGRAMMA        *");
		System.out.println("******************************************");
		
		System.out.println("\nAggiunte revisioni");
		sg.nuovaRevisione("LT2016-1", "anna.verdi@unito.it", "Good paper, should be accepted.", 5);
		sg.nuovaRevisione("LT2016-1", "tl@utoronto.ca", "Not enough new content. I would reject it.", 2);
		
		System.out.println("\nPunteggio medio per il lavoro:");
		System.out.println(" "+l.calcolaPunteggioMedio());

		System.out.println("\nSottomessi altri lavori alla conferenza LT2016");
		sg.sottomettiLavoro("LT2016", "Teaching robotics using Lego Mindstorm", 'P', "tl@utoronto.ca");
		sg.sottomettiLavoro("LT2016", "A review of education technologies in Italy", 'A', "m.rossi@polimi.it");

		System.out.println("\nAggiunte altre revisioni");
		sg.nuovaRevisione("LT2016-2", "anna.verdi@unito.it", "Medium paper, could be accepted.", 4);
		sg.nuovaRevisione("LT2016-3", "anna.verdi@unito.it", "Very bad paper, should not be accepted.", 1);

		System.out.println("\nProgramma conferenza LT2016 (ordinamento per punteggi decrescenti):\n");
		System.out.println(sg.generaProgramma("LT2016"));

		
		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("*               R5. ISCRIZIONI           *");
		System.out.println("******************************************");

		System.out.println("\nIscrizione utente doe@berkeley.edu alla conferenza LT2016");
		
		sg.iscrivi("doe@berkeley.edu","LT2016");

		System.out.println("\nAltre iscrizioni");
		sg.iscrivi("tl@utoronto.ca","LT2016");
		sg.iscrivi("henry.rocks@ucla.edu","LT2016");
		
		System.out.println("\nElenco iscritti alla conferenza LT2016:");
		LinkedList<Utente> listaIscritti = new LinkedList<Utente>(sg.elencoIscritti("LT2016"));
		for(Utente utemp : listaIscritti){
			System.out.println(" "+utemp.getNome()+" "+utemp.getCognome());
		}

		System.out.println("\nIncasso per la conferenza LT2016:");
		System.out.println(" "+sg.calcolaIncasso("LT2016"));


	}

}

