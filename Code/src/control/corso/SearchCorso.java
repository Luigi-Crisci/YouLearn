package control.corso;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CorsoBean;
import exception.NotFoundException;
import manager.CorsoManager;

/**
 * Servlet implementation class SearchCorso
 */
@WebServlet("/SearchCorso")
public class SearchCorso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CorsoManager manager;
	
    public SearchCorso() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        manager=CorsoManager.getIstanza(getServletContext().getRealPath(""));

		response.getWriter().append("Served at: ").append(request.getContextPath());
			String search=request.getParameter("search");
			Collection<CorsoBean> corso;
			try {
				corso = manager.searchCorso(search);
				request.getSession().setAttribute("searched", corso);
				response.sendRedirect(request.getContextPath()+File.separator+"Risultati.jsp");
			} catch (SQLException | NotFoundException e) {
				response.sendRedirect(request.getContextPath()+File.separator+"Error.jsp");
				e.printStackTrace();
			}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
