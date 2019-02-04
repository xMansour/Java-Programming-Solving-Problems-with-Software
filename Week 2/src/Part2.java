public class Part2 {
    public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testSimpleGene();

    }

    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        boolean lower = false;
        if (dna.equals(dna.toLowerCase())) {
            lower = true;
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }

        //find the first index of the startCodon
        int startCodonIndex = dna.indexOf(startCodon);
        if (startCodonIndex == -1) {
            return "";
        }

        //find the first index of the stopCodon
        int stopCodonIndex = dna.indexOf(stopCodon, startCodonIndex + startCodon.length());
        if (stopCodonIndex == -1) {
            return "";
        }
        String resultGene = dna.substring(startCodonIndex, stopCodonIndex + stopCodon.length());
        if (resultGene.length() % 3 != 0) {
            return "";
        }
        if (lower)
            return resultGene.toLowerCase();
        else

            return resultGene.toUpperCase();
    }

    public void testSimpleGene() {
        String startCodon = "ATG";
        String stopCodon = "TAA";

        //invalid, no start codon
        String dna = "GTCGTATAA";
        System.out.println("The gene of the dna: " + dna + ", is: " + findSimpleGene(dna, startCodon, stopCodon));

        //invalid, no stop codon
        dna = "ATGAAGCGTTTG";
        System.out.println("The gene of the dna: " + dna + ", is: " + findSimpleGene(dna, startCodon, stopCodon));

        //invalid, no start or stop codon
        dna = "ATCAGCTGC";
        System.out.println("The gene of the dna: " + dna + ", is: " + findSimpleGene(dna, startCodon, stopCodon));

        //valid dna
        dna = "AATAATGTAGCCGTAA";
        System.out.println("The gene of the dna: " + dna + ", is: " + findSimpleGene(dna, startCodon, stopCodon));

        //invalid, not a multiple of 3
        dna = "ATGAATGCTGCTAA";
        System.out.println("The gene of the dna: " + dna + ", is: " + findSimpleGene(dna, startCodon, stopCodon));

        //valid lowerCase dna
        dna = "aataatgtagccgtaa";
        System.out.println("The gene of the dna: " + dna + ", is: " + findSimpleGene(dna, startCodon, stopCodon));

        //invalid lowerCase dna
        dna = "atgaatgctgctaa";
        System.out.println("The gene of the dna: " + dna + ", is: " + findSimpleGene(dna, startCodon, stopCodon));

    }

}


