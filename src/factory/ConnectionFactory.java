package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String JDBC_URL = "jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pass";

    public ConnectionFactory(){
    }
    public Connection recuperarConexion() {
        try {
            System.out.println("Conectando");
            return DriverManager.getConnection(JDBC_URL,
                    USERNAME,PASSWORD);
        } catch (SQLException e) {
            System.out.println("Error al intentar conectar");
            throw new RuntimeException(e);
        }
    }
}
