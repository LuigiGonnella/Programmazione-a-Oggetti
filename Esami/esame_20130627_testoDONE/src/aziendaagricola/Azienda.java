package aziendaagricola;

import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Azienda {

	List<Zona> zone = new ArrayList<>();

	public Zona aggiungiZona(){
		Zona z = new Zona();
		z.setCodice(zone.size()+1000);
		zone.add(z);
		
		
		return z;
	}
	
	public Zona cercaZona(int codice){
		if (codice-1000>=zone.size()) {
			Zona z = new Zona();
			z.setCodice(codice);
			zone.add(z);
		}

		return zone.stream().filter(s->s.getCodice()==codice).collect(Collectors.toList()).get(0);
	}
	
	public void specificaCaratteristicheZona(int codice, String caratteristica){

		Zona z = cercaZona(codice);
		if (!z.elencoCaratteristiche().contains(caratteristica)) {
			z.aggiungiCaratteristica(caratteristica);
		}


	}
	
	public Collection<Zona> elencoZone(){
		
		return this.zone.stream().sorted(Comparator.comparing(s->s.getCodice())).collect(Collectors.toList());
	}
	
	public Collection<Zona> elencoZone(int ampiezza){
		
		return this.zone.stream().filter(s->s.getAmpiezza()>ampiezza).sorted(Comparator.comparing(s->s.getCodice())).collect(Collectors.toList());
	}

	List<Magazzino> mags = new ArrayList<>();
	
	public Magazzino aggiungiMagazzino(String nome, String prodotto, int quantitaStoccabile){
		Magazzino m = new Magazzino();
		m.setNome(nome);
		m.setProdotto(prodotto);
		m.setQuantitaStoccabile(quantitaStoccabile);

		mags.add(m);

		

		return m;		
	}
	
	public int totaleMagazzino(){
		
		return this.mags.stream().collect(Collectors.summingInt(s->s.getQuantitaStoccata()));
	}
	
	public void leggi(String nomeFile) throws IOException{

		try (Reader src = new FileReader(nomeFile);
		BufferedReader br = new BufferedReader(src)) {

			String line;

			while ((line=br.readLine())!=null) {
				if (line.split(";")[0].equals("C")) {
					int codZona = Integer.valueOf(line.split(";")[1]);
					String prodotto=line.split(";")[2];
					int meseSemina = Integer.valueOf(line.split(";")[3]);
					int meseRaccolta = Integer.valueOf(line.split(";")[4]);


					try {
						cercaZona(codZona).aggiungiColtivazione(prodotto, meseSemina, meseRaccolta);
						
					} catch (ColtivazioneDuplicataException e) {
						return;
					}
					

				}
				else {
					int codZona = Integer.valueOf(line.split(";")[1]);
					String prodotto=line.split(";")[2];
					String data = line.split(";")[3];
					int quantita = Integer.valueOf(line.split(";")[4]);

					cercaZona(codZona).nuovoRaccolto(prodotto, data, quantita);


				}

			}
			
		} catch (IOException e) {
			throw e;
		}

	}
	
}
