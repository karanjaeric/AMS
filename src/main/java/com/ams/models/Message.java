/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.models;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ekaranja
 */
@XmlRootElement
public class Message {
    private Long id;
    private String message;
    private Date created;
    private String author;
    
    public Message(){
    
    }

    public Message(Long id, String message, String author) {
        this.id = id;
        this.message = message;
        this.created = new Date();
        this.author = author;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    
    
}
