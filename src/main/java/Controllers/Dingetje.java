package Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Michiel van Eijkeren
 */
public class Dingetje
{
    private String ding = "ding";

    public String getDing()
    {
        return ding;
    }

    public void setDing(String ding)
    {
        this.ding = ding;
    }

    public Dingetje(String s)
    {
        ding = s;
    }
    
    
}
