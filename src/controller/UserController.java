package controller;

import DAO.UserDAO;
import factory.ConnectionFactory;
import modelo.User;
import java.util.List;

public class UserController {

    private UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO(new ConnectionFactory().recuperarConexion());
    }

    public List<User> cargarUsuarios() {
        return userDAO.cargarUsuarios();
    }
    public List<User> buscarUsuario(String valor) {
        return userDAO.buscarUsuario(valor);
    }

    public void guardarUsuario(User user){
        userDAO.guardarUsuario(user);
    }

    public void modificarUsuario(User user){
        userDAO.modificar(user);
    }

    public void eliminarUsuario(Integer id) {
        userDAO.eliminarUsuario(id);
    }
}
