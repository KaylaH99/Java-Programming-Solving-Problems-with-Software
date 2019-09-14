
/**
 * This is a program to sort through a string of DNA and print a gene
 * 
 * @author Kayla Holmes
 * @version 8/12/19
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf("TAA",startIndex+3);
        if (stopIndex == -1){
            return "";
        }
        result = dna.substring(startIndex, stopIndex+3);
        if ((startIndex - stopIndex) % 3 != 0) {
            return "";
        }
        return result;
    }

    public void testSimpleGene() {
        String dna = "GCTAGTAACGA";
        System.out.println("DNA Strand is: " + dna);
        String result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
        
        dna = "CTATGTAGCT";
        System.out.println("DNA Strand is: " + dna);
        result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
        
        dna = "CTAAGTAGCT";
        System.out.println("DNA Strand is: " + dna);
        result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
        
        dna = "ACTATGGTCAAGTAAGTC";
        System.out.println("DNA Strand is: " + dna);
        result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
        
        dna = "ACTATGGCAAGTAAGTC";
        System.out.println("DNA Strand is: " + dna);
        result = findSimpleGene(dna);
        System.out.println("Gene is " + result);
    }   
}

