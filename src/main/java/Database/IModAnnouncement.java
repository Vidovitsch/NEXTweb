/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Announcement;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public interface IModAnnouncement {
    
    public ArrayList<Announcement> fetchAnnouncement();
}