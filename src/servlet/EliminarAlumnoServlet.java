package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.AlumnoBusiness;
import entity.Alumno;

/**
 * Servlet implementation class EliminarServlet
 */
@WebServlet(name = "EliminarAlumnoServlet", urlPatterns = {
        "/admin/delete/alumno" })
public class EliminarAlumnoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarAlumnoServlet() {
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
            request.getRequestDispatcher("/WEB-INF/admin/delete/alumno.jsp")
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

        Alumno alumno = new Alumno();
        AlumnoBusiness alumnoBusiness = new AlumnoBusiness();

        try {
            alumno.setLegajo(Integer.parseInt(request.getParameter("legajo")));
        } catch (Exception e) {
            response.sendRedirect("../alumnos?op=failure");
            return;
        }

        if (alumnoBusiness.delete(alumno)) {
            response.sendRedirect("../alumnos?op=success");
        } else {
            response.sendRedirect("../alumnos?op=failure");
        }
    }
}
