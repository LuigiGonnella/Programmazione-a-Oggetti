package condomini;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GestioneCondomini {

	List<Condominio> conds = new ArrayList<>();

	public Condominio aggiungiCondominio(String indirizzo, int numeroCivico, double saldo){
		Condominio c = new Condominio(indirizzo, numeroCivico, saldo);
		if (conds.stream().filter(s->s.getIndirizzo().equals(indirizzo)).count()>0) {
			return conds.stream().filter(s->s.getIndirizzo().equals(indirizzo)).collect(Collectors.toList()).get(0);
		}
		conds.add(c);
		return c;
	}
	
	public Condominio cercaCondominio(String codiceCondominio){
		if (conds.stream().filter(s->s.getCodice().equals(codiceCondominio)).count()==0) {
			return null;
		}
		return conds.stream().filter(s->s.getCodice().equals(codiceCondominio)).collect(Collectors.toList()).get(0);
		
	}

	public Condominio cercaCondominioPerIndirizzo(String codiceCondominio){
		return conds.stream().filter(s->s.getIndirizzo().contains(codiceCondominio)).collect(Collectors.toList()).get(0);
	}


	List<Proprietario> props =new ArrayList<>();
	public Proprietario aggiungiProprietario(String codiceCondominio, String cognome, String nome, int interno, double millesimi, double debito) 
			throws MillesimiSuperatiException{
				Condominio c = this.cercaCondominio(codiceCondominio);
				if (c.elencoProprietari().stream().collect(Collectors.summingDouble(s->s.getMillesimi()))+millesimi>1000) {
					throw new MillesimiSuperatiException();
				}
				this.props=this.props.stream().filter(s->!(s.getCondominio().equals(codiceCondominio) && s.getInterno()==interno)).collect(Collectors.toList());

				Proprietario p = new Proprietario(codiceCondominio, cognome, nome, interno, millesimi, debito);
				c.aggiungiProp(p);
				props.add(p);
		return p;
	}
	
	List<Spesa> spese = new ArrayList<>();
	public Spesa aggiungiSpesa(String codiceCondominio, String descrizione, double importo, String data, boolean pagata,
			double percentualeAmministratore){
				Condominio c = this.cercaCondominio(codiceCondominio);
				if (percentualeAmministratore==0.0) {
					Spesa s = new Spesa(descrizione, importo, data, pagata);
					spese.add(s);
					c.aggiungiSpesa(s);
					if (pagata) {
						c.setSaldo(c.getSaldo()-s.getImporto());
					}
					return s;

				}
				else {
					Spesa s = new SpesaStraordinaria(descrizione, importo, data, pagata, percentualeAmministratore);
					spese.add(s);
					c.aggiungiSpesa(s);
					if(pagata) {
						c.setSaldo(c.getSaldo()-s.getImporto());
					}
					return s;


				}
	}
	
	public void pagaSpese(String codiceCondominio, String da, String a){
		Condominio c = cercaCondominio(codiceCondominio);
		
		List<Spesa> daPagare = cercaCondominio(codiceCondominio).elencoSpeseCondominioAncoraDaPagare().stream().filter(s->s.getData().compareTo(da)>=0 && s.getData().compareTo(a)<=0).collect(Collectors.toList());

		c.setSaldo(c.getSaldo()-daPagare.stream().collect(Collectors.summingDouble(s->s.getImporto())));

		
	} 
	
	public void pagaSpese(String codiceCondominio){
		Condominio c = cercaCondominio(codiceCondominio);
		
		List<Spesa> daPagare = cercaCondominio(codiceCondominio).elencoSpeseCondominioAncoraDaPagare().stream().collect(Collectors.toList());

		c.setSaldo(c.getSaldo()-daPagare.stream().collect(Collectors.summingDouble(s->s.getImporto())));
	} 
	
	public void calcolaDovutoProprietari(String codiceCondominio){
		Condominio c = cercaCondominio(codiceCondominio);

		for (Proprietario p : c.elencoProprietari()) {
			p.setDebito(p.getDebito()+(c.getSaldoin()-c.getSaldo())*p.getMillesimi()/1000);
		}

	}
	
	public void saldaDebito(String codiceCondominio, String cognomeProprietario, String nomeProprietario, double versato) {
		Condominio c = cercaCondominio(codiceCondominio);

		if (c.elencoProprietari().stream().filter(s->s.getProprietario().equals(cognomeProprietario+" "+nomeProprietario)).count()>0) {
			Proprietario p = c.elencoProprietari().stream().filter(s->s.getProprietario().equals(cognomeProprietario+" "+nomeProprietario)).collect(Collectors.toList()).get(0);

			p.setDebito(p.getDebito()-versato);
			c.setSaldo(c.getSaldo()+versato);
		}
	}

	public Collection<Proprietario> elencoProprietariMorosi(String codiceCondominio) {
		Condominio c = cercaCondominio(codiceCondominio);

		return c.elencoProprietari().stream().filter(s->s.getDebito()>0).collect(Collectors.toList());
	}
	
	public void leggiFile(String nomeFile) throws IOException {

		try (Reader src = new FileReader(nomeFile);
			BufferedReader br = new BufferedReader(src)) {
				String line;

				while((line=br.readLine())!=null) {
					Condominio c = cercaCondominio(line.split(";")[0]);
					String desc =line.split(";")[1];
					double importo = Double.valueOf(line.split(";")[2]);
					String data = line.split(";")[3];
					boolean pagata = Boolean.valueOf(line.split(";")[4]);
					double perc = Double.valueOf(line.split(";")[5]);
					if (perc ==0 ) {
						Spesa s = new Spesa(desc, importo, data, pagata);
						c.aggiungiSpesa(s);



					}
					else {
						SpesaStraordinaria s = new SpesaStraordinaria(desc, importo, data, pagata, perc);
						c.aggiungiSpesa(s);

					}

				}
			
		} catch (IOException e) {
			throw e;
		}
	}	
}
