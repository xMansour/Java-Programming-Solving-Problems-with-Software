public class GenesFinder {
    public static void main(String[] args) {
        GenesFinder genesFinder = new GenesFinder();
        genesFinder.printAllGenes("GTTAATGTAGCTTAAACCTTTAAAGCAAGGCACTGAAAATGCCTAGATGAGTGAGCTCACTCCATAGACACAAAGGTTTGGTCCTGGCCTTCTTATTAGT");

    }

    //should return the index of the specified stopCodon
    public int findStopCodon(String dna, int startCodonIndex, String stopCodon) {
        int stopCodonIndex = dna.indexOf(stopCodon, startCodonIndex + 3);
        return stopCodonIndex;
    }

    //used for testing findStopCodon() with multiple examples
    public void testFindStopCodon() {
        //                                v            v
        String dna = "GTTAATGTAGCTTAAACCTTTAAAGCAAGGCACTGAAAATGCCTAGATGAGTGAGCTCACTCCATAGACACAAAGGTTTGGTCCTGGCCTTCTTATTAGT";
        int startCodonIndex = 10;
        String stopCodon = "TAA";
        System.out.println("The dna: " + dna + " Contains the following stop \"TAA\" codon at: " + findStopCodon(dna, startCodonIndex, stopCodon));


        startCodonIndex = 22;
        stopCodon = "TGA";
        System.out.println("The dna: " + dna + " Contains the following stop \"TGA\" codon at: " + findStopCodon(dna, startCodonIndex, stopCodon));


    }


    //should find the first occurrence of the start codon ATG
    //Find the index of the first occurrence of the stop codon “TAA”
    //after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. Hint: call findStopCodon.
    //Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG”
    //that is a multiple of three away from the “ATG”. Find the index of the first occurrence of the stop codon “TGA”
    //after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
    //Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away.
    // If there is no valid stop codon and therefore no gene, return the empty string.
    public String findGene(String dna) {

        int startCodonIndex = dna.indexOf("ATG");
        if (startCodonIndex == -1) {
            return "";
        }

        int taaCodonIndex = findStopCodon(dna, startCodonIndex, "TAA");
        int tagCodonIndex = findStopCodon(dna, startCodonIndex, "TAG");
        int tgaCodonIndex = findStopCodon(dna, startCodonIndex, "TGA");

        int minIndex;
        if (taaCodonIndex == -1 || (tagCodonIndex != -1 && tagCodonIndex < taaCodonIndex)) {
            minIndex = tagCodonIndex;
        } else {
            minIndex = taaCodonIndex;
        }

        if (minIndex == -1 || (tgaCodonIndex != -1 && tgaCodonIndex < minIndex)) {
            minIndex = tgaCodonIndex;
        }

        if (minIndex == -1) {
            return "";
        }

        return dna.substring(startCodonIndex, minIndex + 3);
    }


    //Print the DNA string.
    //Calculate the gene by sending this DNA string as an argument to findGene.
    //If a gene exists following our algorithm above, then print the gene, otherwise print the empty string.
    public void testFindGene() {
        String dna = "GTTAATGTAGCTTAAACCTTTAAAGCAAGGCACTGAAAATGCCTAGATGAGTGAGCTCACTCCATAGACACAAAGGTTTGGTCCTGGCCTTCTTATTAGT";
        System.out.println("The dna: " + dna + " Contains the following gene: " + findGene(dna));

        dna = "gatcaaagaacaggagagttcccaggccagtacggaagaatgtgagaaaaataagcaggacacaattacaactaaaaaatatatctagggcctcatgggcccagctttcttgtacaaagtggt";
        System.out.println("The dna: " + dna + " Contains the following gene: " + findGene(dna));


    }


    //you should repeatedly find genes and print each one until there are no more genes.
    public void printAllGenes(String dna) {
        while (true) {
            String gene = findGene(dna);
            if (!gene.isEmpty())
                System.out.println("The dna: " + dna + " Contains the following gene: " + gene);
            else {
                System.out.println("The dna: " + dna + " Contains no gene");    //empty one
                break;
            }
        }

    }
}
