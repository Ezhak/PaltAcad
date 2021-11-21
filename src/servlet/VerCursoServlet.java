package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.AlumnoBusiness;
import business.CursoBusiness;
import entity.Alumno;
import entity.Curso;

/**
 * Servlet implementation class AlumnosServlet
 */
@WebServlet(name = "VerCursoServlet", urlPatterns = { "/admin/ver_curso" })
public class VerCursoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerCursoServlet() {
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

        if (user == null || !String.class.isInstance(user)) {
            response.sendRedirect("../restringido");
        } else {
            AlumnoBusiness alumnoBusiness = new AlumnoBusiness();
            CursoBusiness cursoBusiness = new CursoBusiness();
            ArrayList<Alumno> alumnosList = (ArrayList<Alumno>) alumnoBusiness
                    .selectAllFromCurso(
                            Integer.parseInt(request.getParameter("id")));
            Curso selectedCurso = cursoBusiness
                    .selectOne(Integer.parseInt(request.getParameter("id")));

            request.setAttribute("alumnosList", alumnosList);
            request.setAttribute("selectedCurso", selectedCurso);
            request.getRequestDispatcher("/WEB-INF/admin/ver_curso.jsp")
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
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
