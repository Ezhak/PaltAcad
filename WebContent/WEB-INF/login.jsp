<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>PaltAcad — Login</title>
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
    <% if (request.getAttribute("retry") != null && (boolean) request.getAttribute("retry")) { %>
    <section class="hero is-fullheight is-danger">
    <% } else { %>
    <section class="hero is-fullheight is-success">
    <% } %>
      <div class="hero-body">
        <div class="container has-text-centered">
          <h1 class="title is-1">PaltAcad</h1>
          <h3 class="subtitle is-3">Sistema saludable de gestión académica.</h3>
          <div class="columns is-centered">
            <div class="column is-5-tablet is-4-desktop is-3-widescreen">
              <form class="box" method="POST" action="login">
                <div class="field">
                  <label for="" class="label">Usuario</label>
                  <div class="control">
                    <input class="input" name="user" type="text" autofocus="" />
                  </div>
                </div>
                <div class="field">
                  <label for="" class="label">Contraseña</label>
                  <div class="control">
                    <input class="input" name="pass" type="password" />
                  </div>
                </div>
                <input
                  class="button is-link is-fullwidth"
                  type="submit"
                  value="Login 🥑"
                />
              </form>
              <% if (request.getAttribute("retry") != null && (boolean) request.getAttribute("retry")) { %>
              <h4 class="subtitle is-4"><i>Intente nuevamente.</i></h4>
              <% } %>
            </div>
          </div>
        </div>
      </div>
    </section>
  </body>
</html>
