/**
 * This is a algorithm that sorts through a string of DNA to find and print a gene
 * 
 * @author Kayla Holmes 
 * @version 8/13/19
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String result = "";
        if (dna == dna.toUpperCase()){
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        } else {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1){
            return "";
        }
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
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
        String result = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene is " + result);
        
        dna = "CTATGTAGCT";
        System.out.println("DNA Strand is: " + dna);
        result = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene is " + result);
        
        dna = "CTAAGTAGCT";
        System.out.println("DNA Strand is: " + dna);
        result = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene is " + result);
        
        dna = "ACTATGGTCAAGTAAGTC";
        System.out.println("DNA Strand is: " + dna);
        result = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene is " + result);
        
        dna = "ACTATGGCAAGTAAGTC";
        System.out.println("DNA Strand is: " + dna);
        result = findSimpleGene(dna,"ATG","TAA");
        System.out.println("Gene is " + result);
        
        dna = "ACTATGGCAAAGTAAGTC";
        String startCodon = "ATG";
        String stopCodon = "TAA";
        System.out.println("DNA Strand is: " + dna);
        result = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + result);
        
        dna = "gatgctataat";
        startCodon = "ATG";
        stopCodon = "TAA";
        System.out.println("DNA Strand is: " + dna);
        result = findSimpleGene(dna,startCodon,stopCodon);
        System.out.println("Gene is " + result);

    }
}