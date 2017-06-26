package Models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author David
 */
public class Announcement {

    private String id;
    private String content;
    private String dateTime;
    
    public Announcement(String id, String content, String dateTime) {
        this.id = id;
        this.content = content;
        this.dateTime = dateTime;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getDateTime() {
        return this.dateTime;
    }
    
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    
    public Date toDate(String dateTime) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.ENGLISH);
        Date date = format.parse(dateTime);
        
        return date;
    }
}
