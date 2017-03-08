/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.Random;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author mmjan
 */
@Controller
public class LiveUpdateController
{
    @RequestMapping("/liveupdate")
    public ModelAndView LiveUpdate()
    {
        return new ModelAndView("sparkline", "message", "Sparkline.js Example which accepts JSONArray value every 3 seconds & updates graphs..");
    }
    
    @RequestMapping(value = "/liveupdatetest", method = RequestMethod.GET)
    public @ResponseBody
    String constructJSONArray() throws JSONException {
 
        JSONObject one = new JSONObject();
        JSONObject two = new JSONObject();
        JSONObject three = new JSONObject();
 
        JSONArray result = new JSONArray();
        Random r = new Random();
        int[] r1 = { r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100) };
        int[] r2 = { r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100) };
        int[] r3 = { r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100) };
 
        one.put("one", r1);
        two.put("two", r2);
        three.put("three", r3);
 
        result.put(one);
        result.put(two);
        result.put(three);
 
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("sparkData", result);
        System.out.println("Sendig this data to view (sparkline.jsp): " + jsonObj.toString());
 
        return jsonObj.toString();
    }
}
