package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionInstance {

    public static ConnectionInstance instance;
    private Connection connection;

    private ConnectionInstance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionString = "jdbc:mysql://localhost:3306/paltacad";
            String username = "root";
            String password = "root";
            this.connection = DriverManager.getConnection(connectionString,
                    username, password);
            this.connection.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ConnectionInstance getConnectionInstance() {
        if (instance == null) {
            instance = new ConnectionInstance();
        }

        return instance;
    }

    public Connection getSQLConnection() {
        return this.connection;
    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        instance = null;
    }
}
