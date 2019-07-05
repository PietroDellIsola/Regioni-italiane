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
		
		//prendo la prima tabella dalla pagina scaricata in precedenza
		Elements tabelle = doc.getElementsByTag("table");
		
		/*Scaricare anche il div in alto con classe "ye"*/
		
		/*tabelle che servono:
		 * class = "ut", in posizione 7 
		 * senza classe(è in un div), in posizione 8 
		 * */
		
		int i = 0;
		for(Element t : tabelle) {
			System.out.println("TABELLA "+i+"\n"+t);
			i++;
		}
		
		/*
		        try {
		            response.setContentType("text/html");
		            PrintWriter writer = response.getWriter();
		           
		            writer.println(p);
		            System.out.print("aggiungo    "+p);
		            /* writer.println("<html><body>");
		            writer.println("<p><u>Valori delle variabili locali al metodo" + " doPost()</u> <br />");
		           
		            writer.println("</body></html>");*/
		/*            writer.close();
		        } catch (Exception exc) {
		            exc.printStackTrace();
		        }
		    
		    }
		   
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
