import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class BabyNames {
    public static void main(String[] args) {
        BabyNames babyNames = new BabyNames();
        //babyNames.testTotalBirthNames();
        //babyNames.testGetRank();
        //babyNames.testGetName();
        //babyNames.testWhatIsNameInYear();
        //babyNames.testYearOfHighestRank();
        //babyNames.testGetAverageRank();
        babyNames.testGetTotalBirthsRankedHigher();

    }

    public void totalBirthName(FileResource fileResource) {
        int totalFemaleNames = 0;
        int totalMaleNames = 0;
        for (CSVRecord record : fileResource.getCSVParser(false)) {
            if (record.get(1).equals("F"))
                totalFemaleNames++;
            else
                totalMaleNames++;
        }
        System.out.println("There are " + totalFemaleNames + " female names and " + totalMaleNames + " male names");
    }

    public void testTotalBirthNames() {
        FileResource fileResource = new FileResource();
        totalBirthName(fileResource);
    }

    //Write the method named getRank that has three parameters: an integer named year,
    //a string named name, and a string named gender (F for female and M for male).
    //This method returns the rank of the name in the file for the given gender,
    //where rank 1 is the name with the largest number of births. If the name is not in the file, then -1 is returned.
    public int getRank(int year, String name, String gender) {
        int rank = 0;
        FileResource fileResource = new FileResource("datasets/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fileResource.getCSVParser(false);
        for (CSVRecord record : parser) {
            String currentName = record.get(0);
            String currentGender = record.get(1);
            if (gender.equals(currentGender) && currentName.equals(name)) {
                return rank;
            }
            rank++;

        }
        return -1;
    }

    public void testGetRank() {
        String name = "Mason";
        String gender = "M";
        int year = 2012;
        int rank = getRank(year, name, gender);
        if (rank == -1)
            System.out.println("This name isn't listed in the selected file");
        else
            System.out.println("The rank of the name \"" + name + "\" is RANK " + rank);
    }

    //Write the method named getName that has three parameters: an integer named year,
    //an integer named rank, and a string named gender (F for female and M for male).
    //This method returns the name of the person in the file at this rank, for the given gender,
    //where rank 1 is the name with the largest number of births. If the rank does not exist in the file,
    //then “NO NAME” is returned.
    public String getName(int year, int rank, String gender) {
        int currentRank = 0;
        FileResource fileResource = new FileResource("datasets/us_babynames_by_year/yob" + year + ".csv");
        CSVParser parser = fileResource.getCSVParser(false);
        for (CSVRecord record : parser) {
            if (rank == currentRank && record.get(1).equals(gender)) {
                return record.get(0);
            }
            currentRank++;

        }
        return "NO NAME";
    }

    public void testGetName() {
        int rank = 7;
        int year = 2012;
        String gender = "M";
        String name = getName(year, rank, gender);
        System.out.println("The name of RANK " + rank + " is " + name);
    }

    //Write the void method named whatIsNameInYear that has four parameters: a string name,
    //an integer named year representing the year that name was born, an integer named newYear and a string named gender
    //(F for female and M for male). This method determines what name would have been named if they were born in a different year,
    //based on the same popularity. That is, you should determine the rank of name in the year they were born,
    //and then print the name born in newYear that is at the same rank and same gender.
    public void whatIsNameInYear(String name, int bornYear, int newYear, String gender) {
        int bornRank = getRank(bornYear, name, gender);
        String newName = getName(newYear, bornRank, gender);
        System.out.println(name + " born in " + bornYear + " would be " + newName + " if she was born in " + newYear);
    }

    public void testWhatIsNameInYear() {
        String name = "Isabella";
        int bornYear = 2012;
        int newYear = 2014;
        String gender = "F";
        whatIsNameInYear(name, bornYear, newYear, gender);

    }

    //Write the method yearOfHighestRank that has two parameters: a string name,
    //and a string named gender (F for female and M for male). This method selects a range of files to process and returns an integer,
    //the year with the highest rank for the name and gender. If the name and gender are not in any of the selected files, it should return -1.
    public int yearOfHighestRank(String name, String gender) {
        long rank = 0;
        String fileName = "";
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()) {
            FileResource fileResource = new FileResource(file);
            CSVParser parser = fileResource.getCSVParser(false);
            for (CSVRecord record : parser) {
                String currentName = record.get(0);
                String currentGender = record.get(1);
                if (gender.equals(currentGender) && currentName.equals(name)) {
                    long currentRank = record.getRecordNumber();
                    if (rank == 0) {
                        rank = currentRank;
                        fileName = file.getName();
                    } else if (rank > currentRank) {
                        rank = currentRank;
                        fileName = file.getName();
                    }
                }
            }
        }

        if (fileName.isEmpty())
            return -1;
        else
            return Integer.parseInt(fileName.replaceAll("[^\\d]", ""));     //returns only the numbers in the fileName as integer
    }

    public void testYearOfHighestRank() {
        String name = "Mason";
        String gender = "M";
        int year = yearOfHighestRank(name, gender);
        System.out.println("Year of highest rank for " + name + " is " + year);
    }


    //Write the method getAverageRank that has two parameters: a string name, and a string named gender (F for female and M for male).
    //This method selects a range of files to process and returns a double representing the average rank of the name and gender over the selected files.
    //It should return -1.0 if the name is not ranked in any of the selected files.
    public double getAverageRank(String name, String gender) {
        int rankSum = 0;
        int count = 0;
        DirectoryResource directoryResource = new DirectoryResource();
        for (File file : directoryResource.selectedFiles()) {
            int rank = 0;
            FileResource fileResource = new FileResource(file);
            CSVParser parser = fileResource.getCSVParser(false);
            for (CSVRecord record : parser) {
                String currentName = record.get(0);
                String currentGender = record.get(1);
                if (gender.equals(currentGender) && currentName.equals(name)) {
                    break;
                }
                rank++;
                count++;

            }
            rankSum += rank;
        }

        if (rankSum == 0)
            return -1;
        else
            return (double) rankSum / (double) count;
    }

    public void testGetAverageRank() {
        String name = "Mason";
        String gender = "M";
        double averageRank = getAverageRank(name, gender);
    }

    //Write the method getTotalBirthsRankedHigher that has three parameters: an integer named year, a string named name,
    //and a string named gender (F for female and M for male). This method returns an integer,
    //the total number of births of those names with the same gender and same year who are ranked higher than name.
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBorn = 0;
        long rank = getRank(year, name, gender);
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            String currentGender = record.get(1);
            int currentTotalBorn = Integer.parseInt(record.get(2));
            long currentRank = record.getRecordNumber();
            if (gender.equals(currentGender) && rank > currentRank) {
                totalBorn += currentTotalBorn;
            }
        }
        return totalBorn;

    }


    public void testGetTotalBirthsRankedHigher() {
        String name = "Ethan";
        String gender = "M";
        int year = 2012;
        int totalBorn = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Total born " + totalBorn);
    }


}
