/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * This model is used to pass information between the GUI and the business layer
 * @author Michiel van Eijkeren
 */
public class LoginModel
{
    private String currentemail;

    /**
     * Get the value of currentemail
     * 
     * @return the value of currentemail
     */
    public String getCurrentemail()
    {
        return currentemail;
    }

    /**
     * Set the value of currentemail
     * 
     * @param currentemail to currentemail
     */
    public void setCurrentemail(String currentemail)
    {
        this.currentemail = currentemail;
    }

}
