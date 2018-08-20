package com.ams.controllers;

import com.ams.beans.NetworkI;
import com.ams.models.AssetBlock;
import com.ams.models.YesNo;
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
@WebServlet(name = "AssetRejection", urlPatterns = {"/assetRejection"})
public class AssetRejection extends HttpServlet {

    @EJB
    private NetworkI networkbean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        long id = Long.parseLong(request.getParameter("assetId"));

        AssetBlock asset = networkbean.findAssetById(id);
        String rejectionType = request.getParameter("type");
        String reasonForRejection = request.getParameter("reasonForRejection");
        System.out.println(id);
        System.out.println(rejectionType);
        System.out.println(reasonForRejection);
        if (rejectionType.equalsIgnoreCase("FINANCEREJECTION")) {
            asset.setFinanceRejection(YesNo.YES);
            asset.setFinanceRejectionComments(reasonForRejection);
            asset.setIsRejected(YesNo.YES);

        }
         if (rejectionType.equalsIgnoreCase("STORESREJECTION")) {
            asset.setStoresRejection(YesNo.YES);
            asset.setStoresRejectionComments(reasonForRejection);
            asset.setIsRejected(YesNo.YES);

        }
          if(rejectionType.equalsIgnoreCase("BENEFICIARYREJECTION")) {
            asset.setBeneficiaryUnitRejection(YesNo.YES);
            asset.setBeneficiaryUnitRejectionComments(reasonForRejection);
            asset.setIsRejected(YesNo.YES);

        }
        asset = networkbean.saveBlock(asset);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
