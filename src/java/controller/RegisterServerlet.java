package controller;

import DAO.ClienteDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import util.JsonResponse;

/**
 *
 * @author anton
 */
@WebServlet(name = "RegisterServerlet", urlPatterns = {"/RegisterServerlet"})
public class RegisterServerlet extends HttpServlet {

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
        
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
            
        Gson gson = new Gson();
        String jsonResponse;
        
        try (PrintWriter out = response.getWriter()) {
            if(fullName == null || fullName.trim().isEmpty() || phone == null || phone.trim().isEmpty() || cpf == null || 
                    cpf.trim().isEmpty() || email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                jsonResponse = gson.toJson(new JsonResponse(false, "Preencha todos os campos para se cadastrar"));
                out.print(jsonResponse);
                out.flush();
                return;
            }
            ClienteDAO clienteDAO = new ClienteDAO(); 
            Cliente clienteADD = new Cliente(0, fullName, phone, cpf, email, password);
            System.out.println(clienteADD);
            boolean res = clienteDAO.cadastrarCliente(clienteADD);
            System.out.println(res);
            if(res) {
                jsonResponse = gson.toJson(new JsonResponse(true, "Cadastro efetuado com sucesso."));
                response.setStatus(HttpServletResponse.SC_OK);
                out.print(jsonResponse);
                out.flush();
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
