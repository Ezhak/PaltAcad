package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterface.IAlumnoDAO;
import entity.Alumno;
import entity.Nacionalidad;
import entity.Provincia;

public class AlumnoDAO implements IAlumnoDAO {

    private static final String delete = "{call delete_alumno(?)}";
    private static final String insert = "{call insert_alumno(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String selectAll = "{call get_alumnos()}";
    private static final String selectAllFromCurso = "{call get_alumnos_from_curso(?)}";
    private static final String selectOne = "{call get_alumno_from_legajo(?)}";
    private static final String update = "{call update_alumno(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

    @Override
    public boolean delete(Alumno alumno) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        boolean status = false;

        try {
            CallableStatement cs = connection.prepareCall(delete);
            cs.setInt(1, alumno.getLegajo());

            if (cs.executeUpdate() > 0) {
                connection.commit();
                status = true;
            }

            connectionInstance.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public boolean insert(Alumno alumno) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        boolean status = false;

        try {
            CallableStatement cs = connection.prepareCall(insert);
            cs.setInt(1, alumno.getDni());
            cs.setInt(2, alumno.getLegajo());
            cs.setString(3, alumno.getNombre());
            cs.setDate(4, new Date(alumno.getFechaNacimiento().getTime()));
            cs.setString(5, alumno.getDomicilio());
            cs.setInt(6, alumno.getNacionalidad().getId());
            cs.setInt(7, alumno.getProvincia().getId());
            cs.setString(8, alumno.getEmail());
            cs.setString(9, alumno.getTelefono());

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
    public List<Alumno> selectAll() {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        List<Alumno> alumnos = new ArrayList<>();

        try {
            CallableStatement cs = connection.prepareCall(selectAll);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                alumnos.add(getAlumno(rs));
            }

            connectionInstance.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alumnos;
    }

    @Override
    public List<Alumno> selectAllFromCurso(int id) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        List<Alumno> alumnos = new ArrayList<>();

        try {
            CallableStatement cs = connection.prepareCall(selectAllFromCurso);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                alumnos.add(getAlumno(rs));
            }

            connectionInstance.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alumnos;
    }

    @Override
    public Alumno selectOne(int legajo) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        Alumno docente = null;

        try {
            CallableStatement cs = connection.prepareCall(selectOne);
            cs.setInt(1, legajo);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                docente = getAlumno(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return docente;
    }

    @Override
    public boolean update(Alumno alumno) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        boolean status = false;

        try {
            CallableStatement cs = connection.prepareCall(update);
            cs.setBoolean(1, alumno.isActivo());
            cs.setInt(2, alumno.getDni());
            cs.setString(3, alumno.getNombre());
            cs.setDate(4, new Date(alumno.getFechaNacimiento().getTime()));
            cs.setString(5, alumno.getDomicilio());
            cs.setInt(7, alumno.getNacionalidad().getId());
            cs.setInt(6, alumno.getProvincia().getId());
            cs.setString(8, alumno.getEmail());
            cs.setString(9, alumno.getTelefono());
            cs.setInt(10, alumno.getLegajo());

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

    private Alumno getAlumno(ResultSet resultSet) throws SQLException {

        Alumno alumno = new Alumno();

        alumno.setActivo(resultSet.getBoolean("estado"));
        alumno.setLegajo(resultSet.getInt("legajo"));
        alumno.setDni(resultSet.getInt("dni"));
        alumno.setNombre(resultSet.getString("nombre"));
        alumno.setFechaNacimiento(resultSet.getDate("nacimiento"));
        alumno.setDomicilio(resultSet.getString("domicilio"));
        alumno.setNacionalidad(
                getNacionalidad(resultSet.getInt("nacionalidad_id")));
        alumno.setProvincia(getProvincia(resultSet.getInt("provincia_id")));
        alumno.setEmail(resultSet.getString("email"));
        alumno.setTelefono(resultSet.getString("telefono"));

        return alumno;
    }

    public Nacionalidad getNacionalidad(int id) {

        NacionalidadDAO nacionalidadDAO = new NacionalidadDAO();

        return nacionalidadDAO.selectOne(id);
    }

    public Provincia getProvincia(int id) {

        ProvinciaDAO provinciaDAO = new ProvinciaDAO();

        return provinciaDAO.selectOne(id);
    }
}
