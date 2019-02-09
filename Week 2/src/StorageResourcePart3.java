import edu.duke.FileResource;
import edu.duke.StorageResource;

public class StorageResourcePart3 {
    public static void main(String[] args) {
        StorageResourcePart3 storageResourcePart3 = new StorageResourcePart3();
        //storageResourcePart3.testProcessGenes();
        storageResourcePart3.testProcessGenesWithFile();

    }

    //print all the Strings in sr that are longer than 9 characters         x
    //print the number of Strings in sr that are longer than 9 characters   x
    //print the Strings in sr whose C-G-ratio is higher than 0.35           x
    //print the number of strings in sr whose C-G-ratio is higher than 0.35 x
    //print the length of the longest gene in sr
    public void processGenes(StorageResource storageResource) {
        int count = 0;
        int cgCount = 0;
        String longestGene = "";
        for (String word : storageResource.data()) {
            if (cgRatio(word) > 0.35) {
                System.out.println(word + " has a C-G ratio more than 0.35");
                cgCount++;
            }
            if (word.length() > 9) {
                System.out.println(word + " has characters more than 9");
                count++;
            }

            if (word.length() > longestGene.length())
                longestGene = word;
        }

        System.out.println("The number of strings that are longer than 9 characters is: " + count);
        System.out.println("The number of strings whose C-G ratio is higher than 0.35 is: " + cgCount);
        System.out.println("The longest gene is: " + longestGene);

    }

    public void testProcessGenes() {
        StorageResource storageResource = new StorageResource();
        storageResource.add("ATGCCATAG");
        storageResource.add("ATGCCATAGCTGCGATAGCTGCTG");
        storageResource.add("ATGTAA");
        storageResource.add("ATGCTGCTGCTGATGTAACTGATGCCATAGCTGCGATAGCTGCTG");
        processGenes(storageResource);
    }

    public void testProcessGenesWithFile() {
        StorageResource storageResource = new StorageResource();
        FileResource fileResource = new FileResource("brca1line.fa");
        String dna = fileResource.asString();
        storageResource.add(dna);
        GenesCounter genesCounter = new GenesCounter();
        genesCounter.countGenes(dna);
        processGenes(storageResource);
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
}