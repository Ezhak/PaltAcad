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
 * Servlet implementation class Login
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
        request.getRequestDispatcher("WEB-INF/login.jsp").forward(request,
                response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        if (request.getAttribute("retry") != null
                && (boolean) request.getAttribute("retry")) {
            request.setAttribute("retry", false);
        }

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        DocenteBusiness docenteBusiness = new DocenteBusiness();

        if (user.equals("admin") && pass.equals("admin")) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("admin/index");
            return;
        }

        try {
            Docente docente = docenteBusiness.selectOne(Integer.parseInt(user),
                    pass);

            if (docente != null) {
                request.getSession().setAttribute("user", docente);
                response.sendRedirect("docente/index");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("retry", true);
        doGet(request, response);
    }
}
