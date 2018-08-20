package com.ams.controllers;
import com.ams.beans.NetworkI;
import com.ams.models.AssetBlock;
import com.ams.models.AssetRegStatus;
import com.ams.models.Node;
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
@WebServlet(name = "AssetController", urlPatterns = {"/assetController"})
public class AssetController extends HttpServlet {
    @EJB
    private NetworkI networkbean;
    

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            Node currentNode=networkbean.getNodeList().get(0);
            
                 String creatingNode="";        
          
           if(networkbean.getNodeList() !=null && networkbean.getNodeList().size()>0)
                creatingNode=networkbean.getNodeList().get(0).getIpAddress();
         
              
              
           
        
            AssetBlock block=new AssetBlock();
            String assetName=request.getParameter("assetName");
            block.setAssetName(assetName);
            block.setAssetDescription(request.getParameter("assetDescription"));
            block.setAssetCode(request.getParameter("assetCode"));
            block.setAssetValue(Double.parseDouble(request.getParameter("assetValue")));
            block.setRegStatus(AssetRegStatus.UNAPPROVED);
            block.setCreatingNode(creatingNode);
            block.setHashCode(networkbean.calculateHashcode(block, networkbean.previousHashCode()));
            block.setPreviousHashCode(networkbean.previousHashCode());
            
            AssetBlock existingBlock=networkbean.findAssetByAssetCode(request.getParameter("assetCode"));
             String message = "";
            if(existingBlock!=null)
                  message = "Asset with the provide code already Exists!!!!";
            else{
                           
            networkbean.saveBlock(block);//save an asset locally
           
           //call the next peer on the network for asset approval
          
           String assetToPeer=JsonUtil.convertObjectsToJson(block);
           System.out.println(assetToPeer);
           
           
           try {

		Client client = Client.create();

		WebResource webResource = client
		   .resource("http://"+currentNode.getCloneFrom()+":8080/AMS/api/nodeApi/receiveAssetForApproval");

		//String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";

		ClientResponse response1 = webResource.type("application/json")
		   .post(ClientResponse.class, assetToPeer);

		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			     + response.getStatus());
		}

		System.out.println("Output from Server .... \n");
		String output = response1.getEntity(String.class);
		System.out.println(output);

	  } catch (Exception e) {

		e.printStackTrace();

	  }
            
            }
                
            ResponseObject obj = new ResponseObject();
            obj.setMessage(message);
            response.getWriter().write(JsonUtil.convertObjectsToJson(obj));
          
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
