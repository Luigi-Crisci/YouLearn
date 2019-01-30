package control.corso;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AccountBean;
import manager.CorsoManager;

/**
 * Conferma un corso e lo mette in attesa di validazione
 */
@WebServlet("/ConfermaCorsoServlet")
public class ConfermaCorsoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    CorsoManager manager;
	
    public ConfermaCorsoServlet() {
        super();
        manager=new CorsoManager();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		AccountBean account=(AccountBean)request.getSession().getAttribute("account");
		int idCorso=Integer.parseInt(request.getParameter("idCorso"));
		try {
			manager.confermaCorso(account.getCorsoTenuto(idCorso));
			response.sendRedirect(request.getContextPath()+"//HomepageUtente.jsp");
		}catch (Exception e) {
			e.printStackTrace();
			//TODO SI DEVONO GESTIRE
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}