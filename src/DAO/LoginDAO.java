package DAO;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    private final Connection con;
    public LoginDAO(Connection con){
        this.con = con;
    }
    public void login(){
        try{
            String sql = "SELECT * FROM HUESPEDES";
            final PreparedStatement statement = con.prepareStatement(sql);
            try(statement) {
                final ResultSet resultSet = statement.executeQuery();
                try(resultSet){
                    while (resultSet.next()){
                        System.out.println(resultSet.getString("NOMBRE"));
                        System.out.println(resultSet.getString("APELLIDO"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
