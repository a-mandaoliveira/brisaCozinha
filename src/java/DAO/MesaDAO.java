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

/**
 *
 * @author PTOLEDO
 */
public class MesaDAO {

    public void cadastrarMesa(Mesa m) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement("insert into mesa (idMesa,numeroMesa,qntdLugar,valorReserva,status) values (?,?,?,?,?)");
        comando.setInt(1, m.getIdMesa());
        comando.setInt(2, m.getNumeroMesa());
        comando.setInt(3, m.getQntdLugar());
        comando.setDouble(4, m.getValorReserva());
        comando.setString(5, m.getStatus());
        comando.execute();
        con.close();
    }
    
    public void deletar(Mesa m) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement("delete from mesa where id = ?");
        comando.setInt(1, m.getIdMesa());
        comando.execute();
        con.close();
    }
    
    public void atualizar(Mesa m) throws ClassNotFoundException, SQLException {
        Connection con = ConectaBanco.getConexao();
        PreparedStatement comando = con.prepareStatement("update mesa set numeroMesa = ?, qntdLugar = ?, valorReserva = ?, status = ? where id = ?");
        comando.setInt(1, m.getNumeroMesa());
        comando.setInt(2, m.getQntdLugar());
        comando.setDouble(3, m.getValorReserva());
        comando.setString(4, m.getStatus());
        comando.setInt(5, m.getIdMesa());
        comando.execute();
        con.close();
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
        
        List<Mesa> lmesa = new ArrayList<Mesa>();
        String queryConsultarTodos = "SELECT * FROM mesa WHERE `status`='Disponivel'";
        
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
