
/**
 * A program made to analyze CSV Files on the data of baby ranks
 * 
 * @author Kayla Holmes 
 * @version 8/13/19
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class BabyNames {
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int totalBoyNames = 0;
        int totalGirlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;  
            totalNames ++;
            if (rec.get(1).equals("M")){
                totalBoys += numBorn;
                totalBoyNames ++;
            } else {
                totalGirls += numBorn;
                totalGirlNames++;
            }
        }
        System.out.println("total births = " +totalBirths);
        System.out.println("total boys = " +totalBoys);
        System.out.println("total girls = " +totalGirls);
        System.out.println("total names = " +totalNames);
        System.out.println("total girl names = " +totalGirlNames);
        System.out.println("total boy names = " +totalBoyNames);
    }
    public long getNumGirls(FileResource fr){
        long totalGirlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals("F")){
                totalGirlNames ++;
                }
        }
        return totalGirlNames;
    }
    public long getRank(int year, String name, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year +
        ".csv");
        long rank = -1; 
        for (CSVRecord rec : fr.getCSVParser(false)){
                String currName = rec.get(0);
                String currGender = rec.get(1);
                if (currName.equals(name) && 
                    currGender.equals(gender)){
                    rank = rec.getRecordNumber();
                    if (currGender.equals("M")){
                            long girlNames = getNumGirls(fr);
                            rank -= girlNames;
                    }
                }
        }
        return rank;
    }
    public long getRankFR(String name, String gender){
        FileResource fr = new FileResource();
        long rank = -1; 
        for (CSVRecord rec : fr.getCSVParser(false)){
                String currName = rec.get(0);
                String currGender = rec.get(1);
                if (currName.equals(name) && 
                    currGender.equals(gender)){
                    rank = rec.getRecordNumber();
                    if (currGender.equals("M")){
                            long girlNames = getNumGirls(fr);
                            rank -= girlNames;
                    }
                }
        }
        return rank;
    }
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year +
        ".csv");
        String name = "NO NAME";
        for (CSVRecord rec : fr.getCSVParser(false)){
            long currRank = rec.getRecordNumber();
            String currGender = rec.get(1);
            if (currGender.equals("M")){
                long girlNames = getNumGirls(fr);
                currRank -= girlNames; 
            }
            if (currGender.equals(gender) &&
                currRank == rank){
                name = rec.get(0);
                break;
                }
        }
        return name;
    }
    public void whatIsNameInYear(String name, int year,
                                int newYear, String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year +
        ".csv");
        long rank = getRank(year, name, gender);
        String newName = getName(newYear, (int)rank, gender);
        if (gender.equals("F")){
            System.out.println(name + " born in "+ year + 
                            " would be " + newName + 
                            " if she was born in " + newYear);
        } else {
            System.out.println(name + " born in "+ year + 
                            " would be " + newName + 
                            " if he was born in " + newYear);
        }
    }
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();  
        int year = -1;
        long highestRank = 0;
        String fileName = "";
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            for (CSVRecord rec : fr.getCSVParser(false)){
               String currName = rec.get(0);
               String currGender = rec.get(1);
               long girlNames = getNumGirls(fr);
               if (currName.equals(name) && 
               currGender.equals(gender)){
                   if(highestRank == 0){
                       highestRank = rec.getRecordNumber(); 
                       fileName = f.getName();
                       if (currGender.equals("M")){
                           highestRank -= girlNames;
                       }
                   } else {
                       long currRank = rec.getRecordNumber();
                       if (currGender.equals("M")){
                           currRank -= girlNames;
                       }
                       if (currRank < highestRank){
                           highestRank = currRank;
                           fileName = f.getName();
                       }
                   }
                   fileName = fileName.replaceAll("[^\\d]", "");
                   year = Integer.parseInt(fileName);
               }
            }
        }
        System.out.println(highestRank);
        return year;
    }
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double rankSum = 0.0;
        double totalRanks = 0.0;
        for (File f : dr.selectedFiles()){
           FileResource fr = new FileResource(f);
           for (CSVRecord rec : fr.getCSVParser(false)){
               String currName = rec.get(0);
               String currGender = rec.get(1);
               if (currName.equals(name) && 
                    currGender.equals(gender)){
                        long currRank = rec.getRecordNumber();
                        if (currGender.equals("M")){
                            long girlNames = getNumGirls(fr);
                            currRank = currRank - girlNames;
                        }
                        rankSum += currRank;
                        totalRanks ++;
               }
           }
        }
        double averageRank = rankSum/totalRanks;
        if (Double.isNaN(averageRank)){
            return -1;
        }
        return averageRank;
    }
    public int getTotalBirthsRankedHigher(int year, String name,
                                            String gender){
        FileResource fr = new FileResource("us_babynames_by_year/yob" + year +
        ".csv");
        int totalBirths = 0;
        long rank = getRank(year, name, gender);
        for (CSVRecord rec : fr.getCSVParser(false)){
              int currBorn = Integer.parseInt(rec.get(2));
              String currGender = rec.get(1);
              long currRank = rec.getRecordNumber();
              if (currGender.equals("M")){
                  long girlNames = getNumGirls(fr);
                  currRank -= girlNames; 
              }
              if(gender.equals(currGender) &&
                rank > currRank){
                    totalBirths += currBorn;
                }
        }
        return totalBirths;
    }
    public void testGetTotalBirthsRankedHigher(){
        int result = getTotalBirthsRankedHigher(1990, "Drew","M");
        System.out.println(result);
    }
    public void testGetAverageRank(){
        double result = getAverageRank("Robert", "M");
        System.out.println(result);
    }
    public void testYearOfHighestRank(){
        int result = yearOfHighestRank("Mich", "M");
        System.out.println(result);
    }
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Owen", 1974, 2014, "M");
    }
    public void testGetName(){
        String result = getName(1982, 450, "M");
        System.out.println(result);
    }
    public void testGetRankFR(){
        long result = getRankFR("Genevieve","F"); 
        System.out.println(result);
    }
    public void testGetRank(){
        long result = getRank(1880, "Genevieve","F"); 
        System.out.println(result);
    }
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
}
