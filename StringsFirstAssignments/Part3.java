
/**
 * This is an algorithm that compares one string to another
 *	determine if the stringb appears twice in stringa
 * 
 * @author Kayla Holmes 
 * @version 8/13/19
 */
public class Part3 {
    public String twoOccurrences(String stringa, String stringb){
        String twoOccurrences = "false";
        int occurrences = 0;
        int firstOcc = stringb.indexOf(stringa);
        if (firstOcc == -1){
            return twoOccurrences; 
        } else {
            int second = stringb.indexOf(stringa, firstOcc + stringa.length());
            if (second == -1){
                return twoOccurrences;
            } else {
                twoOccurrences = "true"; 
            }
        }
        return twoOccurrences; 
    }
    public String lastPart(String stringa, String stringb){
        int Occ = stringb.indexOf(stringa);
        if (Occ == -1) {
            return stringb;
        } else {
            String result = stringb.substring(Occ);
            return result;
        }
    }
    public void testing(){
        String stringa = "by";
        String stringb = "A story by Abby Long";
        System.out.println("String A is " + stringa);
        System.out.println("String B is " + stringb);
        String result = twoOccurrences(stringa, stringb);
        System.out.println("Are there two occurrences? " + result);
        
        stringa = "a";
        stringb = "banana";
        System.out.println("String A is " + stringa);
        System.out.println("String B is " + stringb);
        result = twoOccurrences(stringa, stringb);
        System.out.println("Are there two occurrences? " + result);
        
        stringa = "atg";
        stringb = "ctgtatgta";
        System.out.println("String A is " + stringa);
        System.out.println("String B is " + stringb);
        result = twoOccurrences(stringa, stringb);
        System.out.println("Are there two occurrences? " + result);
        
        stringa = "an";
        stringb = "banana";
        System.out.println("String A is " + stringa);
        System.out.println("String B is " + stringb);
        result = lastPart(stringa, stringb);
        System.out.println(result);
        
        stringa = "zoo";
        stringb = "forest";
        System.out.println("String A is " + stringa);
        System.out.println("String B is " + stringb);
        result = lastPart(stringa, stringb);
        System.out.println(result);
    }
}
