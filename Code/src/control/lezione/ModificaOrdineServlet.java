package control.lezione;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Consumer;

import javax.naming.NoPermissionException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bean.AccountBean;
import bean.CorsoBean;
import bean.LezioneBean;
import exception.DatiErratiException;
import exception.NotFoundException;
import exception.NotWellFormattedException;
import manager.LezioneManager;


@WebServlet("/ModificaOrdineServlet")
public class ModificaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	LezioneManager manager;
    
    public ModificaOrdineServlet() {
        super();
        manager=new LezioneManager();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
    		AccountBean account=(AccountBean) request.getSession().getAttribute("account");
    		int idCorso=Integer.parseInt(request.getParameter("idCorso"));
        	int idLezione=Integer.parseInt(request.getParameter("idLezione"));
        	String coppie=request.getParameter("coppie");
        	
        	
        	manager.modificaOrdine(idCorso, coppie); 
        	request.getSession().setAttribute("updated", "true"); //fa ricaricare le lezioni
        	//Setto un attributo per dire che � andato a buon fine
        	//Setto il nuovo nome della lezione
        	response.sendRedirect(request.getContextPath()+"/SettingLezione.jsp?idCorso="+idCorso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotWellFormattedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			
		} catch (DatiErratiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoPermissionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}