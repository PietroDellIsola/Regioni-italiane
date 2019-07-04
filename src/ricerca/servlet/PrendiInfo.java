package ricerca.servlet;


import java.io.IOException;
import java.io.PrintWriter;

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
		System.out.print("dentro servlet "+regioneCliccata);
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
