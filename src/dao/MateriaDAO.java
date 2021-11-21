package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterface.IMateriaDAO;
import entity.Materia;

public class MateriaDAO implements IMateriaDAO {

    private static final String selectAll = "{call get_materias()}";
    private static final String selectOne = "{call get_materia_from_id(?)}";

    @Override
    public List<Materia> selectAll() {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        List<Materia> materias = new ArrayList<>();

        try {
            CallableStatement cs = connection.prepareCall(selectAll);
            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                materias.add(getMateria(rs));
            }

            connectionInstance.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materias;
    }

    @Override
    public Materia selectOne(int id) {

        ConnectionInstance connectionInstance = ConnectionInstance
                .getConnectionInstance();
        Connection connection = connectionInstance.getSQLConnection();
        Materia materia = null;

        try {
            CallableStatement cs = connection.prepareCall(selectOne);
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                materia = getMateria(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materia;
    }

    private Materia getMateria(ResultSet resultSet) throws SQLException {

        Materia materia = new Materia();
        materia.setId(resultSet.getInt("materia_id"));
        materia.setNombre(resultSet.getString("nombre"));

        return materia;
    }
}
