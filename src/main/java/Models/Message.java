/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Timestamp;

/**
 *
 * @author Bert
 */
public class Message {
    private String userName;
    private String content;
    private Timestamp date;
    
    public Message(String name, String content, Timestamp date)
    {
        this.userName = name;
        this.content = content;
        this.date = date;
    }
    
    public String getUserName()
    {
        return userName;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public Timestamp getDate()
    {
        return date;
    }
}
