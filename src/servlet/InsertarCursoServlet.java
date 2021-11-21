package servlet;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.CursoBusiness;
import business.DocenteBusiness;
import business.MateriaBusiness;
import entity.Curso;
import entity.Materia;

/**
 * Servlet implementation class InsertarDocenteServlet
 */
@WebServlet(name = "InsertarCursoServlet", urlPatterns = {
        "/admin/insert/curso" })
public class InsertarCursoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarCursoServlet() {
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
            MateriaBusiness materiaBusiness = new MateriaBusiness();
            ArrayList<Materia> materiasList = (ArrayList<Materia>) materiaBusiness
                    .selectAll();

            request.setAttribute("materiasList", materiasList);
            request.getRequestDispatcher("/WEB-INF/admin/insert/curso.jsp")
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

        Curso curso = new Curso();
        CursoBusiness cursoBusiness = new CursoBusiness();

        try {
            DocenteBusiness docenteBusiness = new DocenteBusiness();
            MateriaBusiness materiaBusiness = new MateriaBusiness();
            curso.setDocente(docenteBusiness.selectOne(
                    Integer.parseInt(request.getParameter("legajo"))));
            curso.setMateria(materiaBusiness.selectOne(
                    Integer.parseInt(request.getParameter("materia"))));

            int semestre = Integer.parseInt(request.getParameter("semestre"));
            if (semestre > 2 || semestre < 1) {
                response.sendRedirect("../cursos?op=failure");
                return;
            }
            curso.setSemestre(semestre);

            int anio = Integer.parseInt(request.getParameter("anio"));
            if (anio < Year.now().getValue()) {
                response.sendRedirect("../cursos?op=failure");
                return;
            }
            curso.setAnio(Integer.parseInt(request.getParameter("anio")));

            if (cursoBusiness.insert(curso)) {
                response.sendRedirect("../cursos?op=success");
            } else {
                response.sendRedirect("../cursos?op=failure");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("../cursos?op=failure");
            return;
        }
    }
}
