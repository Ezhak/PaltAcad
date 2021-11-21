<%@ page import="java.util.ArrayList"%> <%@ page import="entity.Localidad"%> <%@
page import="entity.Nacionalidad"%> <%@ page import="entity.Docente"%> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>PaltAcad — Actualizar docente</title>
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
          <a
            class="has-background-link has-text-light navbar-item"
            href="../docentes"
          >
            🧑‍🏫 Docentes
          </a>
          <a class="navbar-item" href="../cursos"> 📚 Cursos </a>
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
          <h1 class="title is-1">Actualizar docente</h1>
          <h4 class="subtitle is-4">
            Actualice la información del docente seleccionado.
          </h4>
          <% Docente docente = (Docente)
          request.getAttribute("selectedDocente"); %>
          <form class="box" method="POST" action="docente">
            <div class="columns">
              <div class="column">
                <div class="field">
                  <label class="label">Estado</label>
                  <div class="select">
                    <select name="estado">
                      <% if (docente.isActivo()) { %>
                      <option selected="selected">Activo</option>
                      <option>Inactivo</option>
                      <% } else { %>
                      <option>Activo</option>
                      <option selected="selected">Inactivo</option>
                      <% } %>
                    </select>
                  </div>
                </div>

                <div class="field">
                  <label class="label">Legajo</label>
                  <div class="control">
                    <input
                      class="input is-static"
                      name="legajo"
                      type="number"
                      value="<%= docente.getLegajo() %>"
                      readonly="readonly"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">DNI</label>
                  <div class="control">
                    <input
                      class="input"
                      name="dni"
                      type="number"
                      value="<%= docente.getDni() %>"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Nombre</label>
                  <div class="control">
                    <input
                      class="input"
                      name="nombre"
                      type="text"
                      value="<%= docente.getNombre() %>"
                    />
                  </div>
                </div>


                <div class="field">
                  <label class="label">Fecha de nacimiento</label>
                  <div class="control">
                    <input
                      class="input"
                      name="nacimiento"
                      type="date"
                      value="<%= docente.getFechaNacimiento() %>"
                    />
                  </div>
                </div>
              </div>

              <div class="column">
                <div class="field">
                  <label class="label">Domicilio</label>
                  <div class="control">
                    <input
                      class="input"
                      name="domicilio"
                      type="text"
                      value="<%= docente.getDomicilio() %>"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Localidad</label>
                  <div class="select">
                    <select name="localidad">
                      <% for (Localidad localidad : (ArrayList<Localidad>) request.getAttribute("localidadesList")) { %>
                        <% if (localidad.getNombre().equals(docente.getLocalidad().getNombre())) { %>
                      <option value="<%= localidad.getId() %>" selected="selected">
                          <%= localidad.getNombre() %>
                      </option>
                          <%  } else { %>
                      <option value="<%= localidad.getId() %>">
                          <%= localidad.getNombre() %>
                      </option>
                          <%  } %>
                      <% } %>
                    </select>
                  </div>
                </div>

                <div class="field">
                  <label class="label">Nacionalidad</label>
                  <div class="select">
                    <select name="nacionalidad">
                      <% for (Nacionalidad nacionalidad : (ArrayList<Nacionalidad>) request.getAttribute("nacionalidadesList")) { %>
                        <% if (nacionalidad.getNombre().equals(docente.getNacionalidad().getNombre())) { %>
                      <option value="<%= nacionalidad.getId() %>" selected="selected">
                          <%= nacionalidad.getNombre() %>
                      </option>
                          <%  } else { %>
                      <option value="<%= nacionalidad.getId() %>">
                          <%= nacionalidad.getNombre() %>
                      </option>
                          <%  } %>
                      <% } %>
                    </select>
                  </div>
                </div>


                <div class="field">
                  <label class="label">Email</label>
                  <div class="control">
                    <input
                      class="input"
                      name="email"
                      type="email"
                      value="<%= docente.getEmail() %>"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Tel&eacute;fono</label>
                  <div class="control">
                    <input
                      class="input"
                      name="telefono"
                      type="text"
                      value="<%= docente.getTelefono() %>"
                    />
                  </div>
                </div>

                <div class="field">
                  <label class="label">Contrase&ntilde;a</label>
                  <div class="control">
                    <input
                      class="input"
                      name="contrasenia"
                      type="text"
                      value="<%= docente.getContrasenia() %>"
                    />
                  </div>
                </div>
              </div>
            </div>

            <div class="control has-text-centered">
              <input
                class="button is-primary"
                type="submit"
                value="Actualizar"
              />
            </div>
          </form>
        </div>
      </div>
    </section>
  </body>
</html>
