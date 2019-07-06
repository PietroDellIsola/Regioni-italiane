package ricerca.oggetto;

import java.util.ArrayList;

public class Risultato {
	
	/*contiene tutti i dati che ha ottenuto la servlet risultato*/
	private String regioneCliccata;
	private String sigla;
	private String urlStemma;
	private String nomeCapoluogo;
	private ArrayList<Provincia> province;
	private int totaleComuni;
	private int totalePopolazione;
	
	public Risultato() {
		regioneCliccata = " ";
		sigla = " ";
		urlStemma = " ";
		nomeCapoluogo = " ";
		province = new ArrayList<Provincia>();
		totaleComuni = 0;
		totalePopolazione = 0;
	}

	public String getRegioneCliccata() {
		return regioneCliccata;
	}

	public void setRegioneCliccata(String regioneCliccata) {
		this.regioneCliccata = regioneCliccata;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getUrlStemma() {
		return urlStemma;
	}

	public void setUrlStemma(String urlStemma) {
		this.urlStemma = urlStemma;
	}

	public String getNomeCapoluogo() {
		return nomeCapoluogo;
	}

	public void setNomeCapoluogo(String nomeCapoluogo) {
		this.nomeCapoluogo = nomeCapoluogo;
	}

	public ArrayList<Provincia> getProvince() {
		return province;
	}

	public void setProvince(ArrayList<Provincia> province) {
		this.province = province;
	}

	public void addProvince(Provincia nuovaProvincia) {
		this.province.add(nuovaProvincia);
	}
	
	public int getTotaleComuni() {
		return totaleComuni;
	}

	public void setTotaleComuni(int totaleComuni) {
		this.totaleComuni = totaleComuni;
	}

	public int getTotalePopolazione() {
		return totalePopolazione;
	}

	public void setTotalePopolazione(int totalePopolazione) {
		this.totalePopolazione = totalePopolazione;
	}
	
	public String toString() {
		String ris="";
		ris = regioneCliccata+"\n"+sigla+"\n"+urlStemma+"\n"+nomeCapoluogo+"\n";
		for(Provincia p : province) {
			ris = ris + p.toString()+"\n";
		}
		
		ris = ris + "totalePopolazione: "+totalePopolazione +"\ntotaleComuni: "+totaleComuni; 
		
		return ris;
	}
	

}
