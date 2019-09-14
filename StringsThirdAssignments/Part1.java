
/**
 * This is a program that finds all the genes in a DNA strand 
 * 
 * @author Kayla Holmes 
 * @version 8/13/19
 */
import edu.duke.*;
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
    public StorageResource getAllGenes(String dna){
        int startIndex = 0;
        StorageResource geneList = new StorageResource();
        while (true){
            String currGene = findGene(dna,startIndex);
            if (currGene.isEmpty()){
                break;
            }
            geneList.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return geneList;
    }
    public int letterCount(String dna, String letter){
        int Index = 0;
        int Count = 0;
        while (true){
            int l = dna.indexOf(letter, Index);
            if (l == -1){
                break;
            }
            Count = Count + 1;
            Index = l+1;        
        }
        return Count;
    }
    public float cgRatio(String dna){
        int cCount = letterCount(dna,"C");
        int gCount = letterCount(dna, "G");
        float cgCount = cCount+gCount;
        float dnaLength = dna.length();
        float cgRatio = cgCount/dnaLength;
        return cgRatio;
    }
    public int countCTG(String dna){
        int ctgIndex = 0;
        int ctgCount = 0;
        while (true){
            int ctg = dna.indexOf("CTG", ctgIndex);
            if (ctg == -1){
                break;
            }
            ctgCount = ctgCount + 1;
            ctgIndex = ctg+3;        
        }
        return ctgCount;
    }
    public void processGenes(String dna){
        System.out.println("Testing processGenes on " + dna);
        StorageResource genes = getAllGenes(dna); 
        for (String g : genes.data()){
            if (g.length() > 60)System.out.println("Gene Longer than 60 characters: "+ g);;;;
        }
        int count9 = 0;
        for (String g : genes.data()){
            if (g.length() > 60) {
                count9 = count9 + 1;
            }
        }
        System.out.println("Number of Genes with 60 Characters: " + count9);
        for (String g: genes.data()){
            float cgRatio = cgRatio(dna); 
            if (cgRatio > 0.35) System.out.println(g + " CGRatio > 0.35");
        }
        int cgCount = 0;
        for (String g: genes.data()){
            float cgRatio = cgRatio(dna); 
            if (cgRatio > 0.35) cgCount = cgCount+1;
        }
        System.out.println("Number of genes with cgCount > 0.35: " + cgCount);
        int longestGene = 0;
        for (String g: genes.data()){
            if (longestGene < g.length()) longestGene = g.length();
        }
        System.out.println("Longest Gene Length: "+longestGene); 
    }
    public void testProcessGenes(){
        String dna = "acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggatccaaagagaggccaacattttttgaaatttttaagacacgctgcaacaaagcagatttaggaccaataagtcttaattggtttgaagaactttcttcagaagctccaccctataattctgaacctgcagaagaatctgaacataaaaacaacaattacgaaccaaacctatttaaaactccacaaaggaaaccatcttataatcagctggcttcaactccaataatattcaaagagcaagggctgactctgccgctgtaccaatctcctgtaaaagaattagataaattcaaattagacttaggaaggaatgttcccaatagtagacataaaagtcttcgcacagtgaaaactaaaatggatcaagcagatgatgtttcctgtccacttctaaattcttgtcttagtgaaagtcctgttgttctacaatgtacacatgtaacaccacaaagagataagtcagtggtatgtgggagtttgtttcatacaccaaagtttgtgaagggtcgtcagacaccaaaacatatttctgaaagtctaggagctgaggtggatcctgatatgtcttggtcaagttctttagctacaccacccacccttagttctactgtgctcatagtcagaaatgaagaagcatctgaaactgtatttcctcatgatactactgctaatgtgaaaagctatttttccaatcatgatgaaagtctgaagaaaaatgatagatttatcgcttctgtgacagacagtgaaaacacaaatcaaagagaagctgcaagtcatggatttggaaaaacatcagggaattcatttaaagtaaatagctgcaaagaccacattggaaagtcaatgccaaatgtcctagaagatgaagtatatgaaacagttgtagatacctctgaagaagatagtttttcattatgtttttctaaatgtagaacaaaaaatctacaaaaagtaagaactagcaagactaggaaaaaaattttccatgaagcaaacgctgatgaatgtgaaaaatctaaaaaccaagtgaaagaaaaatactcatttgtatctgaagtggaaccaaatgatactgatccattagattcaaatgtagcaaatcagaagccctttgagagtggaagtgacaaaatctccaaggaagttgtaccgtctttggcctgtgaatggtctcaactaaccctttcaggtctaaatggagcccagatggagaaaatacccctattgcatatttcttcatgtgaccaaaatatttcagaaaaagacctattagacacagagaacaaaagaaagaaagattttcttacttcagagaattctttgccacgtatttctagcctaccaaaatcagagaagccattaaatgaggaaacagtggtaaataagagagatgaagagcagcatcttgaatctcatacagactgcattcttgcagtaaagcaggcaatatctggaacttctccagtggcttcttcatttcagggtatcaaaaagtctatattcagaataagagaatcacctaaagagactttcaatgcaagtttttcaggtcatatgactgatccaaactttaaaaaagaaactgaagcctctgaaagtggactggaaatacatactgtttgctcacagaaggaggactccttatgtccaaatttaattgataatggaagctggccagccaccaccacacagaattctgtagctttgaagaatgcaggtttaatatccactttgaaaaagaaaacaaataagtttatttatgctatacatgatgaaacatcttataaaggaaaaaaaataccgaaagaccaaaaatcagaactaattaactgttcagcccagtttgaagcaaatgcttttgaagcaccacttacatttgcaaatgctgattcaggtttattgcattcttctgtgaaaagaagctgttcacagaatgattctgaagaaccaactttgtccttaactagctcttttgggacaattctgaggaaatgttctagaaatgaaacatgttctaataatacagtaatctctcaggatcttgattataaagaagcaaaatgtaataaggaaaaactacagttatttattaccccagaagctgattctctgtcatgcctgcaggaaggacagtgtgaaaatgatccaaaaagcaaaaaagtttcagatataaaagaagaggtcttggctgcagcatgtcacccagtacaacattcaaaagtggaatacagtgatactgactttcaatcccagaaaagtcttttatatgatcatgaaaatgccagcactcttattttaactcctacttccaaggatgttctgtcaaacctagtcatgatttctagaggcaaagaatcatacaaaatgtcagacaagctcaaaggtaacaattatgaatctgatgttgaattaaccaaaaatattcccatggaaaagaatcaagatgtatgtgctttaaatgaaaattataaaaacgttgagctgttgccacctgaaaaatacatgagagtagcatcaccttcaagaaaggtacaattcaaccaaaacacaaatctaagagtaatccaaaaaaatcaagaagaaactacttcaatttcaaaaataactgtcaatccagactctgaagaacttttctcagacaatgagaataattttgtcttccaagtagctaatgaaaggaataatcttgctttaggaaatactaaggaacttcatgaaacagacttgacttgtgtaaacgaacccattttcaagaactctaccatggttttatatggagacacaggtgataaacaagcaacccaagtgtcaattaaaaaagatttggtttatgttcttgcagaggagaacaaaaatagtgtaaagcagcatataaaaatgactctaggtcaagatttaaaatcggacatctccttgaatatagataaaataccagaaaaaaataatgattacatgaacaaatgggcaggactcttaggtccaatttcaaatcacagttttggaggtagcttcagaacagcttcaaataaggaaatcaagctctctgaacataacattaagaagagcaaaatgttcttcaaagatattgaagaacaatatcctactagtttagcttgtgttgaaattgtaaataccttggcattagataatcaaaagaaactgagcaagcctcagtcaattaatactgtatctgcacatttacagagtagtgtagttgtttctgattgtaaaaatagtcatataacccctcagatgttattttccaagcaggattttaattcaaaccataatttaacacctagccaaaaggcagaaattacagaactttctactatattagaagaatcaggaagtcagtttgaatttactcagtttagaaaaccaagctacatattgcagaagagtacatttgaagtgcctgaaaaccagatgactatcttaaagaccacttctgaggaatgcagagatgctgatcttcatgtcataatgaatgccccatcgattggtcaggtagacagcagcaagcaatttgaaggtacagttgaaattaaacggaagtttgctggcctgttgaaaaatgactgtaacaaaagtgcttctggttatttaacagatgaaaatgaagtggggtttaggggcttttattctgctcatggcacaaaactgaatgtttctactgaagctctgcaaaaagctgtgaaactgtttagtgatattgagaatattagtgaggaaacttctgcagaggtacatccaataagtttatcttcaagtaaatgtcatgattctgttgtttcaatgtttaagatagaaaatcataatgataaaactgtaagtgaaaaaaataataaatgccaactgatattacaaaataatattgaaatgactactggcacttttgttgaagaaattactgaaaattacaagagaaatactgaaaatgaagataacaaatatactgctgccagtagaaattctcataacttagaatttgatggcagtgattcaagtaaaaatgatactgtttgtattcataaagatgaaacggacttgctatttactgatcagcacaacatatgtcttaaattatctggccagtttatgaaggagggaaacactcagattaaagaagatttgtcagatttaacttttttggaagttgcgaaagctcaagaagcatgtcatggtaatacttcaaataaagaacagttaactgctactaaaacggagcaaaatataaaagattttgagacttctgatacattttttcagactgcaagtgggaaaaatattagtgtcgccaaagagtcatttaataaaattgtaaatttctttgatcagaaaccagaagaattgcataacttttccttaaattctgaattacattctgacataagaaagaacaaaatggacattctaagttatgaggaaacagacatagttaaacacaaaatactgaaagaaagtgtcccagttggtactggaaatcaactagtgaccttccagggacaacccgaacgtgatgaaaagatcaaagaacctactctattgggttttcatacagctagcgggaaaaaagttaaaattgcaaaggaatctttggacaaagtgaaaaacctttttgatgaaaaagagcaaggtactagtgaaatcaccagttttagccatcaatgggcaaagaccctaaagtacagagaggcctgtaaagaccttgaattagcatgtgagaccattgagatcacagctgccccaaagtgtaaagaaatgcagaattctctcaataatgataaaaaccttgtttctattgagactgtggtgccacctaagctcttaagtgataatttatgtagacaaactgaaaatctcaaaacatcaaaaagtatctttttgaaagttaaagtacatgaaaatgtagaaaaagaaacagcaaaaagtcctgcaacttgttacacaaatcagtccccttattcagtcattgaaaattcagccttagctttttacacaagttgtagtagaaaaacttctgtgagtcagacttcattacttgaagcaaaaaaatggcttagagaaggaatatttgatggtcaaccagaaagaataaatactgcagattatgtaggaaattatttgtatgaaaataattcaaacagtactatagctgaaaatgacaaaaatcatctctccgaaaaacaagatacttatttaagtaacagtagcatgtctaacagctattcctaccattctgatgaggtatataatgattcaggatatctctcaaaaaataaacttgattctggtattgagccagtattgaagaatgttgaagatcaaaaaaacactagtttttccaaagtaatatccaatgtaaaagatgcaaatgcatacccacaaactgtaaatgaagatatttgcgttgaggaacttgtgactagctcttcaccctgcaaaaataaaaatgcagccattaaattgtccatatctaatagtaataattttgaggtagggccacctgcatttaggatagccagtggtaaaatcgtttgtgtttcacatgaaacaattaaaaaagtgaaagacatatttacagacagtttcagtaaagtaattaaggaaaacaacgagaataaatcaaaaatttgccaaacgaaaattatggcaggttgttacgaggcattggatgattcagaggatattcttcataactctctagataatgatgaatgtagcacgcattcacataaggtttttgctgacattcagagtgaagaaattttacaacataaccaaaatatgtctggattggagaaagtttctaaaatatcaccttgtgatgttagtttggaaacttcagatatatgtaaatgtagtatagggaagcttcataagtcagtctcatctgcaaatacttgtgggatttttagcacagcaagtggaaaatctgtccaggtatcagatgcttcattacaaaacgcaagacaagtgttttctgaaatagaagatagtaccaagcaagtcttttccaaagtattgtttaaaagtaacgaacattcagaccagctcacaagagaagaaaatactgctatacgtactccagaacatttaatatcccaaaaaggcttttcatataatgtggtaaattcatctgctttctctggatttagtacagcaagtggaaagcaagtttccattttagaaagttccttacacaaagttaagggagtgttagaggaatttgatttaatcagaactgagcatagtcttcactattcacctacgtctagacaaaatgtatcaaaaatacttcctcgtgttgataagagaaacccagagcactgtgtaaactcagaaatggaaaaaacctgcagtaaagaatttaaattatcaaataacttaaatgttgaaggtggttcttcagaaaataatcactctattaaagtttctccatatctctctcaatttcaacaagacaaacaacagttggtattaggaaccaaagtgtcacttgttgagaacattcatgttttgggaaaagaacaggcttcacctaaaaacgtaaaaatggaaattggtaaaactgaaactttttctgatgttcctgtgaaaacaaatatagaagtttgttctacttactccaaagattcagaaaactactttgaaacagaagcagtagaaattgctaaagcttttatggaagatgatgaactgacagattctaaactgccaagtcatgccacacattctctttttacatgtcccgaaaatgaggaaatggttttgtcaaattcaagaattggaaaaagaagaggagagccccttatcttagtgggagaaccctcaatcaaaagaaacttattaaatgaatttgacaggataatagaaaatcaagaaaaatccttaaaggcttcaaaaagcactccagatggcacaataaaagatcgaagattgtttatgcatcatgtttctttagagccgattacctgtgtaccctttcgcacaactaaggaacgtcaagagatacagaatccaaattttaccgcacctggtcaagaatttctgtctaaatctcatttgtatgaacatctgactttggaaaaatcttcaagcaatttagcagtttcaggacatccattttatcaagtttctgctacaagaaatgaaaaaatgagacacttgattactacaggcagaccaaccaaagtctttgttccaccttttaaaactaaatcacattttcacagagttgaacagtgtgttaggaatattaacttggaggaaaacagacaaaagcaaaacattgatggacatggctctgatgatagtaaaaataagattaatgacaatgagattcatcagtttaacaaaaacaactccaatcaagcagcagctgtaactttcacaaagtgtgaagaagaacctttagatttaattacaagtcttcagaatgccagagatatacaggatatgcgaattaagaagaaacaaaggcaacgcgtctttccacagccaggcagtctgtatcttgcaaaaacatccactctgcctcgaatctctctgaaagcagcagtaggaggccaagttccctctgcgtgttctcataaacagctgtatacgtatggcgtttctaaacattgcataaaaattaacagcaaaaatgcagagtcttttcagtttcacactgaagattattttggtaaggaaagtttatggactggaaaaggaatacagttggctgatggtggatggctcataccctccaatgatggaaaggctggaaaagaagaattttatagggctctgtgtgacactccaggtgtggatccaaagcttatttctagaatttgggtttataatcactatagatggatcatatggaaactggcagctatggaatgtgcctttcctaaggaatttgctaatagatgcctaagcccagaaagggtgcttcttcaactaaaatacagatatgatacggaaattgatagaagcagaagatcggctataaaaaagataatggaaagggatgacacagctgcaaaaacacttgttctctgtgtttctgacataatttcattgagcgcaaatatatctgaaacttctagcaataaaactagtagtgcagatacccaaaaagtggccattattgaacttacagatgggtggtatgctgttaaggcccagttagatcctcccctcttagctgtcttaaagaatggcagactgacagttggtcagaagattattcttcatggagcagaactggtgggctctcctgatgcctgtacacctcttgaagccccagaatctcttatgttaaagatttctgctaacagtactcggcctgctcgctggtataccaaacttggattctttcctgaccctagaccttttcctctgcccttatcatcgcttttcagtgatggaggaaatgttggttgtgttgatgtaattattcaaagagcataccctatacagtggatggagaagacatcatctggattatacatatttcgcaatgaaagagaggaagaaaaggaagcagcaaaatatgtggaggcccaacaaaagagactagaagccttattcactaaaattcaggaggaatttgaagaacatgaagaaaacacaacaaaaccatatttaccatcacgtgcactaacaagacagcaagttcgtgctttgcaagatggtgcagagctttatgaagcagtgaagaatgcagcagacccagcttaccttgagggttatttcagtgaagagcagttaagagccttgaataatcacaggcaaatgttgaatgataagaaacaagctcagatccagttggaaattaggaaggccatggaatctgctgaacaaaaggaacaaggtttatcaagggatgtcacaaccgtgtggaagttgcgtattgtaagctattcaaaaaaagaaaaagattcagttatactgagtatttggcgtccatcatcagatttatattctctgttaacagaaggaaagagatacagaatttatcatcttgcaacttcaaaatctaaaagtaaatctgaaagagctaacatacagttagcagcgacaaaaaaaactcagtatcaacaactaccggtttcagatgaaattttatttcagatttaccagccacgggagccccttcacttcagcaaatttttagatccagactttcagccatcttgttctgaggtggacctaataggatttgtcgtttctgttgtgaaaaaaacaggacttgcccctttcgtctatttgtcagacgaatgttacaatttactggcaataaagttttggatagaccttaatgaggacattattaagcctcatatgttaattgctgcaagcaacctccagtggcgaccagaatccaaatcaggccttcttactttatttgctggagatttttctgtgttttctgctagtccaaaagagggccactttcaagagacattcaacaaaatgaaaaatactgttgagaatattgacatactttgcaatgaagcagaaaacaagcttatgcatatactgcatgcaaatgatcccaagtggtccaccccaactaaagactgtacttcagggccgtacactgctcaaatcattcctggtacaggaaacaagcttctgatgtcttctcctaattgtgagatatattatcaaagtcctttatcactttgtatggccaaaaggaagtctgtttccacacctgtctcagcccagatgacttcaaagtcttgtaaaggggagaaagagattgatgaccaaaagaactgcaaaaagagaagagccttggatttcttgagtagactgcctttacctccacctgttagtcccatttgtacatttgtttctccggctgcacagaaggcatttcagccaccaaggagttgtggcaccaaatacgaaacacccataaagaaaaaagaactgaattctcctcagatgactccatttaaaaaattcaatgaaatttctcttttggaaagtaattcaatagctgacgaagaacttgcattgataaatacccaagctcttttgtctggttcaacaggagaaaaacaatttatatctgtcagtgaatccactaggactgctcccaccagttcagaagattatctcagactgaaacgacgttgtactacatctctgatcaaagaacaggagagttcccaggccagtacggaagaatgtgagaaaaataagcaggacacaattacaactaaaaaatatatctagggcctcatgggcccagctttcttgtacaaagtggt";
        processGenes(dna.toUpperCase());
    }
    
    public void testcgRatio(){
        String dna = "ATGCCATAG";
        float result = cgRatio(dna);
        System.out.println(result);
    }
    public void testCount(){
        String dna = "ACTCCCGCATAA";
        System.out.println(dna);
        String letter = "C";
        System.out.println("Letter: " + letter);
        int result = letterCount(dna, letter);
        System.out.println(result);
        
        dna = "CACTCCCGCATAA";
        System.out.println(dna);
        letter = "G";
        System.out.println("Letter: " + letter);
        result = letterCount(dna, letter);
        System.out.println(result);
    }
    public void testOn(String dna){
        System.out.println("testing printAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna); 
        for (String g : genes.data()){
            System.out.println(g);
        }
    }
    public void test(){
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}
