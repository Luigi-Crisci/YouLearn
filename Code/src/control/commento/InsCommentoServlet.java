package control.commento;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AccountBean;
import bean.CommentoBean;
import bean.LezioneBean;
import exception.NotFoundException;
import exception.NotWellFormattedException;
import manager.LezioneManager;

@WebServlet("/InsCommentoServlet")
public class InsCommentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	LezioneManager manager;
	
    public InsCommentoServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		manager=LezioneManager.getIstanza(getServletContext().getRealPath(""));
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String testo=request.getParameter("testoCommento");
		int idLezione=Integer.parseInt(request.getParameter("idLezione"));
		AccountBean account=(AccountBean)request.getSession().getAttribute("account");
		LezioneBean lezione=new LezioneBean();
		lezione.setIdLezione(idLezione);
		CommentoBean commento=new CommentoBean(null, testo,lezione,account);
		try {
			manager.insCommento(commento);
			request.getSession().setAttribute("commentoInserito", "true");
			response.sendRedirect(request.getContextPath()+"\\Lezione.jsp?idLezione="+idLezione);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath()+File.separator+"Error.jsp");
		} catch (NotWellFormattedException e) {
			request.getSession().setAttribute("commentoInserito", "false");
			response.sendRedirect(request.getContextPath()+File.separator+"Lezione.jsp?idLezione="+idLezione);
			e.printStackTrace();
		} catch (NotFoundException e) {
			response.sendRedirect(request.getContextPath()+File.separator+"Error.jsp");
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
