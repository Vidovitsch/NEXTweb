package Enums;

/**
 *
 * @author David
 */
public enum Course {
    
    Media_Design,
    Software_Engineering,
    Business,
    Techniek;
    
    /**
     * @param course
     * 
     * @return
     */
    public static String getPrefix(Course course) {
        switch (course) {
            case Media_Design:
                return "M";
            case Software_Engineering:
                return "S";
            case Business:
                return "B";
            default:
                return "T";
        }
    }
    
    public static Course getCourse(char prefix) {
        switch (prefix) {
            case 'M':
                return Media_Design;
            case 'S':
                return Software_Engineering;
            case 'B':
                return Business;
            default:
                return Techniek;
        }
    }
};
