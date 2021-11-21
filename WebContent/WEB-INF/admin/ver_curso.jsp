<%@ page import="entity.Curso"%> <%@ page import="entity.Alumno"%>
<%@ page import="java.util.ArrayList"%> <%@
page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>PaltAcad — Curso</title>
    <jsp:include page="../sources.jsp"></jsp:include>
    <script type="text/javascript">
      $(document).ready(function () {
        var table = $("#alumnos").DataTable({
          dom: "Blfrtip",
          buttons: [
            {
              text: "➕",
              action: function () {
                const queryString = window.location.search;
                const urlParams = new URLSearchParams(queryString);
                const id = urlParams.get('id')
                location.href = "insert/alumno_curso?id=" + id;
              },
            },
          ],
        });
      });
    </script>
    <% ArrayList<Alumno> alumnosList = (ArrayList<Alumno>) request.getAttribute("alumnosList"); %>
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
          <a class="navbar-item" href="alumnos"> 🤓 Alumnos </a>
          <a class="navbar-item" href="docentes"> 🧑‍🏫 Docentes </a>
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
          <p><i>Bienvenido,</i> <strong>Administrador</strong></p>
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
        <div class="container is-fluid">
          <% Curso curso = (Curso) request.getAttribute("selectedCurso"); %>
          <h1 class="title is-1 has-text-centered">Curso <%= curso.getId() %></h1>
            <% if (request.getParameter("op") != null) { %>
              <% if (request.getParameter("op").equals("success")) { %>
          <h4 class="subtitle is-4 has-text-centered has-text-success">
            Alumno insertado con éxito.
          </h4>
              <% } else { %>
          <h4 class="subtitle is-4 has-text-centered has-text-danger">
            Alumno no insertado.
          </h4>
              <% } %>
            <% } else { %>
          <h4 class="subtitle is-4 has-text-centered">
              <%= curso.getDocente().getNombre() %>,
              <%= curso.getMateria().getNombre() %>,
              <%= curso.getSemestre() %>C <%= curso.getAnio() %>
          </h4>
            <% } %>
          <% if (alumnosList.isEmpty()) { %>
          <h4 class="subtitle is-4 has-text-centered"><i>Curso vacío.</i></h4>
          <% } %>
          <table
            class="
              table
              is-striped is-narrow is-bordered is-hoverable is-fullwidth
              has-text-centered
            "
            id="alumnos"
          >
            <thead>
              <tr>
                <th><abbr title="Estado">?</abbr></th>
                <th>Legajo</th>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Nacimiento</th>
                <th>Domicilio</th>
                <th>Nacionalidad</th>
                <th>Provincia</th>
                <th>Email</th>
                <th>Tel&eacute;fono</th>
              </tr>
            </thead>
            <tfoot>
              <tr>
                <th><abbr title="Estado">?</abbr></th>
                <th>Legajo</th>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Nacimiento</th>
                <th>Domicilio</th>
                <th>Nacionalidad</th>
                <th>Provincia</th>
                <th>Email</th>
                <th>Tel&eacute;fono</th>
              </tr>
            </tfoot>
            <tbody>
              <% for (Alumno alumno : alumnosList) { %>
                <tr>
                  <% if (alumno.isActivo()) { %>
                  <td>Activo</td>
                  <% } else { %>
                  <td>Inactivo</td>
                  <% } %>
                  <td><%=alumno.getLegajo() %></td>
                  <td><%=alumno.getDni() %></td>
                  <td><%=alumno.getNombre() %></td>
                  <td><%=alumno.getFechaNacimiento() %></td>
                  <td><%=alumno.getDomicilio() %></td>
                  <td><%=alumno.getNacionalidad().getNombre() %></td>
                  <td><%=alumno.getProvincia().getNombre() %></td>
                  <td><%=alumno.getEmail() %></td>
                  <td><%=alumno.getTelefono() %></td>
                </tr>
                <% } %>
            </tbody>
          </table>
        </div>
      </div>
    </section>
  </body>
</html>
