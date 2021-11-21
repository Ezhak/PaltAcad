package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterface.INacionalidadDAO;
import entity.Nacionalidad;

public class NacionalidadDAO implements INacionalidadDAO {

    private static final String selectAll = "{call get_nacionalidades()}";
    private static final String selectOne = "{call get_nacionalidad_from_id(?)}";

    @Override
    public List<Nacionalidad> selectAll() {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        List<Nacionalidad> nacionalidades = new ArrayList<>();

        try {
            CallableStatement cs = connection.prepareCall(selectAll);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                nacionalidades.add(getNacionalidad(rs));
            }

            connectionInstance.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nacionalidades;
    }

    @Override
    public Nacionalidad selectOne(int id) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        Nacionalidad nacionalidad = null;

        try {
            CallableStatement cs = connection.prepareCall(selectOne);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                nacionalidad = getNacionalidad(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nacionalidad;
    }

    private Nacionalidad getNacionalidad(ResultSet resultSet)
            throws SQLException {

        Nacionalidad nacionalidad = new Nacionalidad();

        nacionalidad.setId(resultSet.getInt("nacionalidad_id"));
        nacionalidad.setNombre(resultSet.getString("nombre"));

        return nacionalidad;
    }
}
