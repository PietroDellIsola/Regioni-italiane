package ricerca.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		String regioneCliccata = request.getParameter("regione");
		
		//Scarico la pagina
		Document doc = Jsoup.connect("https://www.tuttitalia.it/"+regioneCliccata).get();
		
		Elements h1 = doc.getElementsByTag("h1");
		String nomeRegione = h1.get(0).text();
		
		//Scarico tutte le tabelle presenti nella pagina
		Elements tabelle = doc.getElementsByTag("table");
		
		/*tabelle che servono:
		 * tabella con classe "uj", posizione 0
		 * class = "ut", in posizione 7 (quella con province)
		 * senza classe(è in un div), in posizione 8 (quella con città maggiori)
		 * */
		
		Element tabellaTop = tabelle.get(0);
		Elements celleTabella = tabellaTop.getElementsByTag("tr"); /*prelevo le righe della tabella*/
		String sigla = "", urlStemma = "", nomeCapoluogo = "";
		String riga = "";
		
		/*prelevo solo le informazioni che mi servono sigla, stemma e nomeCapoluogo*/
		Element c = celleTabella.get(0);
		Element el = (c.getElementsByTag("td")).get(2);
		urlStemma = (el.getElementsByTag("img")).attr("src");
		
		c = celleTabella.get(1);
		riga = c.text();
		sigla = riga.substring(riga.indexOf(" ")+1);
		
		c = celleTabella.get(celleTabella.size()-1);
		nomeCapoluogo = c.text();
		nomeCapoluogo = nomeCapoluogo.substring(nomeCapoluogo.indexOf(" ")+1);
		nomeCapoluogo = nomeCapoluogo.substring(0,nomeCapoluogo.indexOf(" "));
		
		/*tabella province*/
		Element tabellaProvince = tabelle.get(7);
		celleTabella = tabellaProvince.getElementsByTag("tr"); /*prelevo le righe della tabella*/
		
		String provincia = "", nomeProvincia = "", popolazione = "", numComuni = "";
		int totaleComuni = 0, totalePopolazione = 0;
		
		for(int i = 1; i<celleTabella.size()-1; i++) { 
			/*
			 * la prima riga contiene l'intestazione della tabella che non serve
			 * l'ultima riga contiene il totale di popolazioen e numeri comuni che posso calcolarmi*/
		
			c = celleTabella.get(i);
			
			riga = c.text();
			provincia = riga.substring(0,2); //esempio SA
			provincia = provincia.trim();
			riga = riga.substring(2);
			
			numComuni = riga.substring(riga.lastIndexOf(" "));
			riga = riga.substring(0, riga.lastIndexOf(" "));
			numComuni = numComuni.trim();
			
			popolazione = riga.substring(riga.lastIndexOf(" "));
			riga = riga.substring(0, riga.lastIndexOf(" "));
			popolazione = popolazione.trim();
			for(;popolazione.contains(".");) /*rimuovo l'ventuale punto*/
				popolazione = popolazione.substring(0,popolazione.indexOf('.'))+
						popolazione.substring(popolazione.indexOf('.')+1);
			
			nomeProvincia = riga;
			nomeProvincia = nomeProvincia.trim();
			
			totalePopolazione = totalePopolazione + Integer.parseInt(popolazione);
			totaleComuni = totaleComuni + Integer.parseInt(numComuni);
			
		}
		
		/*
		 * //String dati="2018_0_18_1_27_2_29_3_15_4_16_5_50_6_66_7_34_8_5_9_9_10_44_11_10";//Stringa realizzata scorrendo la lista di operazioni ritornata dalla DAO
		 *    
		 *    JSONObject jsonObject = new JSONObject();
		 *
	     *   jsonObject.put(dati, 0);
	     *   
	     *   response.getWriter().write(jsonObject.toString());
		 */
			
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
