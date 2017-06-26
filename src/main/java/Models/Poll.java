/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import java.util.ArrayList;
import java.util.TreeSet;


/**
 *
 * @author David
 */
public class Poll {

    private int phase;
    private String header;
    private ArrayList<PollIdea> ideas = new ArrayList();
            
    public Poll(int phase) {
        this.phase = phase;
        setTitleByPhase(phase);
    }
    
    public int getPhase() {
        return phase;
    }
    
    public void setPhase(int phase) {
        this.phase = phase;
    }
    
    public String getHeader() {
        return header;
    }
    
    public void setHeader(String header) {
        this.header = header;
    }
    
    public ArrayList<PollIdea> getIdeas() {
        return ideas;
    }
    
    public void setIdeas(ArrayList<PollIdea> ideas) {
        this.ideas = ideas;
    }
    
    private void setTitleByPhase(int phase) {
        switch (phase) {
            case 1:
                header = "Vote for the best idea";
                break;
            case 2:
                header = "Results of the poll";
                break;
            default:
                header = "Got any ideas?";
                break;
        }
    }
}
