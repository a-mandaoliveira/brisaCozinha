/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;

public class ClienteDAO {
    private String querySelecionarPorEmail;
    public model.Cliente selecionarPorEmail(String email){
        ResultSet rs = null;
        Statement stmt = null;
        Connection con = null;
        model.Cliente cliente = null;
        
        try {
            con = util.ConectaBanco.getConexao();
            stmt = con.createStatement();
            rs = stmt.executeQuery(querySelecionarPorEmail);
            rs.next();
            cliente = new model.Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("cpf"), rs.getString("email"), rs.getString("senha"));
            
            con.close();
            stmt.close();
        } catch(SQLException err1) {
            throw new RuntimeException(err1);
        }
        
        return cliente;
    }
}
