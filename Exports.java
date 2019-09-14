
/**
 * This is a program that analyzes CSV data on countries and their exports.
 * 
 * @author Kayla Holmes
 * @version 8/13/19
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {
    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord record : parser){
            String myCountry = record.get("Country");
            if (myCountry.contains(country)){
               String exports = record.get("Exports");
               String value = (record.get("Value (dollars)"));
               String info = myCountry+": "+exports+": "+value;
               return info;
            } 
        }
        return "NOT FOUND";
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)){
                System.out.println(record.get("Country"));
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            String country = record.get("Country");
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()){
                System.out.print(country + " ");
                System.out.println(value);
            }
        }
    }
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //String info = countryInfo(parser, "Nauru");
        //System.out.println(info);
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //int result = numberOfExporters(parser, "cocoa");
        //System.out.println(result);
        bigExporters(parser, "$999,999,999,999");
    }
}
