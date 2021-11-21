package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterface.IDocenteDAO;
import entity.Docente;
import entity.Localidad;
import entity.Nacionalidad;

public class DocenteDAO implements IDocenteDAO {

    private static final String delete = "{call delete_docente(?)}";
    private static final String insert = "{call insert_docente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String selectAll = "{call get_docentes()}";
    private static final String selectOne = "{call get_docente_from_legajo(?)}";
    private static final String selectOneLogin = "{call get_docente_login(?, ?)}";
    private static final String update = "{call update_docente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

    @Override
    public boolean delete(Docente docente) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        boolean status = false;

        try {
            CallableStatement cs = connection.prepareCall(delete);
            cs.setInt(1, docente.getLegajo());

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
    public boolean insert(Docente docente) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        boolean status = false;

        try {
            CallableStatement cs = connection.prepareCall(insert);
            cs.setInt(1, docente.getDni());
            cs.setInt(2, docente.getLegajo());
            cs.setString(3, docente.getNombre());
            cs.setDate(4, new Date(docente.getFechaNacimiento().getTime()));
            cs.setString(5, docente.getDomicilio());
            cs.setInt(6, docente.getLocalidad().getId());
            cs.setInt(7, docente.getNacionalidad().getId());
            cs.setString(8, docente.getEmail());
            cs.setString(9, docente.getTelefono());
            cs.setString(10, docente.getContrasenia());

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
    public List<Docente> selectAll() {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        List<Docente> docentes = new ArrayList<>();

        try {
            CallableStatement cs = connection.prepareCall(selectAll);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                docentes.add(getDocente(rs));
            }

            connectionInstance.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return docentes;
    }

    @Override
    public Docente selectOne(int legajo) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        Docente docente = null;

        try {
            CallableStatement cs = connection.prepareCall(selectOne);
            cs.setInt(1, legajo);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                docente = getDocente(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return docente;
    }

    @Override
    public Docente selectOne(int legajo, String contrasenia) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        Docente docente = null;

        try {
            CallableStatement cs = connection.prepareCall(selectOneLogin);
            cs.setInt(1, legajo);
            cs.setString(2, contrasenia);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                docente = getDocente(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return docente;
    }

    @Override
    public boolean update(Docente docente) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        boolean status = false;

        try {
            CallableStatement cs = connection.prepareCall(update);
            cs.setBoolean(1, docente.isActivo());
            cs.setInt(2, docente.getDni());
            cs.setString(3, docente.getNombre());
            cs.setDate(4, new Date(docente.getFechaNacimiento().getTime()));
            cs.setString(5, docente.getDomicilio());
            cs.setInt(6, docente.getLocalidad().getId());
            cs.setInt(7, docente.getNacionalidad().getId());
            cs.setString(8, docente.getEmail());
            cs.setString(9, docente.getTelefono());
            cs.setString(10, docente.getContrasenia());
            cs.setInt(11, docente.getLegajo());

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

    private Docente getDocente(ResultSet resultSet) throws SQLException {

        Docente docente = new Docente();

        docente.setActivo(resultSet.getBoolean("estado"));
        docente.setLegajo(resultSet.getInt("legajo"));
        docente.setDni(resultSet.getInt("dni"));
        docente.setNombre(resultSet.getString("nombre"));
        docente.setFechaNacimiento(resultSet.getDate("nacimiento"));
        docente.setDomicilio(resultSet.getString("domicilio"));
        docente.setLocalidad(getLocalidad(resultSet.getInt("localidad_id")));
        docente.setNacionalidad(
                getNacionalidad(resultSet.getInt("nacionalidad_id")));
        docente.setEmail(resultSet.getString("email"));
        docente.setTelefono(resultSet.getString("telefono"));
        docente.setContrasenia(resultSet.getString("contrasenia"));

        return docente;
    }

    public Localidad getLocalidad(int id) {

        LocalidadDAO localidadDAO = new LocalidadDAO();

        return localidadDAO.selectOne(id);
    }

    public Nacionalidad getNacionalidad(int id) {

        NacionalidadDAO nacionalidadDAO = new NacionalidadDAO();

        return nacionalidadDAO.selectOne(id);
    }
}
