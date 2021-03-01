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


@WebServlet("/records/edit")
public class RecordsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RecordsEditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Record e = em.find(Record.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("record", e);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("record_id", e.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/records/edit.jsp");
        rd.forward(request, response);
    }

}
