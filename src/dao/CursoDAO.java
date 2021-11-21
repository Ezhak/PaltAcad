package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterface.ICursoDAO;
import entity.Curso;
import entity.Docente;
import entity.Materia;

public class CursoDAO implements ICursoDAO {

    private static final String insert = "{call insert_curso(?, ?, ?, ?)}";
    private static final String selectAll = "{call get_cursos()}";
    private static final String selectAllDocente = "{call get_cursos_docente(?)}";
    private static final String selectOne = "{call get_curso_from_id(?)}";

    @Override
    public boolean insert(Curso curso) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        boolean status = false;

        try {
            CallableStatement cs = connection.prepareCall(insert);
            cs.setInt(1, curso.getDocente().getLegajo());
            cs.setInt(2, curso.getMateria().getId());
            cs.setInt(3, curso.getSemestre());
            cs.setInt(4, curso.getAnio());

            if (cs.executeUpdate() > 0) {
                connection.commit();
                status = true;
            }

            connectionInstance.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return status;
    }

    @Override
    public List<Curso> selectAll() {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        List<Curso> cursos = new ArrayList<>();

        try {
            CallableStatement cs = connection.prepareCall(selectAll);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                cursos.add(getCurso(rs));
            }

            connectionInstance.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    @Override
    public List<Curso> selectAll(int legajo) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        List<Curso> cursos = new ArrayList<>();

        try {
            CallableStatement cs = connection.prepareCall(selectAllDocente);
            cs.setInt(1, legajo);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                cursos.add(getCurso(rs));
            }

            connectionInstance.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    @Override
    public Curso selectOne(int id) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        Curso curso = null;

        try {
            CallableStatement cs = connection.prepareCall(selectOne);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                curso = getCurso(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return curso;
    }

    private Curso getCurso(ResultSet resultSet) throws SQLException {

        Curso curso = new Curso();

        curso.setId(resultSet.getInt("curso_id"));
        curso.setDocente(getDocente(resultSet.getInt("docente_legajo")));
        curso.setMateria(getMateria(resultSet.getInt("materia_id")));
        curso.setSemestre(resultSet.getInt("semestre"));
        curso.setAnio(resultSet.getInt("anio"));

        return curso;
    }

    private Docente getDocente(int legajo) {

        DocenteDAO docenteDAO = new DocenteDAO();

        return docenteDAO.selectOne(legajo);
    }

    public Materia getMateria(int id) {

        MateriaDAO materiaDAO = new MateriaDAO();

        return materiaDAO.selectOne(id);
    }
}
