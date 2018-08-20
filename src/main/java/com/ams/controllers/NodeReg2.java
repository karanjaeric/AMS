/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.controllers;

import com.ams.beans.NetworkI;
import com.ams.models.Node;
import com.ams.util.JsonUtil;
import com.ams.util.ResponseObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author ekaranja
 */
@WebServlet(name = "NodeReg2", urlPatterns = {"/NodeReg10"})
public class NodeReg2 extends HttpServlet {
       @EJB
    private NetworkI networkbean;

  

 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
                try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
              ResponseObject obj= new ResponseObject();
                    
           Node node=new Node();
           if(networkbean.getNodeList() !=null && networkbean.getNodeList().size()>0)
               node=networkbean.getNodeList().get(0);
           

           node.setIpAddress(request.getParameter("nodeIpAddress"));
           node.setNodeName(request.getParameter("nodeName"));
           node.setNumberOfUsers(Integer.parseInt(request.getParameter("numberOfUsers")));
           node.setCloneFrom(request.getParameter("cloneFromIpAddress"));
           node= networkbean.registerNode(node);

          
            String message = "Node Saved Successfully";
            obj.setMessage(message);
            response.getWriter().write(JsonUtil.convertObjectsToJson(obj));
    }
        
    }
}

    

