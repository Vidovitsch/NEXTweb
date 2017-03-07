package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

@Controller
public class mainController extends ParameterizableViewController{
    
   private String message = "No message specified";
 
    public String getMessage() {
        return(message);
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
