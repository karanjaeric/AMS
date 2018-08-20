/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ekaranja
 */
public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {

        messages.put(new Long(1), new Message(new Long(1), "Hello world", "Eric Karanja"));
        messages.put(new Long(2), new Message(new Long(2), "Hello world", "Eric Karanja"));
    }

    public List<Message> getAllMessages() {

        return new ArrayList<>(messages.values());
    }

    public Message getMessage(Long id) {
        return messages.get(id);
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + new Long(1));
        messages.put(message.getId(), message);
        return message;
    }

    public Message updateMessage(Message message) {

        if (message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(Long id) {

        return messages.remove(id);
    }

}
