package DAO;

import modelo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection con;

    public UserDAO(Connection con) {
        this.con = con;
    }

    public  void guardarUsuario(User user) {

        try {
            String sql = "INSERT INTO HUESPEDES(NOMBRE, APELLIDO, NACIMIENTO, NACIONALIDAD, TELEFONO)" +
                    " VALUES(?, ?, ?, ?, ?)";
            final PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            try(statement) {
                registrarUsuario(user, statement);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private static void registrarUsuario(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getNombre());
        statement.setString(2, user.getApellido());
        statement.setString(3, user.getFechaNacimiento());
        statement.setString(4, user.getNacionalidad());
        statement.setString(5, user.getTelefono());

        statement.execute();

        ResultSet resultSet = statement.getGeneratedKeys();
        try(resultSet) {
            while(resultSet.next()){
                user.setId(resultSet.getInt(1));
                System.out.println("se guardo el usuario: "+ resultSet.getInt(1));
            }
        }
    }

    public List<User> leerUsuarios(){
        List<User> lista = new ArrayList<>();
        try{
            String sql = "SELECT ID, NOMBRE, APELLIDO, NACIMIENTO, NACIONALIDAD, TELEFONO, IdReserva FROM HUESPEDES";
            final PreparedStatement statement = con.prepareStatement(sql);
            try(statement){
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();
                try(resultSet){
                    System.out.println("resultado "+ statement);

                    while (resultSet.next()){
                        User user = new User(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO"),
                                resultSet.getString("NACIMIENTO"),
                                resultSet.getString("NACIONALIDAD"),
                                resultSet.getString("TELEFONO"),
                                resultSet.getInt("IdReserva")
                        );
                        lista.add(user);
                    }
                }
            }
                    return lista;
        } catch (SQLException e){
            System.out.println("no se pudo cargar la lista de usuarios");
            throw new RuntimeException(e);
        }
    }
}
