/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Models.Announcement;
import java.util.TreeSet;

/**
 *
 * @author David
 */
public interface IModAnnouncement {
    
    // Fetches a sorted list (on date) of announcements from the database
    public TreeSet<Announcement> fetchAnnouncements();
}
