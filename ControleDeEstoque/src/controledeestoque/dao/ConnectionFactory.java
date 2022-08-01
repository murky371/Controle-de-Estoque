/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controledeestoque.dao;
import java.sql.*;
/**
 *
 * @author Joao
 */
public class ConnectionFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/DBExercicios";
    private static  String USER = "UserExercicios";
    private static final String PASS = "1234";
    
    public static Connection getConnection()
         {
        try{
            System.out.print("Conectado ao banco de dados");
            return DriverManager.getConnection(URL, USER, PASS);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    
    
}
