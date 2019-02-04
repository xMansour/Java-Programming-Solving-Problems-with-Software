public class Part1 {
    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.testSimpleGene();

    }

    public String findSimpleGene(String dna) {
        //find the first index of the start codon "ATG"
        int startCodonIndex = dna.indexOf("ATG");
        if (startCodonIndex == -1) {
            return "";
        }

        //find the first index of the stop codon "TAA"
        int stopCodonIndex = dna.indexOf("TAA", startCodonIndex + 3);
        if (stopCodonIndex == -1) {
            return "";
        }
        String resultGene = dna.substring(startCodonIndex, stopCodonIndex + 3);
        if (resultGene.length() % 3 != 0) {
            return "";
        }
        return resultGene;
    }

    public void testSimpleGene() {
        //invalid, no start codon
        String dna = "GTCGTATAA";
        System.out.println("The gene of the dna: " + dna + ", is: " + findSimpleGene(dna));

        //invalid, no stop codon
        dna = "ATGAAGCGTTTG";
        System.out.println("The gene of the dna: " + dna + ", is: " + findSimpleGene(dna));

        //invalid, no start or stop codon
        dna = "ATCAGCTGC";
        System.out.println("The gene of the dna: " + dna + ", is: " + findSimpleGene(dna));

        //valid dna
        dna = "AATAATGTAGCCGTAA";
        System.out.println("The gene of the dna: " + dna + ", is: " + findSimpleGene(dna));

        //invalid, not a multiple of 3
        dna = "ATGAATGCTGCTAA";
        System.out.println("The gene of the dna: " + dna + ", is: " + findSimpleGene(dna));

    }

}
