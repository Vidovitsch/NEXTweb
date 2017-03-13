/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Database.DBWorkshopModifier;
import Database.IModWorkshop;
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
    private IModWorkshop dbMod = new DBWorkshopModifier();
            
    @RequestMapping(value = "/workshops", method = RequestMethod.GET)
    public ModelAndView initWorkshopScreen() {
        insertDummyWorkshops();
                
        ModelAndView modelView = new ModelAndView("workshop");
        //modelView.addObject("workshops", getWorkshops());
        
        return modelView;
    }
    
    //Test method
    private void insertDummyWorkshops() {
        Workshop ws = new Workshop("TestWorkshop");
        ws.setStartTime("12:00");
        ws.setEndTime("13:00");
        ws.setDate("11-11-2017");
        ws.setLocationName("Hier");
        
        dbMod.insertEvent(ws);
    }
    
    /**
     * Fetching workshops from the database into a list.
     * Each index of the list represents a row of workshops.
     * This amount of workshops is defined in the variable ROWNUMBER.
     * @return list of rows containing workshops
     */
//    private ArrayList<Workshop[]> getWorkshops() {
//        ArrayList<Workshop[]> workshopsDivided = new ArrayList();
//        ArrayList<Workshop> workshops = dbMod.getWorkshops();
//        
//        Workshop[] row = new Workshop[ROWNUMBER];
//        int wsCounter = 0;
//        for (int i = 0; i < workshops.size(); i++) {
//            row[wsCounter] = workshops.get(i);
//            wsCounter++;
//            if (wsCounter == ROWNUMBER || workshops.size() - 1 == i) {
//                workshopsDivided.add(row);
//                row = new Workshop[calcRowLength(i + 1, workshops.size())];
//                wsCounter = 0;
//            }
//        }
//        
//        return workshopsDivided;
//    }
    
    /**
     * Calculates the length of a row.
     * Example: ROWNUMBER = 3. There are 7 workshops.
     * This means there are 2 rows of 3 workshops and 1 row of 1 workshop.
     * This method calculates the indexes of each row.
     * @param current
     * @param max
     * @return the current row length.
     */
    private int calcRowLength(int current, int max) {
        if (max - current > ROWNUMBER) {
            return ROWNUMBER;
        } else {
            return max - current;
        }
    }
}
