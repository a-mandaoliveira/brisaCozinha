/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import model.Reserva;

public class ReservaDAO {
    public boolean cadastrarReserva(Reserva r) throws ClassNotFoundException, SQLException {
        boolean res = false;
        
        PreparedStatement stmt = null;
        Connection con = null;
        String queryCadastrarReserva = "INSERT INTO reserva (nrMesa, cliente, `data`, hora, qtdPessoas) VALUES (?,?,?,?,?)";
        try{
            con = util.ConectaBanco.getConexao();
            stmt = con.prepareStatement(queryCadastrarReserva);
            
            stmt.setInt(1, r.getMesa());
            stmt.setInt(2, r.getCliente());
            stmt.setString(3, r.getDtReserva());
            stmt.setString(4, r.getHoraReserva());
            stmt.setInt(5, r.getNumeroPessoas());
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
    
    public List<Reserva> consultarMinhasReservas(int cliente) {
        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        
        List<Reserva> lReserva = new ArrayList<>();
        String queryConsultar = "SELECT * FROM reserva WHERE cliente=?";
        
        try{
            con = util.ConectaBanco.getConexao();
            stmt = con.prepareStatement(queryConsultar);
            stmt.setInt(1, cliente);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Reserva reserva = new Reserva(rs.getInt("id"), rs.getString("data"), rs.getString("hora"), rs.getInt("qtdPessoas"),rs.getInt("nrMesa"), rs.getInt("cliente"));
                lReserva.add(reserva);
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
                
        return lReserva;
    }
    
    public boolean deletar(int id) throws ClassNotFoundException, SQLException {
        boolean res = false;
        
        PreparedStatement stmt = null;
        Connection con = null;
        String queryDeletar = "DELETE FROM reserva WHERE id = ?";
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
}
