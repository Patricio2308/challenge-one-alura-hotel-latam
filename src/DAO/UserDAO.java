package DAO;

import controller.ReservaController;
import modelo.Reserva;
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
            String sql = "INSERT INTO HUESPEDES(NOMBRE, APELLIDO, NACIMIENTO, NACIONALIDAD, TELEFONO, IdReserva)" +
                    " VALUES(?, ?, ?, ?, ?, ?)";
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
        statement.setDate(3, user.getFechaNacimiento());
        statement.setString(4, user.getNacionalidad());
        statement.setString(5, user.getTelefono());
        statement.setInt(6,user.getNumeroReserva());

        statement.execute();

        ResultSet resultSet = statement.getGeneratedKeys();
        try(resultSet) {
            while(resultSet.next()){
                user.setId(resultSet.getInt(1));
                System.out.println("se guardo el usuario: "+ resultSet.getInt(1));
            }
        }
    }

    public List<User> cargarUsuarios(){
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
                                resultSet.getDate("NACIMIENTO"),
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

    public List<User> buscarUsuario(String valorBusqueda){
        List<User> lista = new ArrayList<>();
        String sql;
        boolean esNumero = false;

        try {
            //intenta ver si es numero o string
            int idReserva = Integer.parseInt(valorBusqueda);
            esNumero = true;
            sql = "SELECT ID, NOMBRE, APELLIDO, NACIMIENTO, NACIONALIDAD, TELEFONO, IdReserva FROM HUESPEDES WHERE IdReserva=?";
        } catch (NumberFormatException e) {
            sql = "SELECT ID, NOMBRE, APELLIDO, NACIMIENTO, NACIONALIDAD, TELEFONO, IdReserva FROM HUESPEDES WHERE APELLIDO=?";
        }
        try{
            PreparedStatement statement = con.prepareStatement(sql);

            try(statement) {

                if (esNumero) {
                    statement.setInt(1, Integer.parseInt(valorBusqueda));
                } else {
                    statement.setString(1, valorBusqueda);
                }

                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                try(resultSet){
                    while(resultSet.next()){
                        User user = new User(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("APELLIDO"),
                                resultSet.getDate("NACIMIENTO"),
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


    public void modificar(User user){
            String sql = "UPDATE HUESPEDES SET NOMBRE=?, APELLIDO=?, NACIMIENTO=?, NACIONALIDAD=?, " +
                    "TELEFONO=?, IdReserva=? WHERE ID=?";
        try{
            final PreparedStatement statement = con.prepareStatement(sql);
            try(statement) {
                statement.setString(1, user.getNombre());
                statement.setString(2, user.getApellido());
                statement.setDate(3, user.getFechaNacimiento());
                statement.setString(4, user.getNacionalidad());
                statement.setString(5, user.getTelefono());
                statement.setInt(6,user.getNumeroReserva());
                statement.setInt(7,user.getId());

                statement.executeUpdate();
                System.out.println("modificado con exito");
            }

        } catch (SQLException e){
            System.out.println("No se pudo modificar el registro");
            throw new RuntimeException(e);
        }
    }

    public void eliminarUsuario(Integer id) {
        String sql = "DELETE FROM HUESPEDES WHERE ID=?";
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            try(statement){
                statement.setInt(1, id);
                statement.execute();
            }
        } catch (SQLException e){
            System.out.println("No se pudo modificar el registro");
            throw new RuntimeException(e);
        }
    }
}
