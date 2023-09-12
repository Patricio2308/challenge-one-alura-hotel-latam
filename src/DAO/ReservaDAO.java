package DAO;

import modelo.Reserva;
import modelo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    private Connection con;

    public ReservaDAO(Connection con){
        this.con = con;
    }

    public void guardarReserva(Reserva reserva){

        try {
            String sql = "INSERT INTO RESERVAS(FECHAENTRADA, FECHASALIDA, VALOR, FORMADEPAGO) " +
                    "VALUES(?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            try(statement){
                statement.setDate(1,reserva.getFechaEntrada());
                statement.setDate(2,reserva.getFechaSalida());
                statement.setBigDecimal(3,reserva.getValor());
                statement.setString(4,reserva.getFormaDePago());

                statement.execute();
                final ResultSet result = statement.getGeneratedKeys();
                try(result){
                    while (result.next()) {
                        reserva.setId(result.getInt(1));
                    }
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public List<Reserva> cargarReservas(){
        List<Reserva> listaReservas = new ArrayList<>();
        String sql = "SELECT ID, fechaEntrada, fechaSalida, valor, formaDePago FROM RESERVAS";
        try {
            final PreparedStatement statement = con.prepareStatement(sql);
            try(statement) {
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();
                try(resultSet){
                    System.out.println("cargando reservas");
                    while(resultSet.next()){
                        Reserva reserva = new Reserva(
                                resultSet.getInt("ID"),
                                resultSet.getDate("fechaEntrada"),
                                resultSet.getDate("fechaSalida"),
                                resultSet.getBigDecimal("valor"),
                                resultSet.getString("formaDePago")
                                );
                        System.out.println("cargando..");

                        listaReservas.add(reserva);
                    }
                }
            }
            return listaReservas;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void eliminarReserva(Integer id){
        String sql ="DELETE FROM RESERVAS WHERE ID= ?";
        try {
            final PreparedStatement statement = con.prepareStatement(sql);
            try(statement) {
                statement.setInt(1,id);
                statement.execute();
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
