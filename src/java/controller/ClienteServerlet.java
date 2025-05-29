/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.*;

import java.util.Arrays;
import java.util.List;

import util.JsonResponse;
/**
 *
 * @author anton
 */
public class ClienteServerlet extends HttpServlet {

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
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        Gson gson = new Gson();
        String jsonResponse;
        
        try (PrintWriter out = response.getWriter()) {
            //email/senha nulos
            if(email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()){
               jsonResponse = gson.toJson(new JsonResponse(false, "Email e senha são obrigatórios."));
               out.print(jsonResponse);
               out.flush();
               return;
            }
           
            //admin
            if("admin".equals(email) && "admin".equals(password)) {
               jsonResponse = gson.toJson(new JsonResponse(true, "Administrador logado com sucesso", "Admin"));
               response.setStatus(HttpServletResponse.SC_OK);
               out.print(jsonResponse);
               out.flush();
               return;
            }
           
            DAO.ClienteDAO clienteDAO = new DAO.ClienteDAO(); //
            model.Cliente cliente = null;
           
            try {
                cliente = clienteDAO.selecionarPorEmail(email); //
            } catch (RuntimeException e) {
                e.printStackTrace();

                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500 Internal Server Error
                jsonResponse = gson.toJson(new JsonResponse(false, e.toString()));
                out.print(jsonResponse);
                out.flush();
                return;
            }finally{
                jsonResponse = gson.toJson(new JsonResponse(true, cliente.toString(), cliente));
               response.setStatus(HttpServletResponse.SC_OK);
               out.print(jsonResponse);
               out.flush();
               return;
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
    
    class ClienteDetails {
        int id;
        String nome;
        String email;
        String telefone;
        String cpf;

        public ClienteDetails(int id, String nome, String email, String telefone, String cpf) {
            this.id = id;
            this.nome = nome;
            this.email = email;
            this.telefone = telefone;
            this.cpf = cpf;
        }
        
        
    }
}


