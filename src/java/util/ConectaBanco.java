/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author anton
 */
public class ConectaBanco {
    public static java.sql.Connection getConexao() {
        java.sql.Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/dbloja", "root", "");
        }catch(ClassNotFoundException | java.sql.SQLException ex){
            throw new RuntimeException(ex.getMessage());
        }
        return con;
    }
}
