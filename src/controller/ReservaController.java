package controller;

import DAO.ReservaDAO;
import factory.ConnectionFactory;
import modelo.Reserva;

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

    public Reserva buscarReserva(Integer id) {
        return reservaDAO.buscarReserva(id);
    }
}
