
/**
 * This is a program that finds a single gene in a strand of DNA
 * 
 * @author Kayla Holmes
 * @version 8/13/19
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while(currIndex != -1){
            if ((currIndex - startIndex) % 3 == 0){
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return dna.length();
    }
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG",where);
        if (startIndex == -1){ return "";}
        int indexTAA = findStopCodon(dna,startIndex,"TAA");
        int indexTAG = findStopCodon(dna,startIndex,"TAG");
        int indexTGA = findStopCodon(dna,startIndex,"TGA");
        int minIndex = Math.min(indexTAA, Math.min(indexTAG, indexTGA));
        if (minIndex == dna.length()){return "";}
        return dna.substring(startIndex,minIndex+3);
    }
    public void testFindStopCodon(){
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna,9,"TAA");
        if (dex != 21)System.out.println("error on 21");
        dex = findStopCodon(dna, 1, "TAA");
        if (dex != 26) System.out.println("error on 26");
        dex = findStopCodon(dna,0,"TAG");
        if (dex != 26) System.out.println("error on 26 TAG");
        System.out.println("Tests Finished");
    }
    /*public void testFindGene(){
        String dna = "AGGAGTATAAATT";
        System.out.println(dna);
        String result = findGene(dna);
        System.out.println(result);
        
        dna = "AGGATGGAGATGTAA";
        System.out.println(dna);
        result = findGene(dna);
        System.out.println(result);
        
        dna = "AGGATGGTAGAGATGTAATAATGA";
        System.out.println(dna);
        result = findGene(dna);
        System.out.println(result);
        
        dna = "AGGAGTAATT";
        System.out.println(dna);
        result = findGene(dna);
        System.out.println(result);
        
        dna = "AGGATGGTAGAGATGTAATAATGA";
        System.out.println(dna);
        result = findGene(dna);
        System.out.println(result);
    }*/
    public void printAllGenes(String dna){
        int startIndex = 0;
        while (true){
            String currGene = findGene(dna,startIndex);
            if (currGene.isEmpty()){
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }
    public void testOn(String dna){
        System.out.println("testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    public void test(){
        testOn("AATGCTAACTAGCTGACTAAT");
    }
}
