package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.AlumnoBusiness;
import business.NacionalidadBusiness;
import business.ProvinciaBusiness;
import entity.Alumno;
import entity.Nacionalidad;
import entity.Provincia;

/**
 * Servlet implementation class InsertarDocenteServlet
 */
@WebServlet(name = "InsertarAlumnoServlet", urlPatterns = {
        "/admin/insert/alumno" })
public class InsertarAlumnoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarAlumnoServlet() {
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
            NacionalidadBusiness nacionalidadBusiness = new NacionalidadBusiness();
            ProvinciaBusiness provinciaBusiness = new ProvinciaBusiness();
            ArrayList<Nacionalidad> nacionalidadesList = (ArrayList<Nacionalidad>) nacionalidadBusiness
                    .selectAll();
            ArrayList<Provincia> provinciasList = (ArrayList<Provincia>) provinciaBusiness
                    .selectAll();

            request.setAttribute("nacionalidadesList", nacionalidadesList);
            request.setAttribute("provinciasList", provinciasList);
            request.getRequestDispatcher("/WEB-INF/admin/insert/alumno.jsp")
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
        NacionalidadBusiness nacionalidadBusiness = new NacionalidadBusiness();
        ProvinciaBusiness localidadBusiness = new ProvinciaBusiness();

        try {
            alumno.setLegajo(Integer.parseInt(request.getParameter("legajo")));
            alumno.setDni(Integer.parseInt(request.getParameter("dni")));
            alumno.setNombre(request.getParameter("nombre"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            alumno.setFechaNacimiento(
                    format.parse(request.getParameter("nacimiento")));
            alumno.setDomicilio(request.getParameter("domicilio"));
            alumno.setNacionalidad(nacionalidadBusiness.selectOne(
                    Integer.parseInt(request.getParameter("nacionalidad"))));
            alumno.setProvincia(localidadBusiness.selectOne(
                    Integer.parseInt(request.getParameter("provincia"))));
            alumno.setEmail(request.getParameter("email"));
            alumno.setTelefono(request.getParameter("telefono"));
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("../alumnos?op=failure");
            return;
        }

        if (alumnoBusiness.insert(alumno)) {
            response.sendRedirect("../alumnos?op=success");
        } else {
            response.sendRedirect("../alumnos?op=failure");
        }
    }
}
