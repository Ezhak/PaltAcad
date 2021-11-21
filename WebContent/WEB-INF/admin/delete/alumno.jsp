<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>PaltAcad — Eliminar</title>
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
    <section class="hero is-fullheight is-danger">
      <div class="hero-body">
        <div class="container has-text-centered">
          <h1 class="title is-1">
            ¿Está seguro que desea eliminar este registro?
          </h1>
          <h4 class="subtitle is-4">
            <i
              ><%= request.getParameter("legajo") %>, <%=
              request.getParameter("nombre") %></i
            >
          </h4>
          <form
            method="POST"
            action='alumno?legajo=<%= request.getParameter("legajo") %>&nombre=<%= request.getParameter("nombre") %>'
          >
            <div class="field">
              <div class="control">
                <input class="button" type="submit" value="Confirmar" />
              </div>
            </div>
          </form>
        </div>
      </div>
    </section>
  </body>
</html>
