package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AccountBean;
import bean.AccountBean.Ruolo;


@WebFilter("/AdminFilter")
public class AdminFilter implements Filter {


    public AdminFilter() {
    }


	public void destroy() {

}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		AccountBean account=(AccountBean) httpRequest.getSession().getAttribute("account");
		if(!account.getTipo().equals(Ruolo.Supervisore)) {
			//Oppure una error page (Forbidden Access)
				httpResponse.sendRedirect(httpRequest.getContextPath()+"/HomepageUtente.jsp");
				return;
		}else
				chain.doFilter(request, response);

	}


	public void init(FilterConfig fConfig) throws ServletException {		// TODO Auto-generated method stub
	}

}
