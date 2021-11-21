package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.DocenteBusiness;
import business.LocalidadBusiness;
import business.NacionalidadBusiness;
import entity.Docente;
import entity.Localidad;
import entity.Nacionalidad;

/**
 * Servlet implementation class InsertarDocenteServlet
 */
@WebServlet(name = "InsertarDocenteServlet", urlPatterns = {
        "/admin/insert/docente" })
public class InsertarDocenteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarDocenteServlet() {
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
            LocalidadBusiness localidadBusiness = new LocalidadBusiness();
            NacionalidadBusiness nacionalidadBusiness = new NacionalidadBusiness();
            ArrayList<Localidad> localidadesList = (ArrayList<Localidad>) localidadBusiness
                    .selectAll();
            ArrayList<Nacionalidad> nacionalidadesList = (ArrayList<Nacionalidad>) nacionalidadBusiness
                    .selectAll();

            request.setAttribute("localidadesList", localidadesList);
            request.setAttribute("nacionalidadesList", nacionalidadesList);
            request.getRequestDispatcher("/WEB-INF/admin/insert/docente.jsp")
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
        LocalidadBusiness localidadBusiness = new LocalidadBusiness();
        NacionalidadBusiness nacionalidadBusiness = new NacionalidadBusiness();

        try {
            docente.setLegajo(Integer.parseInt(request.getParameter("legajo")));
            docente.setDni(Integer.parseInt(request.getParameter("dni")));
            docente.setNombre(request.getParameter("nombre"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            docente.setFechaNacimiento(
                    format.parse(request.getParameter("nacimiento")));
            docente.setDomicilio(request.getParameter("domicilio"));
            docente.setLocalidad(localidadBusiness.selectOne(
                    Integer.parseInt(request.getParameter("localidad"))));
            docente.setNacionalidad(nacionalidadBusiness.selectOne(
                    Integer.parseInt(request.getParameter("nacionalidad"))));
            docente.setEmail(request.getParameter("email"));
            docente.setTelefono(request.getParameter("telefono"));
            docente.setContrasenia(request.getParameter("contrasenia"));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("../docentes?op=failure");
            return;
        }

        if (docenteBusiness.insert(docente)) {
            response.sendRedirect("../docentes?op=success");
        } else {
            response.sendRedirect("../docentes?op=failure");
        }
    }
}
