package control.utente;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AccountBean;
import bean.AccountBean.Ruolo;
import exception.NotFoundException;
import manager.AccountManager;


@WebServlet("/CambiaMailServlet")
public class CambiaMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AccountManager manager;
	
    public CambiaMailServlet() {
        super();
        manager= new AccountManager();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String newMail=request.getParameter("newMail");
		AccountBean account=(AccountBean)request.getSession().getAttribute("Account");
		try {
			manager.modificaMail(account.getMail(), newMail);
			request.getSession().setAttribute("emailModificata",true);
		} catch (SQLException | NotFoundException e) {
			//Non pu� succedere per via dei filtri
			//Vanno quindi eliminate le eccezioni?
		}
		if(account.getTipo().equals(Ruolo.Utente))
			response.sendRedirect(request.getContextPath()+"\\HomepageUtente.jsp");
		else
			response.sendRedirect(request.getContextPath()+"\\HomepageSupervisore.jsp");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}