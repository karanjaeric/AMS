/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.beans;

import com.ams.models.AssetBlock;
import com.ams.models.AssetRegStatus;
import com.ams.models.DappRole;
import com.ams.models.DappUser;
import com.ams.models.Node;
import com.ams.models.YesNo;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.security.ntlm.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.ws.rs.core.MediaType;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.math.BigDecimal;
import java.util.Arrays;
import javax.ejb.EJB;

/**
 *
 * @author ekaranja
 */
@Stateless
public class NetworkBean implements NetworkI {
    @PersistenceContext(unitName = "APP_PU")
    private EntityManager em;

    @EJB
    private ClonnedDataI clonnedDatabean;
    
    @EJB
    private NetworkI networkBean;

    @Override
    public AssetBlock getLatestBlock() {

        List<AssetBlock> blockList = em.createQuery("select b from AssetBlock b order by b.id desc").setMaxResults(1).getResultList();
        if (blockList == null) {
            return null;
        } else {
            return blockList.get(0);
        }
    }

    @Override
    public Node getLatestNode() {

        return null;
    }

    @Override
    public AssetBlock saveBlock(AssetBlock assetBlock) {
        return em.merge(assetBlock);
    }

    @Override
    public AssetBlock generateGenesisBlock(AssetBlock block) {

        return null;

    }

    @Override
    public Node getForwardPeer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node getBackPeer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node registerNode(Node node) {
        return em.merge(node);
    }

    @Override
    public Node findNodeByIpAddress(String ipAddress) {
        return (Node) em.createQuery("SELECT N FROM Node N where N.ipAddress= :ipAddress").setParameter("ipAddress", ipAddress).getResultList().get(0);

    }

    @Override
    public List<AssetBlock> allAssets(String assetStatus) {
//        YesNo rejectedStatus=null;
//        if(AssetRegStatus.valueOf(assetStatus)==AssetRegStatus.APPROVED){
//            rejectedStatus=YesNo.NO;
//    
//    }
        return em.createQuery("SELECT A FROM AssetBlock A where A.regStatus= :assetStatus").setParameter("assetStatus", AssetRegStatus.valueOf(assetStatus)).getResultList();
    }

//clone transactions
    @Override
    public String cloneNetwork(String cloneFromIp) {

        //clone nodes
        //clone users
        //clone assets
         String REST_URI = "http://"+cloneFromIp+":8080/AMS/api/nodeApi/";
         System.out.println("clonning from "+ REST_URI);
        
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(REST_URI);
        WebResource msgService = service.path("approvedAssets");
        ArrayList<AssetBlock> assets = new ArrayList<>();
        JSONArray data = new JSONArray(msgService.accept(MediaType.APPLICATION_JSON).get(String.class));
        ObjectMapper mapper = new ObjectMapper();
        System.err.println("data length is "+data.length());
        
        if(networkBean.getNodeList().isEmpty()){
            return "Join the network first for you to clone the network";
        
        }
        
        for (int i = 0; i < data.length(); i++) {
            try {
                JSONObject assetblock = data.getJSONObject(i);
                AssetBlock assetBlock = mapper.readValue(assetblock.toString(), AssetBlock.class);
                if(networkBean.findAssetByAssetCode(assetBlock.getAssetCode())==null){
                    
                    assets.add(assetBlock);
                
                }
                     assets.add(assetBlock);
                   
                    
                

            } catch (IOException ex) {
                Logger.getLogger(NetworkBean.class.getName()).log(Level.SEVERE, null, ex);
                return "we have a problem clonning the network.Ensure that you join the network first";
            }
        }
        System.out.println(assets.size());
        //persist cloned assests
        if (!assets.isEmpty()) {
            for (AssetBlock assetBlock : assets) {
                clonnedDatabean.saveClonnedAsset(assetBlock);
            }

        }
         return "Network has been clonned successfully.Create an Account/Login to view asset Listings";
    }
//     @Override
//    public Node currentNode(){
//    
//           return (Node) em.createQuery("SELECT N FROM Node N").getResultList().get(0);
//  
//    }
//    
//     @Override
//    public void saveClonedAssets(AssetBlock assetBlock){
//        
//        em.persist(assetBlock);
//        
//    
//    }

    @Override
    public List<Node> getNodeList() {

        return em.createQuery("SELECT N FROM Node N").getResultList();
    }

    @Override
    public AssetBlock findAssetById(long id) {

        List<AssetBlock> blockList = em.createQuery("select b from AssetBlock b where b.id= :id").setParameter("id", id).getResultList();
        if (blockList == null) {
            return null;
        } else {
            return blockList.get(0);
        }
    }

    @Override
    public AssetBlock findAssetByAssetCode(String assetCode) {

        List<AssetBlock> blockList = em.createQuery("select b from AssetBlock b where b.assetCode= :assetCode").setParameter("assetCode", assetCode).getResultList();
//        if (blockList == null) {
//            return null;
//        } else {
//            return blockList.get(0);
//        }

if(blockList.isEmpty())
    return null;
else
    return blockList.get(0);
    
            
    }

    @Override
    public int previousHashCode() {
        List<AssetBlock> blockList = em.createQuery("select b from AssetBlock b  order by b.id desc")
                                                                                       .setMaxResults(1).getResultList();

        if (blockList.size() > 0) {
            return blockList.get(0).getHashCode();
        }

        return 0;

    }

    @Override
    public int calculateHashcode(AssetBlock block, int previousHashCode) {
        String[] assetProperties = {block.getAssetCode(), block.getAssetValue().toString(), block.getAssetDescription()};
        Object[] contents = {Arrays.hashCode(assetProperties), previousHashCode};
        return Arrays.hashCode(contents);
    }

    @Override
    public String validateAsset(long id) {
        List<AssetBlock> blockList = em.createQuery("select b from AssetBlock b where b.id= :id").setParameter("id", id).getResultList();
        if (blockList == null) {
            return null;
        } else {
            AssetBlock block = blockList.get(0);
            String[] assetProperties = {block.getAssetCode(), block.getAssetValue().toString(), block.getAssetDescription()};
            Object[] contents = {Arrays.hashCode(assetProperties), block.getPreviousHashCode()};
            int calculatedHashCode = Arrays.hashCode(contents);
            if (block.getHashCode() == calculatedHashCode) {
                return "Details of this asset are correct as provided on the blockchain.Thank You";
            } else {

                return "Details of this asset are NOT CORRECT as provided on the blockchain.Kindly contact any county asset manager and report this asset.Thank you.";
            }

        }

    }

    @Override
    public void updateBlockChainBalance(DappUser user, BigDecimal coins) {
        System.out.println("member id is"+user.getUserName());
         System.out.println("earned coin"+coins);
        
       BigDecimal currentCoins=user.getBlockChainBalance()==null?BigDecimal.ZERO:user.getBlockChainBalance();
        user.setBlockChainBalance(currentCoins.add(coins));
        em.merge(user);
       

    }

    @Override
    public List<AssetBlock> allAssets() {
        
                return em.createQuery("SELECT A FROM AssetBlock A where A.regStatus= :assetStatus").setParameter("assetStatus", AssetRegStatus.valueOf("APPROVED")).getResultList();

    }

    @Override
    public DappRole findRoleByName(String rolename) {
    
        
            List<DappRole> roleLists= em.createQuery("Select D from DappRole D where D.roleName=:rolename").setParameter("rolename", rolename).getResultList();
            if(roleLists.isEmpty())
                return null;
            else
                return roleLists.get(0);
    }

}
