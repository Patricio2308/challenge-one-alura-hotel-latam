package controller;

import DAO.LoginDAO;
import factory.ConnectionFactory;

public class LoginController {
    private LoginDAO loginDAO;

    public LoginController() {
        this.loginDAO = new LoginDAO( new ConnectionFactory().recuperarConexion() );
    }

    public void login(){
        loginDAO.login();
    }
}
