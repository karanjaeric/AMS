/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author ekaranja
 */
public class JsonUtil {
    private static ObjectMapper objectMapper;
    static{
    
        objectMapper=new ObjectMapper();
    }
    public static String convertObjectsToJson(Object obj) throws JsonProcessingException{
        String jsonString=objectMapper.writeValueAsString(obj);
        return jsonString;
    
    }
    
}
