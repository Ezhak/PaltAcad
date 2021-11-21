<%@ page import="java.util.ArrayList"%> <%@ page import="entity.Curso"%> <%@
page import="entity.Materia"%> <%@ page import="entity.Docente"%> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>PaltAcad — Insertar curso</title>
    <link
      rel="icon"
      href="https://i.pinimg.com/originals/f4/ab/1c/f4ab1c771b3590c7224ed278671883c2.png"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css"
    />
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
          <a class="navbar-item" href="../alumnos"> 🤓 Alumnos </a>
          <a class="navbar-item" href="../docentes"> 🧑‍🏫 Docentes </a>
          <a
            class="has-background-link has-text-light navbar-item"
            href="../cursos"
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
            <a class="button is-danger" href="../../logout">Log out</a>
          </div>
        </div>
      </div>
    </nav>

    <section class="hero is-fullheight-with-navbar">
      <div class="hero-body">
        <div class="container">
          <h1 class="title is-1">Insertar curso</h1>
          <h4 class="subtitle is-4">Inserte un nuevo curso.</h4>
          <form class="box" method="POST" action="curso">
            <div class="columns">
              <div class="column">
                <div class="field">
                  <label class="label">Legajo del docente</label>
                  <div class="control">
                    <input class="input" name="legajo" type="number" />
                  </div>
                </div>
              </div>

              <div class="column">
                <div class="field">
                  <label class="label">Materia</label>
                  <div class="select">
                    <select name="materia">
                      <% for (Materia materia : (ArrayList<Materia>) request.getAttribute("materiasList")) { %>
                      <option value="<%= materia.getId() %>">
                        <%= materia.getNombre() %>
                      </option>
                      <% } %>
                    </select>
                  </div>
                </div>
              </div>

              <div class="column">
                <div class="field">
                  <label class="label">Semestre</label>
                  <div class="control">
                    <input class="input" name="semestre" type="number" />
                  </div>
                </div>
              </div>

              <div class="column">
                <div class="field">
                  <label class="label">A&ntilde;o</label>
                  <div class="control">
                    <input class="input" name="anio" type="number" />
                  </div>
                </div>
              </div>
            </div>

            <div class="control has-text-centered">
              <input
                class="button is-primary"
                type="submit"
                value="Insertar"
              />
            </div>
          </form>
        </div>
      </div>
    </section>
  </body>
</html>
