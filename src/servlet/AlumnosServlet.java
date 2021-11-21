package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.AlumnoBusiness;
import entity.Alumno;

/**
 * Servlet implementation class AlumnosServlet
 */
@WebServlet(name = "AlumnosServlet", urlPatterns = { "/admin/alumnos" })
public class AlumnosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlumnosServlet() {
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
            ArrayList<Alumno> alumnosList = (ArrayList<Alumno>) alumnoBusiness
                    .selectAll();

            request.setAttribute("alumnosList", alumnosList);
            request.getRequestDispatcher("/WEB-INF/admin/alumnos.jsp")
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
