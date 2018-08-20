/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.controllers;

import com.ams.beans.NetworkI;
import com.ams.util.JsonUtil;
import com.ams.util.ResponseObject;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ekaranja
 */
@WebServlet(name = "ValidateAsset", urlPatterns = {"/validateAsset"})
public class ValidateAsset extends HttpServlet {

    @EJB
    private NetworkI networkbean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("assetId"));
        networkbean.validateAsset(id);

        String message = networkbean.validateAsset(id);
        ResponseObject obj = new ResponseObject();
        obj.setMessage(message);
        response.getWriter().write(JsonUtil.convertObjectsToJson(obj));

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
