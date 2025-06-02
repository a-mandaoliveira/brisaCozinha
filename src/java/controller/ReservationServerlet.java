package controller;

import DAO.ReservaDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Reserva;
import util.JsonResponse;

public class ReservationServerlet extends HttpServlet {

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
        
        HttpSession session = request.getSession(false);
        
        String data = null;
        String hora = null;
        int pessoas = 0;
        int mesas = 0;
        
        if(op.equals("efetuarReserva")) {
            data = request.getParameter("data");
            hora = request.getParameter("hora");       
            pessoas = Integer.parseInt(request.getParameter("pessoas"));
            mesas = Integer.parseInt(request.getParameter("mesas"));
        }else if(op.equals("listMyReservations")) {
            
        }
        
        int cliente = (Integer) session.getAttribute("clienteId");
        
        Gson gson = new Gson();
        String jsonResponse;
        
        try (PrintWriter out = response.getWriter()) {
            if(op.equals("efetuarReserva")) {
                if(data == null || data.trim().isEmpty() || hora == null || hora.trim().isEmpty() || pessoas <= 0 || mesas <= 0) {
                    jsonResponse = gson.toJson(new JsonResponse(false, "Preencha todos os campos para fazer uma reserva"));
                    out.print(jsonResponse);
                    out.flush();
                    return;
                }
                ReservaDAO reservaDAO = new ReservaDAO(); 
                Reserva reservaADD = new Reserva(data, hora, pessoas, mesas, cliente);
                boolean res = false;
                try {
                    res = reservaDAO.cadastrarReserva(reservaADD);
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
            } else if(op.equals("listMyReservations")) {
                ReservaDAO reservaDao = new ReservaDAO();
                List<Reserva> reservas = reservaDao.consultarMinhasReservas(cliente);
                jsonResponse = gson.toJson(reservas);
                response.setStatus(HttpServletResponse.SC_OK);
                out.print(jsonResponse);
            } else if(op.equals("deleteReservation")) {
                int id = Integer.parseInt(request.getParameter("id"));
                
                boolean res = false;
                
                ReservaDAO reservaDao = new ReservaDAO();
                try{
                    res = reservaDao.deletar(id);
                }catch(ClassNotFoundException err1) {
                    System.out.println(err1);    
                }catch(SQLException err2) {
                    System.out.println(err2);
                }
                
                if(res){
                    jsonResponse = gson.toJson(new JsonResponse(res, "Reserva cancelado com sucesso"));
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print(jsonResponse);
                }else{
                    jsonResponse = gson.toJson(new JsonResponse(res, "Falha em cancelar sua reserva"));
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    out.print(jsonResponse);
                }
            }
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
