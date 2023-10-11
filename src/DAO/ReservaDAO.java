package DAO;

import modelo.Reserva;

import java.math.BigDecimal;
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

                    while(resultSet.next()){
                        Reserva reserva = new Reserva(
                                resultSet.getInt("ID"),
                                resultSet.getDate("fechaEntrada"),
                                resultSet.getDate("fechaSalida"),
                                resultSet.getBigDecimal("valor"),
                                resultSet.getString("formaDePago")
                                );

                        listaReservas.add(reserva);
                    }
                }
            }
            return listaReservas;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Reserva buscarReserva(Integer id) {
        Reserva resultado = null;
        String sql ="SELECT ID, fechaEntrada, fechaSalida, valor, formaDePago  FROM RESERVAS WHERE ID=?";
        try{
            PreparedStatement statement = con.prepareStatement(sql);
            try(statement) {
                statement.setInt(1,id);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                try(resultSet){
                    while(resultSet.next()) {
                        resultado = new Reserva(
                                resultSet.getInt("ID"),
                                resultSet.getDate("fechaEntrada"),
                                resultSet.getDate("fechaSalida"),
                                resultSet.getBigDecimal("valor"),
                                resultSet.getString("formaDePago")
                        );
                    }
                }
            }
        return resultado;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    public void  modificar(Reserva reserva){
        String sql = "UPDATE RESERVAS SET fechaEntrada=?, fechaSalida=?, valor=?, formaDePago=? WHERE ID=?";
        try {
            PreparedStatement statement = con.prepareStatement(sql);
            try(statement){
                statement.setDate(1, reserva.getFechaEntrada());
                statement.setDate(2, reserva.getFechaEntrada());
                statement.setBigDecimal(3,reserva.getValor());
                statement.setString(4,reserva.getFormaDePago());
                statement.setInt(5,reserva.getId());

                statement.execute();
                System.out.println("modificado");
            }

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
