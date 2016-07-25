package com.gyngro.vanbora;

import java.io.Serializable;

/**
 * Created by Alexandre Lara on 01/07/2016.
 */

public class Event implements Serializable {

    String datetime, message, sender, type;

    public Event() {

    }

    public Event(String datetime, String message, String sender, String type) {
        this.datetime = datetime;
        this.message = message;
        this.sender = sender;
        this.type = type;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
