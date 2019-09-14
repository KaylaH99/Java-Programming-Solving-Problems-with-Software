
/**
 * This is a program that analyzes CSV Weather Data from NC
 * 
 * @author Kayla Holmes
 * @version 8/13/19
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Temperature {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser){
            if (smallestSoFar == null){
                smallestSoFar = currentRow;
            } else {
                double currTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if (currTemp == -9999){
                    continue; 
                }
                if (currTemp < smallestTemp){
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }
    public String fileWithColdestTemperature(){
        File fileName = null;
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for ( File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if ( smallestSoFar == null){
                smallestSoFar = currentRow;
                fileName = f;
            } else {
                double currTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if (currTemp < smallestTemp){
                    smallestSoFar = currentRow;
                    fileName = f;
                }
            }
        }
        return fileName.getAbsolutePath();
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for (CSVRecord currentRow : parser){
            if (currentRow.get("Humidity").equals("N/A")){
                    continue; 
                }
            else if (smallestSoFar == null){
                smallestSoFar = currentRow;
            } else { 
                double currHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double smallestHumidity = Double.parseDouble(smallestSoFar.get("Humidity"));
                if (currHumidity < smallestHumidity){
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for ( File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (smallestSoFar == null){
                smallestSoFar = currentRow;
            } else { 
                double currHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double smallestHumidity = Double.parseDouble(smallestSoFar.get("Humidity"));
                if (currHumidity < smallestHumidity){
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }
    public double averageTemperatureInFile(CSVParser parser){
        double tempSum = 0.0;
        double totalTemp = 0.0;
        for (CSVRecord record : parser){
            double temp = Double.parseDouble(record.get("TemperatureF"));
            tempSum = tempSum + temp;
            totalTemp++;
        }
        double averageTemp = tempSum/totalTemp;
        return averageTemp;
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser,int value){
        double tempSum = 0.0;
        double totalTemp = 0.0;
        for (CSVRecord record : parser){
            double temp = Double.parseDouble(record.get("TemperatureF"));
            int humidity = Integer.parseInt(record.get("Humidity"));
            if (humidity >= value){
                tempSum = tempSum + temp;
                totalTemp++;
            }
        }
        double averageTemp = tempSum/totalTemp;
        return averageTemp;
    }
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (Double.isNaN(avg)){
            System.out.println("No Temperature was found");
        } else {
            System.out.println("Average Temperature is " + avg);
        }
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);
        System.out.println("Average Temperature in file is " +avg);
    }
    public void testLowestHumidityInManyFiles(){
        CSVRecord smallest = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + smallest.get("Humidity") +
        " at " + smallest.get("DateUTC"));
    }
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        String lowest = csv.get("Humidity");
        String date = csv.get("DateUTC");
        System.out.println("Lowest Humidity was " +lowest+ " at " +date);
    }
    public void testFileWithColdestTemperature(){
        String file = fileWithColdestTemperature();
        File f = new File(file);
        String fileName = f.getName();
        System.out.println("Coldest day was in file " +fileName);
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestTemp = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " +lowestTemp.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        CSVParser parser2 = fr.getCSVParser();
        for (CSVRecord record : parser2){
            double temp = Double.parseDouble(record.get("TemperatureF"));
            String date = record.get("DateUTC");
            System.out.println(date + ": " + temp);
        }
    }
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Temperature Was: " + 
        smallest.get("TemperatureF") + " at " + smallest.get("DateUTC"));
    }
}
