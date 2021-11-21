package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.CursoBusiness;
import entity.Curso;
import entity.Docente;

/**
 * Servlet implementation class AlumnosServlet
 */
@WebServlet(name = "DocenteCursosServlet", urlPatterns = { "/docente/cursos" })
public class DocenteCursosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocenteCursosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control",
                "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.

        Object user = request.getSession().getAttribute("user");

        if (user == null || !Docente.class.isInstance(user)) {
            response.sendRedirect("../restringido");
        } else {
            CursoBusiness cursoBusiness = new CursoBusiness();
            ArrayList<Curso> cursosList = (ArrayList<Curso>) cursoBusiness
                    .selectAll(((Docente) user).getLegajo());

            request.setAttribute("cursosList", cursosList);
            request.getRequestDispatcher("/WEB-INF/docente/cursos.jsp")
                    .forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated constructor stub
        doGet(request, response);
    }
}
