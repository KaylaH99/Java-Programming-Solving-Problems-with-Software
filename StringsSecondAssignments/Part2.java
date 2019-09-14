
/**
 * This is a program that counts the number of times stringa appears in stringb
 * 
 * @author Kayla Holmes
 * @version 8/13/19
 */
public class Part2 {
    public int howMany(String stringa,String stringb){
        int count = 0;
        int currIndex = -1;
        if (stringa.isEmpty() || stringb.isEmpty()){
            return count;
        }
        while (true){    
            currIndex = stringb.indexOf(stringa, currIndex+1);
            if (currIndex == -1){
                return count;
            } else {
                count = count + 1;
            }
        }
    }
    public void testHowMany(){
        String stringa = "an";
        String stringb = "banana";
        int result = howMany(stringa, stringb);
        System.out.println(stringa + " is in " + stringb + " " + result + " times.");
        
        stringa = "Kayla";
        stringb = "Alaya";
        result = howMany(stringa, stringb);
        System.out.println(stringa + " is in " + stringb + " " + result + " times.");
        
        stringa = "a";
        stringb = "Banana";
        result = howMany(stringa, stringb);
        System.out.println(stringa + " is in " + stringb + " " + result + " times.");
        
        stringa = "aaaaa";
        stringb = "aaaaa";
        result = howMany(stringa, stringb);
        System.out.println(stringa + " is in " + stringb + " " + result + " times.");
        
        stringa = " ";
        stringb = "Yoyo";
        result = howMany(stringa, stringb);
        System.out.println(stringa + " is in " + stringb + " " + result + " times.");
        
    }
}
