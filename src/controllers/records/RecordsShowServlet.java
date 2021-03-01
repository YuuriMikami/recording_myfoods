package controllers.records;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Record;
import utils.DBUtil;


@WebServlet("/records/show")
public class RecordsShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RecordsShowServlet() {
		super();
	}

	protected void doget(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();

		Record e =em.find(Record.class, Integer.parseInt(request.getParameter("id")));

		em.close();

		request.setAttribute("record", e);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/show.jsp");
		rd.forward(request, response);
	}

}
