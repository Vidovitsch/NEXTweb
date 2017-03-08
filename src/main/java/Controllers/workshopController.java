/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Models.Workshop;
import java.util.ArrayList;
import java.util.List;
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
    
    @RequestMapping(value = "/workshops", method = RequestMethod.GET)
    public ModelAndView initWorkshopScreen() {
        ModelAndView modelView = new ModelAndView("workshop");
        modelView.addObject("workshops", divideWorkshops());
        
        return modelView;
    }
    
    //Test method
    private ArrayList<Workshop> getWorkshops() {
        ArrayList<Workshop> workshops = new ArrayList();
        workshops.add(new Workshop("W1", "test", "12-12-12"));
        workshops.add(new Workshop("W2", "test", "12-12-12"));
        workshops.add(new Workshop("W3", "test", "12-12-12"));
        workshops.add(new Workshop("W4", "test", "12-12-12"));
        workshops.add(new Workshop("W5", "test", "12-12-12"));
        workshops.add(new Workshop("W6", "test", "12-12-12"));
        
        return workshops;
    }
    
    private ArrayList<Workshop[]> divideWorkshops() {
        ArrayList<Workshop[]> workshopsDivided = new ArrayList();
        ArrayList<Workshop> workshops = getWorkshops();
        
        for (int i = 0; i < workshops.size(); i = i + ROWNUMBER) {
            workshopsDivided.add(new Workshop[]{workshops.get(i), workshops.get(i + 1), workshops.get(i + 2)});
        }
        
        return workshopsDivided;
    }
}
