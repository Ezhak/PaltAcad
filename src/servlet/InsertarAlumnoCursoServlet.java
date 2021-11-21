package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.AlumnoBusiness;
import business.CursoBusiness;
import business.NotasBusiness;
import entity.Notas;

/**
 * Servlet implementation class InsertarDocenteServlet
 */
@WebServlet(name = "InsertarAlumnoCursoServlet", urlPatterns = {
        "/admin/insert/alumno_curso" })
public class InsertarAlumnoCursoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarAlumnoCursoServlet() {
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
            request.getRequestDispatcher(
                    "/WEB-INF/admin/insert/alumno_curso.jsp")
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

        Notas notas = new Notas();
        NotasBusiness notasBusiness = new NotasBusiness();
        AlumnoBusiness alumnoBusiness = new AlumnoBusiness();
        CursoBusiness cursoBusiness = new CursoBusiness();

        try {
            notas.setAlumno(alumnoBusiness.selectOne(
                    Integer.parseInt(request.getParameter("legajo"))));
            notas.setCurso(cursoBusiness
                    .selectOne(Integer.parseInt(request.getParameter("id"))));

            if (notasBusiness.insert(notas)) {
                response.sendRedirect("../ver_curso?id="
                        + request.getParameter("id") + "&op=success");
            } else {
                response.sendRedirect("../ver_curso?id="
                        + request.getParameter("id") + "&op=failure");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("../ver_curso?id="
                    + request.getParameter("id") + "&op=failure");
            return;
        }
    }
}
