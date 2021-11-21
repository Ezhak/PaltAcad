package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.AlumnoBusiness;
import business.CursoBusiness;
import business.NotasBusiness;
import entity.Curso;
import entity.Docente;
import entity.Notas;

/**
 * Servlet implementation class AlumnosServlet
 */
@WebServlet(name = "DocenteVerCursoServlet", urlPatterns = {
        "/docente/ver_curso" })
public class DocenteVerCursoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocenteVerCursoServlet() {
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
            NotasBusiness notasBusiness = new NotasBusiness();
            ArrayList<Notas> notasList = (ArrayList<Notas>) notasBusiness
                    .selectAll(Integer.parseInt(request.getParameter("id")));
            Curso selectedCurso = cursoBusiness
                    .selectOne(Integer.parseInt(request.getParameter("id")));

            request.setAttribute("notasList", notasList);
            request.setAttribute("selectedCurso", selectedCurso);
            request.getRequestDispatcher("/WEB-INF/docente/ver_curso.jsp")
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

        AlumnoBusiness alumnoBusiness = new AlumnoBusiness();
        CursoBusiness cursoBusiness = new CursoBusiness();
        NotasBusiness notasBusiness = new NotasBusiness();

        try {
            for (int i = 0; i < Integer.parseInt(request.getParameter("size"))
                    * 6; i += 6) {
                Notas notas = new Notas();

                DecimalFormat decimalFormat = new DecimalFormat();
                decimalFormat.setParseBigDecimal(true);

                BigDecimal p1 = (BigDecimal) decimalFormat
                        .parse((request.getParameter("parcial" + i)));
                BigDecimal p2 = (BigDecimal) decimalFormat
                        .parse((request.getParameter("parcial" + (i + 1))));
                BigDecimal r1 = (BigDecimal) decimalFormat
                        .parse((request.getParameter("recu" + (i + 2))));
                BigDecimal r2 = (BigDecimal) decimalFormat
                        .parse((request.getParameter("recu" + (i + 3))));

                if (p1.compareTo(new BigDecimal(0)) == -1
                        || p1.compareTo(new BigDecimal(10)) == 1
                        || p2.compareTo(new BigDecimal(0)) == -1
                        || p2.compareTo(new BigDecimal(10)) == 1
                        || r1.compareTo(new BigDecimal(0)) == -1
                        || r1.compareTo(new BigDecimal(10)) == 1
                        || r2.compareTo(new BigDecimal(0)) == -1
                        || r2.compareTo(new BigDecimal(10)) == 1) {
                    throw new Exception();
                }

                notas.setAlumno(alumnoBusiness.selectOne(Integer
                        .parseInt(request.getParameter("legajo" + (i + 5)))));
                notas.setCurso(cursoBusiness.selectOne(
                        Integer.parseInt(request.getParameter("id"))));
                notas.setParcial1(p1);
                notas.setParcial2(p2);
                notas.setRecu1(r1);
                notas.setRecu2(r2);
                if (request.getParameter("estado" + (i + 4))
                        .equals("Regular")) {
                    notas.setEstado(true);
                } else if (request.getParameter("estado" + (i + 4))
                        .equals("Libre")) {
                    notas.setEstado(false);
                }
                notasBusiness.update(notas);
            }
            response.sendRedirect("ver_curso?id=" + request.getParameter("id")
                    + "&op=success");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ver_curso?id=" + request.getParameter("id")
                    + "&op=failure");
            return;
        }
    }
}
