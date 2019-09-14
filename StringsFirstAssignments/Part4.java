
/**
 * This is an an algorithm to sort through a list of URLs 
 *	and print out all the links to YouTube
 * 
 * @author Kayla Holmes 
 * @version 8/13/19
 */
import edu.duke.*; 
public class Part4 {
    public void findYoutubeUrl(String url){
        URLResource ur = new URLResource(url);
        for(String word : ur.words()){
            if (word.toLowerCase().indexOf("youtube.com") != -1){
                int start = word.indexOf("\"");
                int end = word.lastIndexOf("\"");
                System.out.println(word.substring(start, end));
            }
        }
    }
    public void testUrl() {
        findYoutubeUrl("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
    
    public static void main() {
        Part4 url = new Part4();
        url.testUrl();
    }
}
