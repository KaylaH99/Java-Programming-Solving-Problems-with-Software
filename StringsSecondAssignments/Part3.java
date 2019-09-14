
/**
 * This is a program that finds and prints all the genes in a DNA Strand 
 * 
 * @author Kayla Holmes
 * @version 8/13/19
 */
public class Part3 {
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
    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while (true){
            String currGene = findGene(dna,startIndex);
            if (currGene.isEmpty()){
                break;
            }
            count = count + 1;
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return count;
    }
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
    public void testCountGenes(){
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println(dna);
        int result = countGenes(dna);
        System.out.println("Number of genes " + result);
        
        dna = "ATGTAAGATGCCCTAGTTAA";
        System.out.println(dna);
        result = countGenes(dna);
        System.out.println("Number of genes " + result);
        
        dna = "ATGTAAGATGCCTAGT";
        System.out.println(dna);
        result = countGenes(dna);
        System.out.println("Number of genes " + result);
        
        dna = "ATGTAAATGTAGATGTGA";
        System.out.println(dna);
        result = countGenes(dna);
        System.out.println("Number of genes " + result);
        
        dna = "TAAGCCCTAGT";
        System.out.println(dna);
        result = countGenes(dna);
        System.out.println("Number of genes " + result);
    }
}
