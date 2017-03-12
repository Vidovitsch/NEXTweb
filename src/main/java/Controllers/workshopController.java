/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Database.DBWorkshopModifier;
import Models.Workshop;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * @author David
 */
@Controller
public class workshopController {

    private final static int ROWNUMBER = 3;
    private DBWorkshopModifier dbMod;
            
    @RequestMapping(value = "/workshops", method = RequestMethod.GET)
    public ModelAndView initWorkshopScreen() {
        dbMod = new DBWorkshopModifier();
        insertDummyWorkshops();
                
        ModelAndView modelView = new ModelAndView("workshop");
        modelView.addObject("workshops", getWorkshops());
        
        return modelView;
    }
    
    //Test method
    private void insertDummyWorkshops() {
        dbMod.insertWorkshop(new Workshop("W1", "test", 5, "12-12-12"));
        dbMod.insertWorkshop(new Workshop("W2", "test", 5, "12-12-12"));
        dbMod.insertWorkshop(new Workshop("W3", "test", 5, "12-12-12"));
        dbMod.insertWorkshop(new Workshop("W4", "test", 5, "12-12-12"));
        dbMod.insertWorkshop(new Workshop("W5", "test", 5, "12-12-12"));
    }
    
    /**
     * Fetching workshops from the database into a list.
     * Each index of the list represents a row of workshops.
     * This amount of workshops is defined in the variable ROWNUMBER.
     * @return 
     */
    private ArrayList<Workshop[]> getWorkshops() {
        ArrayList<Workshop[]> workshopsDivided = new ArrayList();
        ArrayList<Workshop> workshops = dbMod.getWorkshops();
        
        Workshop[] row = new Workshop[ROWNUMBER];
        int wsCounter = 0;
        for (int i = 0; i < workshops.size(); i++) {
            row[wsCounter] = workshops.get(i);
            wsCounter++;
            if (wsCounter == ROWNUMBER || workshops.size() - 1 == i) {
                workshopsDivided.add(row);
                row = new Workshop[remaining(i + 1, workshops.size())];
                wsCounter = 0;
            }
        }
        
        return workshopsDivided;
    }
    
    /**
     * Calculates the length of a row.
     * Example: ROWNUMBER = 3. There are 7 workshops.
     * This means there are 2 rows of 3 workshops and 1 row of 1 workshop.
     * This method calculates the indexes of each row.
     * @param current
     * @param max
     * @return 
     */
    private int remaining(int current, int max) {
        if (max - current > ROWNUMBER) {
            return ROWNUMBER;
        } else {
            return max - current;
        }
    }
}
