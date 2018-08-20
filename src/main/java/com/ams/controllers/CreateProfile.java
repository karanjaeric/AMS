package com.ams.controllers;

import com.ams.beans.NetworkI;
import com.ams.beans.UserI;
import com.ams.models.DappUser;
import com.ams.util.JsonUtil;
import com.ams.util.ResponseObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CreateProfile", urlPatterns = {"/createProfile"})
public class CreateProfile extends HttpServlet {

    @EJB
    private UserI authenticationBean;
    
    @EJB
    private NetworkI networkbean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.print("testing this operation");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {

            DappUser user = new DappUser();
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setUserName(username);
            user.setDappRole(networkbean.findRoleByName("explorer"));
            user.setBlockChainBalance(BigDecimal.ONE);
            String message="";
            if(authenticationBean.findUserByUsername(username) !=null)
                 message = "Sorry.There exists a user with the provided username.Use another username";
            else{
             authenticationBean.saveUser(user);
             message = "Profile created Successfully.Click Login link to Proceed";
            }

          
            ResponseObject obj = new ResponseObject();
            obj.setMessage(message);
            response.getWriter().write(JsonUtil.convertObjectsToJson(obj));

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
