/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ekaranja
 */
public class DatabaseClass {
    
    private static  Map<Long,Message> messages=new HashMap<>();
     private static  Map<Long,Message> profiles=new HashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<Long, Message> getProfiles() {
        return profiles;
    }
    
}
