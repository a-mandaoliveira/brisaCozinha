/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {
    private final String querySelecionarPorEmail = "SELECT id, nome, telefone, cpf, email, senha FROM cliente WHERE email = ?";;
    public model.Cliente selecionarPorEmail(String email){
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        model.Cliente cliente = null;
        
        try {
            con = util.ConectaBanco.getConexao();
            stmt = con.prepareStatement(querySelecionarPorEmail);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            
            rs.next();
            cliente = new model.Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("cpf"), rs.getString("email"), rs.getString("senha"));
            /*if(rs.next()) {
                cliente = new model.Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("telefone"), rs.getString("cpf"), rs.getString("email"), rs.getString("senha"));
            }*/
        } catch(SQLException err1) {
            throw new RuntimeException(err1);
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return cliente;
    }
}
