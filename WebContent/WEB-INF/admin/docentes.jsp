<%@ page import="entity.Docente"%> <%@ page import="java.util.ArrayList"%> <%@
page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>PaltAcad — Docentes</title>
    <jsp:include page="../sources.jsp"></jsp:include>
    <script type="text/javascript">
      $(document).ready(function () {
        var table = $("#docentes").DataTable({
          dom: "Blfrtip",
          select: "single",
          buttons: [
            {
              text: "➕",
              action: function () {
                location.href = "insert/docente";
              },
            },
            {
              text: "🔧",
              action: function () {
                location.href =
                  "update/docente?legajo=" +
                  table.rows({ selected: true }).data()[0][1];
              },
            },
            {
              text: "❌",
              action: function () {
                location.href =
                  "delete/docente?legajo=" +
                  table.rows({ selected: true }).data()[0][1] +
                  "&nombre=" +
                  table.rows({ selected: true }).data()[0][3];
              },
            },
          ],
        });
      });
    </script>
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
          <a
            class="has-background-link has-text-light navbar-item"
            href="docentes"
          >
            🧑‍🏫 Docentes
          </a>
          <a class="navbar-item" href="cursos"> 📚 Cursos </a>
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
        <div class="table-container is-fluid">
          <h1 class="title is-1 has-text-centered">Docentes</h1>
          <% if (request.getParameter("op") != null) { %>
            <% if (request.getParameter("op").equals("success")) { %>
          <h4 class="subtitle is-4 has-text-centered has-text-success">Operación exitosa.</h4>
            <% } else { %>
          <h4 class="subtitle is-4 has-text-centered has-text-danger">Operación fallida.</h4>
            <% } %>
          <% } else { %>
          <h4 class="subtitle is-4 has-text-centered">
            Seleccione un docente para actualizar o eliminar.
          </h4>
          <% } %>
          <table
            class="
              table
              is-striped is-narrow is-bordered is-hoverable is-fullwidth
              has-text-centered
            "
            id="docentes"
          >
            <thead>
              <tr>
                <th><abbr title="Estado">?</abbr></th>
                <th>Legajo</th>
                <th>DNI</th>
                <th>Nombre</th>
                <th>Nacimiento</th>
                <th>Domicilio</th>
                <th>Localidad</th>
                <th>Nacionalidad</th>
                <th>Email</th>
                <th>Tel&eacute;fono</th>
                <th>Contrase&ntilde;a</th>
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
                <th>Localidad</th>
                <th>Nacionalidad</th>
                <th>Email</th>
                <th>Tel&eacute;fono</th>
                <th>Contrase&ntilde;a</th>
              </tr>
            </tfoot>
            <tbody>
              <% for (Docente docente : (ArrayList<Docente>) request.getAttribute("docentesList")) { %>
              <tr>
                <% if (docente.isActivo()) { %>
                <td>Activo</td>
                <% } else { %>
                <td>Inactivo</td>
                <% } %>
                <td><%=docente.getLegajo() %></td>
                <td><%=docente.getDni() %></td>
                <td><%=docente.getNombre() %></td>
                <td><%=docente.getFechaNacimiento() %></td>
                <td><%=docente.getDomicilio() %></td>
                <td><%=docente.getLocalidad().getNombre() %></td>
                <td><%=docente.getNacionalidad().getNombre() %></td>
                <td><%=docente.getEmail() %></td>
                <td><%=docente.getTelefono() %></td>
                <td><%=docente.getContrasenia() %></td>
              </tr>
              <% } %>
            </tbody>
          </table>
        </div>
      </div>
    </section>
  </body>
</html>
