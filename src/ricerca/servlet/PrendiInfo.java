package ricerca.servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class prendiInfo
 */
@WebServlet("/prendiInfo")
public class PrendiInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrendiInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONArray jsonArray = new JSONArray();
		
		String regioneCliccata = request.getParameter("regione");
		
		//Scarico la pagina
		Document doc = Jsoup.connect("https://www.tuttitalia.it/"+regioneCliccata).get();
		
		Elements h1 = doc.getElementsByTag("h1");
		JSONObject regione = new JSONObject();
		regione.put("regione", h1.get(0).text());
		jsonArray.put(regione);
		/*Prendo il nome della regione dalla pagina
				(potremme essere scritto in diverso modo per la richesta. es:
					se nella richiesta è scritto "campania", sulla pania Campania
				)*/
		
		//Scarico tutte le tabelle presenti nella pagina
		Elements tabelle = doc.getElementsByTag("table");
		
		/*tabelle che servono:
		 * tabella con classe "uj", posizione 0
		 * class = "ut", in posizione 7 (quella con province)
		 * senza classe(è in un div), in posizione 8 (quella con città maggiori)
		 * */
		
		Element tabellaTop = tabelle.get(0);
		Elements celleTabella = tabellaTop.getElementsByTag("tr"); /*prelevo le righe della tabella*/
		String nomeCapoluogostr = "";
		String riga = "";
		
		/*prelevo solo le informazioni che mi servono sigla, urlStemma e nomeCapoluogo*/
		Element c = celleTabella.get(0);
		Element el = (c.getElementsByTag("td")).get(2);
		JSONObject urlStemma = new JSONObject();
		urlStemma.put("urlStemma", (el.getElementsByTag("img")).attr("src"));
		jsonArray.put(urlStemma);
		
		c = celleTabella.get(1);
		riga = c.text();
		JSONObject sigla = new JSONObject();
		sigla.put("sigla", riga.substring(riga.indexOf(" ")+1));
		jsonArray.put(sigla);
		
		c = celleTabella.get(celleTabella.size()-1);
		nomeCapoluogostr = c.text();
		nomeCapoluogostr = nomeCapoluogostr.substring(nomeCapoluogostr.indexOf(" ")+1);
		nomeCapoluogostr = nomeCapoluogostr.substring(0,nomeCapoluogostr.indexOf(" "));
		JSONObject nomeCapoluogo = new JSONObject();
		nomeCapoluogo.put("nomeCapoluogo", nomeCapoluogostr);
		jsonArray.put(nomeCapoluogo);
	
		/*tabella province*/
		Element tabellaProvince = tabelle.get(7);
		celleTabella = tabellaProvince.getElementsByTag("tr"); /*prelevo le righe della tabella*/
		
		String provincia = "", nomeProvincia = "", popolazione = "", numComuni = "";
		int totaleComuni = 0, totalePopolazione = 0;
		
		for(int i = 1; i<celleTabella.size()-1; i++) { 
			/*
			 * la prima riga contiene l'intestazione della tabella che non serve
			 * l'ultima riga contiene il totale di popolazioen e numeri comuni che posso calcolarmi*/
			
			JSONObject nuovaProvincia = new JSONObject();
			
			c = celleTabella.get(i);
			
			riga = c.text();
			provincia = riga.substring(0,2); //esempio SA
			provincia = provincia.trim();
			riga = riga.substring(2);
			nuovaProvincia.put("provincia", provincia);
			
			numComuni = riga.substring(riga.lastIndexOf(" "));
			riga = riga.substring(0, riga.lastIndexOf(" "));
			numComuni = numComuni.trim();
			nuovaProvincia.put("numComuni", numComuni);
			
			popolazione = riga.substring(riga.lastIndexOf(" "));
			riga = riga.substring(0, riga.lastIndexOf(" "));
			popolazione = popolazione.trim();
			for(;popolazione.contains(".");) /*rimuovo l'ventuale punto*/
				popolazione = popolazione.substring(0,popolazione.indexOf('.'))+
						popolazione.substring(popolazione.indexOf('.')+1);
			nuovaProvincia.put("popolazione", popolazione);
			
			nomeProvincia = riga;
			nomeProvincia = nomeProvincia.trim();
			nuovaProvincia.put("nomeProvincia", nomeProvincia);
			totalePopolazione = totalePopolazione + Integer.parseInt(popolazione);
			totaleComuni = totaleComuni + Integer.parseInt(numComuni);
			
			jsonArray.put(nuovaProvincia);
		}
		
		JSONObject totaleComuniJ = new JSONObject();
		totaleComuniJ.put("totaleComuni", totaleComuni);
		jsonArray.put(totaleComuniJ);
		JSONObject totalePopolazioneJ = new JSONObject();
		totalePopolazioneJ.put("totalePopolazione", totalePopolazione);
		jsonArray.put(totalePopolazioneJ);
		
		try {
            request.setAttribute("jsonArray", jsonArray);
            RequestDispatcher rd = request.getRequestDispatcher("risultato.jsp");  
            rd.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
