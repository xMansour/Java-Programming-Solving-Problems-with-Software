import edu.duke.StorageResource;

public class StorageResourcePart1 {
    public static void main(String[] args) {
        StorageResourcePart1 storageResourcePart1 = new StorageResourcePart1();
        storageResourcePart1.testGetAllGenes();
    }


    public int findStopCodon(String dna, int startCodonIndex, String stopCodon) {
        int stopCodonIndex = dna.indexOf(stopCodon, startCodonIndex + 3);
        return stopCodonIndex;
    }

    public String findGene(String dna) {

        int startCodonIndex = dna.indexOf("ATG");
        if (startCodonIndex == -1) {
            return "";
        }

        int taaCodonIndex = findStopCodon(dna, startCodonIndex, "TAA");
        int tagCodonIndex = findStopCodon(dna, startCodonIndex, "TAG");
        int tgaCodonIndex = findStopCodon(dna, startCodonIndex, "TGA");

        int minIndex = -1;
        if (taaCodonIndex == -1 || (tagCodonIndex != -1 && tagCodonIndex < taaCodonIndex) && Math.abs(startCodonIndex - tagCodonIndex) % 3 == 0) {
            minIndex = tagCodonIndex;
        } else if (startCodonIndex - taaCodonIndex % 3 == 0) {
            minIndex = taaCodonIndex;
        }

        if (minIndex == -1 || (tgaCodonIndex != -1 && tgaCodonIndex < minIndex) && Math.abs(startCodonIndex - tgaCodonIndex) % 3 == 0) {
            minIndex = tgaCodonIndex;
        }

        if (minIndex == -1) {
            return "";
        }

        if (Math.abs(startCodonIndex - minIndex) % 3 == 0)
            return dna.substring(startCodonIndex, minIndex + 3);

        return "";
    }


    public void printAllGenes(String dna) {
        while (!dna.isEmpty()) {
            String gene = findGene(dna);
            int geneIndex = dna.indexOf(gene);
            if (gene.isEmpty()) {
                System.out.println("There are no more genes in the dna");
                break;
            }
            System.out.println("The gene \"" + gene + "\"" + " is in the dna");
            dna = dna.substring(geneIndex + gene.length());
        }
    }

    public StorageResource getAllGenes(String dna) {
        StorageResource storageResource = new StorageResource();

        while (!dna.isEmpty()) {
            String gene = findGene(dna);
            int geneIndex = dna.indexOf(gene);
            if (gene.isEmpty()) {
                break;
            }
            storageResource.add(gene);
            dna = dna.substring(geneIndex + gene.length());
        }

        return storageResource;
    }


    public void testGetAllGenes() {
        StorageResource storageResource = getAllGenes("ATGTAAGATGCCCTAGT");
        for (String gene : storageResource.data()) {
            System.out.println("Gene: " + gene + " is in the dna");
        }
    }
}
