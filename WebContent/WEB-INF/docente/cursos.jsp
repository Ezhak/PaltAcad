<%@ page import="entity.Curso"%> <%@ page import="entity.Docente"%> <%@ page
import="entity.Materia"%> <%@ page import="java.util.ArrayList"%> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>PaltAcad — Cursos</title>
    <jsp:include page="../sources.jsp"></jsp:include>
    <script type="text/javascript">
      $(document).ready(function () {
        var table = $("#cursos").DataTable({
          dom: "Blfrtip",
          select: "single",
          buttons: [
            {
              text: "👁",
              action: function () {
                location.href =
                "ver_curso?id=" + table.rows({ selected: true }).data()[0][0];
              },
            },
          ],
        });
      });
    </script>
    <% Docente docente = ((Docente) request.getSession().getAttribute("user")); %>
    <% ArrayList<Curso> cursosList = (ArrayList<Curso>) request.getAttribute("cursosList"); %>
  </head>

  <body>
    <nav class="navbar is-spaced has-shadow">
      <div class="navbar-brand">
        <a class="navbar-item">
          <img
            src="https://i.pinimg.com/originals/f4/ab/1c/f4ab1c771b3590c7224ed278671883c2.png"
            alt="Logo"
          />
        </a>
      </div>

      <div class="navbar-menu">
        <div class="navbar-start">
          <a
            class="has-background-link has-text-light navbar-item"
            href="cursos"
          >
            📚 Cursos
          </a>
        </div>
      </div>

      <div class="navbar-end">
        <div class="navbar-item">
          <p><i>Bienvenid@,</i> <strong><%= docente.getNombre() %></strong></p>
        </div>
        <div class="navbar-item">
          <div class="buttons">
            <a class="button is-danger" href="../logout">Log out</a>
          </div>
        </div>
      </div>
    </nav>

    <section class="hero is-fullheight-with-navbar">
      <div class="hero-body">
        <div class="container">
          <% if (cursosList.isEmpty()) { %>
          <h4 class="subtitle is-4 has-text-centered"><i>No posee cursos.</i></h4>
          <% } else { %>
          <h1 class="title is-1 has-text-centered">Cursos</h1>
          <h4 class="subtitle is-4 has-text-centered">
            Seleccione un curso para ver.
          </h4>
          <table
            class="
              table
              is-striped is-narrow is-bordered is-hoverable is-fullwidth
              has-text-centered
            "
            id="cursos"
          >
            <thead>
              <tr>
                <th>ID</th>
                <th>Materia</th>
                <th>Semestre</th>
                <th>A&ntilde;o</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <th>ID</th>
                <th>Materia</th>
                <th>Semestre</th>
                <th>A&ntilde;o</th>
              </tr>
            </tfoot>
            <tbody>
            <% for (Curso curso : cursosList) { %>
              <tr>
                <td><%= curso.getId() %></td>
                <td><%= curso.getMateria().getNombre() %></td>
                <td><%= curso.getSemestre() %></td>
                <td><%= curso.getAnio() %></td>
              </tr>
            <% } %>
            </tbody>
          </table>
          <% } %>
        </div>
      </div>
    </section>
  </body>
</html>
