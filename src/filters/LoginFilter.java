package filters;

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
import javax.servlet.http.HttpSession;

import models.Record;


@WebFilter("/*")
public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	public void destroy() {
	}

		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	        String context_path = ((HttpServletRequest)request).getContextPath();
	        String servlet_path = ((HttpServletRequest)request).getServletPath();

	        if(!servlet_path.matches("/css.*")) {
	            HttpSession session = ((HttpServletRequest)request).getSession();

	            Record e = (Record)session.getAttribute("login_record");

	            if(!servlet_path.equals("/login")) {
	                if(e == null) {
	                    ((HttpServletResponse)response).sendRedirect(context_path + "/login");
	                    return;
	                }

	                // 従業員管理の機能は管理者のみが閲覧できる
	                if(servlet_path.matches("/records.*") && e.getAdmin_flag() == 0) {
	                    ((HttpServletResponse)response).sendRedirect(context_path + "/");
	                    return;
	                }
	            } else {
	                if(e != null) {
	                    ((HttpServletResponse)response).sendRedirect(context_path + "/");
	                    return;
	                }
	            }
	        }

	        chain.doFilter(request, response);
	    }


	    public void init(FilterConfig fConfig) throws ServletException {
	    }

	}

