package com.ams.controllers;

import com.ams.beans.NetworkI;
import com.ams.beans.UserI;
import com.ams.models.DappUser;
import com.ams.models.Node;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ekaranja
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @EJB
    private UserI authenticationBean;
    @EJB
    private NetworkI networkbean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Node node = null;
            if (networkbean.getNodeList() != null && networkbean.getNodeList().size() > 0) {
                node = networkbean.getNodeList().get(0);
            }else{
            
             node = new Node();
            }
            System.out.print(node.getNumberOfUsers());
           

            DappUser user = authenticationBean.login(username, password);
            if (user == null) {
                user = new DappUser();
            }

            if (user.isValid()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", user);
                session.setAttribute("currentNode", node);
                response.sendRedirect("index.jsp");
            } else {
                 HttpSession session = request.getSession(true);
                 session.setAttribute("loginMessage", "User with the provided username/password does not exist!!");
                 //response.sendRedirect("index.jsp"); //error page 
                 response.sendRedirect("errorpage.jsp"); //error page 
                  
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
