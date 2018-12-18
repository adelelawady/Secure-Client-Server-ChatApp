package com.socket;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    public String type, sender, recipient;
    public Object content;

    public Message(String type, String sender, Object content, String recipient) {
        this.type = type;
        this.sender = sender;
        this.content = content;
        this.recipient = recipient;
    }

   
}
