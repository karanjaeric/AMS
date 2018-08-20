/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams;

import com.ams.beans.NetworkI;
import com.ams.models.Node;
import javax.ejb.EJB;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

/**
 *
 * @author ekaranja
 */
public class DappUtil {
       @EJB
    private NetworkI networkbean;
    
    public static void urlPost(String url,Object obj,String objType){
    Client client=ClientBuilder.newClient();
    Response response=client.target(url).request().get();
    }
    
    public  String postToIp(){
        Node node=new Node();
           if(networkbean.getNodeList() !=null && networkbean.getNodeList().size()>0)
               node=networkbean.getNodeList().get(0);
           return node.getCloneFrom();
    
    }
    
}
