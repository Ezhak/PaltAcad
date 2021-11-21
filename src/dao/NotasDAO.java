package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.AlumnoBusiness;
import business.CursoBusiness;
import daoInterface.INotasDAO;
import entity.Notas;

public class NotasDAO implements INotasDAO {

    private static final String insert = "{call insert_notas_alumno(?, ?)}";
    private static final String selectAll = "{call get_notas(?)}";
    private static final String update = "{call update_notas(?, ?, ?, ?, ?, ?, ?)}";

    @Override
    public boolean insert(Notas notas) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        boolean status = false;

        try {
            CallableStatement cs = connection.prepareCall(insert);
            cs.setInt(1, notas.getCurso().getId());
            cs.setInt(2, notas.getAlumno().getLegajo());

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
    public List<Notas> selectAll(int id) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        List<Notas> notas = new ArrayList<>();

        try {
            CallableStatement cs = connection.prepareCall(selectAll);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                notas.add(getNotas(rs));
            }

            connectionInstance.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notas;
    }

    @Override
    public boolean update(Notas notas) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        boolean status = false;

        try {
            CallableStatement cs = connection.prepareCall(update);
            cs.setInt(1, notas.getCurso().getId());
            cs.setInt(2, notas.getAlumno().getLegajo());
            cs.setBigDecimal(3, notas.getParcial1());
            cs.setBigDecimal(4, notas.getParcial2());
            cs.setBigDecimal(5, notas.getRecu1());
            cs.setBigDecimal(6, notas.getRecu2());
            cs.setBoolean(7, notas.isEstado());

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

    private Notas getNotas(ResultSet resultSet) throws SQLException {

        Notas notas = new Notas();
        CursoBusiness cursoBusiness = new CursoBusiness();
        AlumnoBusiness alumnoBusiness = new AlumnoBusiness();

        notas.setCurso(cursoBusiness.selectOne(resultSet.getInt("curso_id")));
        notas.setAlumno(
                alumnoBusiness.selectOne(resultSet.getInt("alumno_legajo")));
        notas.setParcial1(resultSet.getBigDecimal("parcial1"));
        notas.setParcial2(resultSet.getBigDecimal("parcial2"));
        notas.setRecu1(resultSet.getBigDecimal("recu1"));
        notas.setRecu2(resultSet.getBigDecimal("recu2"));
        notas.setEstado(resultSet.getBoolean("estado"));

        return notas;
    }
}
