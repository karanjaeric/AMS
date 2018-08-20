/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.controllers;

import com.ams.beans.NetworkI;
import com.ams.models.AssetApprovalDTO;
import com.ams.models.AssetBlock;
import com.ams.models.AssetRegStatus;
import com.ams.models.Node;
import com.ams.models.YesNo;
import com.ams.util.JsonUtil;
import com.ams.util.ResponseObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ApproveAsset", urlPatterns = {"/approveAsset"})
public class ApproveAsset extends HttpServlet {

    @EJB
    private NetworkI networkbean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {

            String approvingNode = "";

            if (networkbean.getNodeList() != null && networkbean.getNodeList().size() > 0) {
                approvingNode = networkbean.getNodeList().get(0).getIpAddress();
            }
            long id = Long.parseLong(request.getParameter("assetId"));
            String approvalCategory = request.getParameter("approvalCategory");
            String financeComments=request.getParameter("financeComments");
            String paymentMode=request.getParameter("paymentMode");
            String storesComment=request.getParameter("storeComments");
            String beneficiaryAllocationComments=request.getParameter("buComments");
            String beneficiary=request.getParameter("beneficiary");
            
           
                

            System.out.println(id);
            String message="";
            
            AssetBlock asset = networkbean.findAssetById(id);
            if(beneficiary !=null)
                asset.setBeneficiary(beneficiary);
            if(beneficiaryAllocationComments !=null)
                asset.setBeneficiaryAllocationComments(beneficiaryAllocationComments);
            if(storesComment !=null)
                asset.setStoresComment(storesComment);
             if(financeComments !=null)
                 asset.setFinanceComments(financeComments);
             if(paymentMode !=null)
                 asset.setPaymentMode(paymentMode);
            
            asset.setApprovingNode(approvingNode);
              AssetApprovalDTO assetDto = new AssetApprovalDTO();
            if (approvalCategory.equalsIgnoreCase("finance")) {
                asset.setFinanceApproval(YesNo.YES);
                asset.setValid(YesNo.YES);
                
                assetDto.setFinanceApproved(YesNo.YES);
                 message="Finance Unit approval successful";
            }else if(approvalCategory.equalsIgnoreCase("beneficiaryAllocation")){
                if(asset.getStoresApproval() !=null && asset.getStoresApproval()==YesNo.YES){
                asset.setBeneficiaryUnitApproval(YesNo.YES);
                asset.setValid(YesNo.YES);
                assetDto.setBuApproved(YesNo.YES);
                message="Beneficiary Unit approval successful";
                
                }else
                    message="Ensure that the asset has been approved by Stores DEPT";
          
            
            }else if(approvalCategory.equalsIgnoreCase("stores")){
                if(asset.getFinanceApproval() !=null && asset.getFinanceApproval()==YesNo.YES){
                asset.setStoresApproval(YesNo.YES);
                asset.setValid(YesNo.YES);
                assetDto.setStoresApproved(YesNo.YES);
                message="Stores Asset Approval successfull";
                
                }
                else
                    message="Ensure that the asset has been approved by finance DEPT";
            
            
            }
            if(asset.getBeneficiaryUnitApproval()==YesNo.YES && asset.getFinanceApproval()==YesNo.YES && asset.getStoresApproval()==YesNo.YES)
                asset.setRegStatus(AssetRegStatus.APPROVED);
            
            //approve remote notes
            
            if(asset.getValid() !=null && asset.getValid()==YesNo.YES){
                  asset = networkbean.saveBlock(asset);

            //call the creating node to approve on its end
          
            assetDto.setApprovingNode(approvingNode);
            assetDto.setAssetCode(asset.getAssetCode());
            

            assetDto.setApprovalStatus("APPROVED");
            String assetDTOToPeer = JsonUtil.convertObjectsToJson(assetDto);
            Node currentNode = networkbean.getNodeList().get(0);

            try {

                Client client = Client.create();

                WebResource webResource = client
                        .resource("http://" + currentNode.getCloneFrom() + ":8080/AMS/api/nodeApi/approveFromDto");

                //String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";
                ClientResponse response1 = webResource.type("application/json")
                        .post(ClientResponse.class, assetDTOToPeer);

                if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }

                 message = "Asset has been approved successfully";
                ResponseObject obj = new ResponseObject();
                obj.setMessage(message);
                response.getWriter().write(JsonUtil.convertObjectsToJson(obj));

            } catch (Exception e) {

                e.printStackTrace();

            }
            
            
            
            }else{
                 ResponseObject obj = new ResponseObject();
                obj.setMessage(message);
                response.getWriter().write(JsonUtil.convertObjectsToJson(obj));
            
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
