package controller;

import DAO.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;
import modelo.User;

import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperarConexion());
    }

    public void guardarReserva(Reserva reserva){
        reservaDAO.guardarReserva(reserva);
    }

    public List<Reserva> cargarReservas(){
        return reservaDAO.cargarReservas();
    }
    public void eliminarReserva(Integer id){
        reservaDAO.eliminarReserva(id);
    }
    public void modificarReserva(Reserva reserva){
        //reservaDAO.modificar(reserva);
        System.out.println("modificacion de reserva");
    }

    public Reserva buscarReserva(Integer id) {
        return reservaDAO.buscarReserva(id);
    }
}
