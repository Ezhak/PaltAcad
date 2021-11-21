<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>PaltAcad — Insertar alumno (curso)</title>
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
            <a class="button is-danger" href="../logout">Log out</a>
          </div>
        </div>
      </div>
    </nav>

    <section class="hero is-fullheight-with-navbar">
      <div class="hero-body">
        <div class="container">
          <h1 class="title is-1">Insertar alumno en curso</h1>
          <h4 class="subtitle is-4">Inserte un nuevo alumno.</h4>
          <div class="columns is-centered">
            <div class="column is-4">
              <form class="box" method="POST" action="">
                <div class="field">
                  <label class="label">Legajo</label>
                  <div class="control">
                    <input class="input" name="legajo" type="number" />
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
        </div>
      </div>
    </section>
  </body>
</html>
