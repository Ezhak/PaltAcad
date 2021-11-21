package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterface.ILocalidadDAO;
import entity.Localidad;

public class LocalidadDAO implements ILocalidadDAO {

    private static final String selectAll = "{call get_localidades()}";
    private static final String selectOne = "{call get_localidad_from_id(?)}";

    @Override
    public List<Localidad> selectAll() {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        List<Localidad> localidades = new ArrayList<>();

        try {
            CallableStatement cs = connection.prepareCall(selectAll);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                localidades.add(getLocalidad(rs));
            }

            connectionInstance.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return localidades;
    }

    @Override
    public Localidad selectOne(int id) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        Localidad localidad = null;

        try {
            CallableStatement cs = connection.prepareCall(selectOne);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                localidad = getLocalidad(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return localidad;
    }

    private Localidad getLocalidad(ResultSet resultSet) throws SQLException {

        Localidad localidad = new Localidad();
        localidad.setId(resultSet.getInt("localidad_id"));
        localidad.setNombre(resultSet.getString("nombre"));

        return localidad;
    }
}
