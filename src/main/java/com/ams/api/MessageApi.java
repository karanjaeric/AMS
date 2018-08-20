/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.api;

import com.ams.models.Message;
import com.ams.models.MessageService;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ekaranja
 */
@Path("messenges/")
public class MessageApi {
    MessageService messageService=new MessageService();
  
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getMessages")
    public List<Message> getMessages(){
        return messageService.getAllMessages();
    }
    
}
