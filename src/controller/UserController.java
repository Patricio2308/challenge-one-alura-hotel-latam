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

    public List<User> cargarUsiarios() {
        return userDAO.leerUsuarios();
    }
    public void guardarUsuario(User user){
        userDAO.guardarUsuario(user);
    }
}
