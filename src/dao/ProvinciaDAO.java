package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterface.IProvinciaDAO;
import entity.Provincia;

public class ProvinciaDAO implements IProvinciaDAO {

    private static final String selectAll = "{call get_provincias()}";
    private static final String selectOne = "{call get_provincia_from_id(?)}";

    @Override
    public List<Provincia> selectAll() {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        List<Provincia> provincias = new ArrayList<>();

        try {
            CallableStatement cs = connection.prepareCall(selectAll);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                provincias.add(getProvincia(rs));
            }

            connectionInstance.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return provincias;
    }

    @Override
    public Provincia selectOne(int id) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        Provincia provincia = null;

        try {
            CallableStatement cs = connection.prepareCall(selectOne);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                provincia = getProvincia(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return provincia;
    }

    private Provincia getProvincia(ResultSet resultSet) throws SQLException {

        Provincia provincia = new Provincia();
        provincia.setId(resultSet.getInt("provincia_id"));
        provincia.setNombre(resultSet.getString("nombre"));

        return provincia;
    }
}
