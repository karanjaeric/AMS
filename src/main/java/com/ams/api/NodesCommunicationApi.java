/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.api;

import com.ams.beans.NetworkI;
import com.ams.models.AssetBlock;
import com.ams.models.AssetTransaction;
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
@Path("nodesCommunicationapi/")
public class NodesCommunicationApi {

    @EJB
    private NetworkI networkbean;

    public void sendAssetForApproval(AssetBlock asset) {

    }

    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("receiveAssetForApproval")
    @POST

    public AssetBlock receiveAssetForApproval(AssetBlock asset) {
        asset.setAssetName("name from approvs");
        return networkbean.saveBlock(asset);

    }

    public void sendAssetTransactionForApproval(AssetTransaction assetTransaction) {

    }

    public void receiveAssetTransactionForApproval(AssetTransaction assetTransaction) {

    }
    
    
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("allAssets")
//    @GET
//    public List<AssetBlock> getAllAssets(){
//        return networkbean.allAssets();
//    }

}
