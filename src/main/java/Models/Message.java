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
    
    private int groupNumber;
    private String pcn;
    private String content;
    private Timestamp date;
    
    public Message(String pcn, int groupNumber, String content, Timestamp date)
    {
        this.groupNumber = groupNumber;
        this.pcn = pcn;
        this.content = content;
        this.date = date;
    }
    
    public int getGroupNumber() {
        return groupNumber;
    }
    
    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }
    
    public String getPcn()
    {
        return pcn;
    }
    
    public void setPcn(String pcn) {
        this.pcn = pcn;
    }
    
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public Timestamp getDate()
    {
        return date;
    }
    
    public void setDate(Timestamp date) {
        this.date = date;
    }
}
