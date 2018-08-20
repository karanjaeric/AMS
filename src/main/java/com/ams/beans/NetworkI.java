/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.beans;

import com.ams.models.AssetBlock;
import com.ams.models.DappRole;
import com.ams.models.DappUser;
import com.ams.models.Node;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author ekaranja
 */

@Local
public interface NetworkI {
    AssetBlock getLatestBlock();
    Node getLatestNode();
    AssetBlock saveBlock(AssetBlock assetBlock);
    AssetBlock generateGenesisBlock(AssetBlock block);
    Node getForwardPeer();
    Node getBackPeer();
    Node registerNode(Node node);
    Node findNodeByIpAddress(String ipAddress);
    List<AssetBlock> allAssets(String assetStatus);
    List<AssetBlock> allAssets();
    List<Node> getNodeList();
    String cloneNetwork(String cloneFromIp);
    AssetBlock findAssetById(long id);
    AssetBlock findAssetByAssetCode(String assetCode);
    int previousHashCode();
    int calculateHashcode(AssetBlock block,int previousHashCode);
    String validateAsset(long id);
    void updateBlockChainBalance(DappUser user,BigDecimal coins);
    
    DappRole findRoleByName(String rolename);
     

//    public  Node currentNode();
//
//    public void saveClonedAssets(AssetBlock assetBlock);
    
    
    

   
}
