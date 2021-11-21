<%@ page import="entity.Curso"%> <%@ page import="entity.Docente"%> <%@ page
import="entity.Alumno"%> <%@ page import="entity.Notas"%> <%@ page
import="java.util.ArrayList"%> <%@ page language="java" contentType="text/html;
charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>PaltAcad — Curso</title>
    <jsp:include page="../sources.jsp"></jsp:include>
    <script type="text/javascript">
      $(document).ready(function () {
        var table = $("#alumnos").DataTable({
          dom: "lfrtip",
        });
      });
    </script>
    <% Docente docente = ((Docente) request.getSession().getAttribute("user"));
    %>
    <% ArrayList<Notas> notasList = ((ArrayList<Notas>) request.getAttribute("notasList")); %>
    <% Curso curso = (Curso) request.getAttribute("selectedCurso"); %>
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
        <div class="container is-fluid">
          <h1 class="title is-1 has-text-centered">Curso <%= curso.getId() %></h1>
          <% if (request.getParameter("op") != null) { %>
            <% if (request.getParameter("op").equals("success")) { %>
          <h4 class="subtitle is-4 has-text-centered has-text-success">Calificación exitosa.</h4>
            <% } else { %>
          <h4 class="subtitle is-4 has-text-centered has-text-danger">Calificación fallida.</h4>
            <% } %>
          <% } else { %>
          <h4 class="subtitle is-4 has-text-centered">
            <%= curso.getDocente().getNombre() %>, <%=
            curso.getMateria().getNombre() %>, <%= curso.getSemestre() %>C <%=
            curso.getAnio() %>
          </h4>
          <% } %>
          <form
          method="POST"
          action="ver_curso?id=<%= curso.getId() %>&size=<%= notasList.size() %>"
          >
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
                  <th>Legajo</th>
                  <th>Nombre</th>
                  <th>P1</th>
                  <th>P2</th>
                  <th>R1</th>
                  <th>R2</th>
                  <th>Estado</th>
                </tr>
              </thead>
              <tfoot>
                <tr>
                  <th>Legajo</th>
                  <th>Nombre</th>
                  <th>P1</th>
                  <th>P2</th>
                  <th>R1</th>
                  <th>R2</th>
                  <th>Estado</th>
                </tr>
              </tfoot>
              <tbody>
                <% for (int i = 0, j = 0; i < notasList.size(); i++, j += 6) { %>
                  <tr>
                    <td>
                      <div class="control">
                        <input
                          class="input is-static has-text-centered"
                          name="legajo<%= j+5 %>"
                          type="number"
                          value="<%= notasList.get(i).getAlumno().getLegajo() %>"
                          readonly="readonly"
                        />
                      </div>
                    </td>
                    <td><%= notasList.get(i).getAlumno().getNombre() %></td>
                    <td>
                      <div class="field">
                        <div class="control">
                          <input
                            class="input has-text-centered"
                            name="parcial<%= j %>"
                            type="text"
                            value="<%= notasList.get(i).getParcial1() %>"
                          />
                        </div>
                      </div>
                    </td>
                    <td>
                      <div class="field">
                        <div class="control">
                          <input
                            class="input has-text-centered"
                            name="parcial<%= j+1 %>"
                            type="text"
                            value="<%= notasList.get(i).getParcial2() %>"
                          />
                        </div>
                      </div>
                    </td>
                    <td>
                      <div class="field">
                        <div class="control">
                          <input
                            class="input has-text-centered"
                            name="recu<%= j+2 %>"
                            type="text"
                            value="<%= notasList.get(i).getRecu1() %>"
                          />
                        </div>
                      </div>
                    </td>
                    <td>
                      <div class="field">
                        <div class="control">
                          <input
                          class="input has-text-centered"
                          name="recu<%= j+3 %>"
                          type="text"
                          value="<%= notasList.get(i).getRecu2() %>"
                          />
                        </div>
                      </div>
                    </td>
                    <td>
                      <div class="field">
                        <div class="select is-small">
                          <select name="estado<%= j+4 %>">
                          <% if (notasList.get(i).isEstado()) { %>
                            <option selected="selected">Regular</option>
                            <option>Libre</option>
                            <% } else { %>
                            <option>Regular</option>
                            <option selected="selected">Libre</option>
                          <% } %>
                          </select>
                        </div>
                      </div>
                    </td>
                  </tr>
                <% } %>
              </tbody>
            </table>
            <div class="control has-text-centered">
              <input
              class="button is-primary"
              type="submit"
              value="Calificar"
            />
          </form>
        </div>
      </div>
    </section>
  </body>
</html>
