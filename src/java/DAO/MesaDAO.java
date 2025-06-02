/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mesa;
import util.ConectaBanco;

public class MesaDAO {

    public boolean cadastrarMesa(Mesa m) throws ClassNotFoundException, SQLException {
        boolean res = false;
        
        PreparedStatement stmt = null;
        Connection con = null;
        String queryCadastrarReserva = "INSERT INTO mesa (numero, lugares, valorReserva, `status`) VALUES (?,?,?,?)";
        try{
            con = util.ConectaBanco.getConexao();
            stmt = con.prepareStatement(queryCadastrarReserva);
            
            stmt.setInt(1, m.getNumeroMesa());
            stmt.setInt(2, m.getQntdLugar());
            stmt.setDouble(3, m.getValorReserva());
            stmt.setString(4, m.getStatus());
            stmt.executeUpdate();
            res = true;
        }catch(SQLException err1){
            throw new RuntimeException(err1);
        }catch (RuntimeException errDb) {
            Logger.getLogger(MesaDAO.class.getName()).log(Level.SEVERE, "Erro de Runtime ao consultar todas as mesas (possivelmente conexao).", errDb);
            throw new RuntimeException("Erro interno (DAO) ao consultar todas as mesas: " + errDb.getMessage(), errDb);
        }finally{
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }
    
    public boolean deletar(int id) throws ClassNotFoundException, SQLException {
        boolean res = false;
        
        PreparedStatement stmt = null;
        Connection con = null;
        String queryDeletar = "DELETE FROM mesa WHERE id = ?";
        try{
            con = util.ConectaBanco.getConexao();
            stmt = con.prepareStatement(queryDeletar);
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
            res = true;
        }catch(SQLException err1){
            throw new RuntimeException(err1);
        }catch (RuntimeException errDb) {
            Logger.getLogger(MesaDAO.class.getName()).log(Level.SEVERE, "Erro de Runtime ao consultar todas as mesas (possivelmente conexao).", errDb);
            throw new RuntimeException("Erro interno (DAO) ao consultar todas as mesas: " + errDb.getMessage(), errDb);
        }finally{
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }
    
    public boolean atualizar(Mesa m) throws ClassNotFoundException, SQLException {
        boolean res = false;
        
        PreparedStatement stmt = null;
        Connection con = null;
        String queryAtualizar = "UPDATE mesa SET numero = ?, lugares = ?, valorReserva = ?, status = ? WHERE id = ?";
        try{
            con = util.ConectaBanco.getConexao();
            stmt = con.prepareStatement(queryAtualizar);
            
            stmt.setInt(1, m.getNumeroMesa());
            stmt.setInt(2, m.getQntdLugar());
            stmt.setDouble(3, m.getValorReserva());
            stmt.setString(4, m.getStatus());
            stmt.setInt(5, m.getIdMesa());
            stmt.executeUpdate();
            res = true;
        }catch(SQLException err1){
            throw new RuntimeException(err1);
        }catch (RuntimeException errDb) {
            Logger.getLogger(MesaDAO.class.getName()).log(Level.SEVERE, "Erro de Runtime ao consultar todas as mesas (possivelmente conexao).", errDb);
            throw new RuntimeException("Erro interno (DAO) ao consultar todas as mesas: " + errDb.getMessage(), errDb);
        }finally{
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return res;
    }

    public Mesa consultarById(Mesa m) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement("select * from mesa where id = ?");
        comando.setInt(1, m.getIdMesa());
        ResultSet rs = comando.executeQuery();
        Mesa mesa = null;
        if (rs.next()){
           mesa = new Mesa(rs.getInt("idMesa"), rs.getInt("numeroMesa"), rs.getInt("qntdLugar"),rs.getString("status"), rs.getDouble("valorReserva"));
        }        
        return mesa;
    }
    
    
    public List<Mesa> consultarTodos() {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        
        List<Mesa> lmesa = new ArrayList<>();
        String queryConsultarTodos = "SELECT * FROM mesa";
        
        try{
            con = util.ConectaBanco.getConexao();
            stmt = con.prepareStatement(queryConsultarTodos);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Mesa mesa = new Mesa(rs.getInt("id"), rs.getInt("numero"), rs.getInt("lugares"),rs.getString("status"), rs.getDouble("valorReserva"));
                lmesa.add(mesa);
            }
        }catch(SQLException err1){
            throw new RuntimeException(err1);
        }catch (RuntimeException errDb) {
            Logger.getLogger(MesaDAO.class.getName()).log(Level.SEVERE, "Erro de Runtime ao consultar todas as mesas (possivelmente conexao).", errDb);
            throw new RuntimeException("Erro interno (DAO) ao consultar todas as mesas: " + errDb.getMessage(), errDb);
        }finally{
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
        return lmesa;
    }
}
