/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;


/**
 *
 * @author David
 */
public class PollIdea {

    private String ideaId;
    private String content;
    private int votes;
    
    public PollIdea(String ideaId, String content) {
        this.ideaId = ideaId;
        this.content = content;
    }
    
    public String getIdeaId() {
        return ideaId;
    }
    
    public void setIdeaId(String ideaId) {
        this.ideaId = ideaId;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public int getVotes() {
        return votes;
    }
    
    public void setVotes(int votes) {
        this.votes = votes;
    }
}
