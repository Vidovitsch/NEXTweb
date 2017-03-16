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
    private String date;
    
    public Message(String pcn, int groupNumber, String content, String date)
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
    
    public String getDate()
    {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
}
