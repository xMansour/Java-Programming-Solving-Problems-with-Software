public class StorageResourcePart2 {
    public static void main(String[] args) {
        StorageResourcePart2 storageResourcePart2 = new StorageResourcePart2();
        //storageResourcePart2.testCGRaito();
        storageResourcePart2.testCountCTG();

    }

    //gets the ratio of c and g in the dna
    public double cgRatio(String dna) {
        dna = dna.toUpperCase();        //for lowercase comparability
        int count = 0;
        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G')
                count++;
        }
        return (float) count / dna.length();
    }

    public void testCGRaito() {
        String dna = "ATGCCATAG";
        System.out.println("The dna \"" + dna + "\" contains \"C\" and \"G\" by the following ratio: " + cgRatio(dna));
    }

    //counts the number the codon CTG appears in the dna
    public int countCTG(String dna) {
        String codon = "CTG";
        int count = 0;
        while (!dna.isEmpty()) {
            int ctgIndex = dna.indexOf(codon);
            if (ctgIndex == -1)
                return 0;
            count++;
            dna = dna.substring(ctgIndex + codon.length());
        }

        return count;
    }

    public void testCountCTG() {
        String dna = "ATGCCATAGCTGCGATAGCTGCTG";
        System.out.println("The dna \"" + dna + "\" contains " + countCTG(dna) + " \"CTG\" codons");
    }
}
