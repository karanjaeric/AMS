/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.networkmanagement;

import com.ams.beans.NetworkI;
import com.ams.models.AssetBlock;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;




/**
 *
 * @author ekaranja
 */

@Singleton
public class NetworkSynchronization {
    @EJB
    private NetworkI networkbean;
    
    
    @Schedule(second = "*/60", minute = "*", hour = "*", dayOfMonth="*", year="*", persistent = false)
    public void syncNetwork(){
        System.err.println("Synchrozing with the blockchain network");
    
    }
    
     @Schedule(second = "*/45", minute = "*", hour = "*", dayOfMonth="*", year="*", persistent = false)
    public void getLatestBlock(){
        AssetBlock block=networkbean.getLatestBlock();
         System.out.println("Latest Block Id is :"+block.getId());
         System.out.println("Latest Block Name is :"+block.getAssetName());
         System.out.println("Latest Block Owner is :"+block.getBlockOwner());
    
    }

    
}
