package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Mesa;
import DAO.MesaDAO;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import util.JsonResponse;
import java.sql.SQLException;

/**
 *
 * @author anton
 */
public class TableServerlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String op = request.getParameter("op");
        
        Gson gson = new Gson();
        String jsonResponse;
        
        try (PrintWriter out = response.getWriter()) {
            if(op.equals("listTable")){
                MesaDAO mesaDao = new MesaDAO();
                List<Mesa> mesas = mesaDao.consultarTodos();
                jsonResponse = gson.toJson(mesas);
                response.setStatus(HttpServletResponse.SC_OK);
                out.print(jsonResponse);
            } else if(op.equals("updateTable")){
                int id = Integer.parseInt(request.getParameter("id"));
                int numero = Integer.parseInt(request.getParameter("numero"));
                int lugares = Integer.parseInt(request.getParameter("lugares"));
                String status = request.getParameter("status");
                Double preco = Double.valueOf(request.getParameter("preco"));
                
                boolean res = false;
                
                MesaDAO mesaDao = new MesaDAO();
                Mesa m = new Mesa(id, numero, lugares, status, preco);
                try{
                    res = mesaDao.atualizar(m);
                }catch(ClassNotFoundException err1) {
                    System.out.println(err1);    
                }catch(SQLException err2) {
                    System.out.println(err2);
                }
                
                if(res){
                    jsonResponse = gson.toJson(new JsonResponse(res, "Atuatilização efetuada com sucesso"));
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print(jsonResponse);
                }else{
                    jsonResponse = gson.toJson(new JsonResponse(res, "Falha na atualização"));
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.print(jsonResponse);
                }
                
            }else if(op.equals("deleteTable")){
                int id = Integer.parseInt(request.getParameter("id"));
                
                boolean res = false;
                
                MesaDAO mesaDao = new MesaDAO();
                try{
                    res = mesaDao.deletar(id);
                }catch(ClassNotFoundException err1) {
                    System.out.println(err1);    
                }catch(SQLException err2) {
                    System.out.println(err2);
                }
                
                if(res){
                    jsonResponse = gson.toJson(new JsonResponse(res, "Mesa removida com sucesso"));
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print(jsonResponse);
                }else{
                    jsonResponse = gson.toJson(new JsonResponse(res, "Falha em deletar mesa"));
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.print(jsonResponse);
                }
            } else if(op.equals("addTable")){
                int numero = Integer.parseInt(request.getParameter("numero"));
                int lugares = Integer.parseInt(request.getParameter("lugares"));
                String status = request.getParameter("status");
                Double preco = Double.valueOf(request.getParameter("preco"));
                if(status == null || status.trim().isEmpty() || preco <= 0 || numero <=0 || lugares <=0) {
                    jsonResponse = gson.toJson(new JsonResponse(false, "Preencha todos os campos para cadastrar uma mesa"));
                    out.print(jsonResponse);
                    out.flush();
                    return;
                }
                MesaDAO mesaDAO = new MesaDAO(); 
                Mesa reservaADD = new Mesa(numero, lugares, preco, status);
                boolean res = false;
                try {
                    res = mesaDAO.cadastrarMesa(reservaADD);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ReservationServerlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ReservationServerlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                if(res) {
                    jsonResponse = gson.toJson(new JsonResponse(true, "Reserva efetuada com sucesso."));
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print(jsonResponse);
                    out.flush();
                }else{
                    jsonResponse = gson.toJson(new JsonResponse(false, "Erro ao efetuar reserva"));
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.print(jsonResponse);
                    out.flush();
                }
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                jsonResponse = gson.toJson(new JsonResponse(false, "Operação desconhecida: " + op));
                out.print(jsonResponse);
            }
            out.flush();
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
