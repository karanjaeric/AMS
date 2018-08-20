/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.api;

import com.ams.beans.NetworkI;
import com.ams.models.AssetApprovalDTO;
import com.ams.models.AssetBlock;
import com.ams.models.AssetRegStatus;
import com.ams.models.YesNo;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ekaranja
 */
@Path("nodeApi/")
public class NodeApi {

    @EJB
    private NetworkI networkbean;

    @POST
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "it works";

    }

    @Produces(MediaType.APPLICATION_JSON)
    @Path("allAssets")
    @GET
    public List<AssetBlock> getAllAssets(String assetStatus) {
        return networkbean.allAssets(assetStatus);
    }
    
    
    @Produces(MediaType.APPLICATION_JSON)
    @Path("approvedAssets")
    @GET
     public List<AssetBlock> getAllApprovedAssets() {
        return networkbean.allAssets();
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("receiveAssetForApproval")
    @POST

    public AssetBlock receiveAssetForApproval(AssetBlock asset) {
        return networkbean.saveBlock(asset);

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public String receiveAsset() {
        return "test2";
    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("approveFromDto")
    @POST

    public AssetBlock ApproveAssetFromDTO(AssetApprovalDTO assetApprovalDTO) {
        AssetBlock asset = networkbean.findAssetByAssetCode(assetApprovalDTO.getAssetCode());
        asset.setApprovingNode(assetApprovalDTO.getApprovingNode());
        //asset.setRegStatus(AssetRegStatus.valueOf(assetApprovalDTO.getApprovalStatus()));
        if(assetApprovalDTO.getFinanceApproved()!=null && assetApprovalDTO.getFinanceApproved()==YesNo.YES)
            asset.setFinanceApproval(YesNo.YES);
        if(assetApprovalDTO.getBuApproved()!=null && assetApprovalDTO.getBuApproved()==YesNo.YES)
            asset.setBeneficiaryUnitApproval(YesNo.YES);
        if(assetApprovalDTO.getStoresApproved()!=null && assetApprovalDTO.getStoresApproved()==YesNo.YES)
            asset.setStoresApproval(YesNo.YES);
        
           if(asset.getBeneficiaryUnitApproval()==YesNo.YES && asset.getFinanceApproval()==YesNo.YES && asset.getStoresApproval()==YesNo.YES)
                asset.setRegStatus(AssetRegStatus.APPROVED);
        System.out.println("Am approving from a remote DTO");
        return networkbean.saveBlock(asset);

    }

}
