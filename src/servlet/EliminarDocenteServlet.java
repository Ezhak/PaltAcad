package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.DocenteBusiness;
import entity.Docente;

/**
 * Servlet implementation class EliminarServlet
 */
@WebServlet(name = "EliminarDocenteServlet", urlPatterns = {
        "/admin/delete/docente" })
public class EliminarDocenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarDocenteServlet() {
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
            request.getRequestDispatcher("/WEB-INF/admin/delete/docente.jsp")
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

        Docente docente = new Docente();
        DocenteBusiness docenteBusiness = new DocenteBusiness();

        try {
            docente.setLegajo(Integer.parseInt(request.getParameter("legajo")));
        } catch (Exception e) {
            response.sendRedirect("../docentes?op=failure");
            return;
        }

        if (docenteBusiness.delete(docente)) {
            response.sendRedirect("../docentes?op=success");
        } else {
            response.sendRedirect("../docentes?op=failure");
        }
    }
}
