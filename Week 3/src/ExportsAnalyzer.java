import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ExportsAnalyzer {
    public static void main(String[] args) {
        ExportsAnalyzer exportsAnalyzer = new ExportsAnalyzer();
        //exportsAnalyzer.testCountryInfo();
        //exportsAnalyzer.testListExportersTwoProducts();
        //exportsAnalyzer.testNumberOfExporters();
        exportsAnalyzer.testBigExporters();
    }


    //Write a method named countryInfo that has two parameters, parser is a CSVParser and country is a String.
    //This method returns a string of information about the country or returns “NOT FOUND”
    //if there is no information about the country. The format of the string returned is the country,
    //followed by “: “, followed by a list of the countries’ exports, followed by “: “, followed by the countries export value.
    public String countryInfo(CSVParser parser, String countryName) {
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(countryName)) {
                String exports = record.get("Exports");
                if (exports.isEmpty())
                    return "NOT FOUND";
                String exportsValue = record.get("Value (dollars)");
                return countryName + ": " + exports + ": " + exportsValue;
            }
        }

        return "";
    }

    public void testCountryInfo() {
        String countryName = "Germany";
        CSVParser parser = createCSVParser();
        System.out.println(countryInfo(parser, countryName));
    }


    //Write a void method named listExportersTwoProducts that has three parameters,
    //parser is a CSVParser, exportItem1 is a String and exportItem2 is a String.
    //This method prints the names of all the countries that have both exportItem1 and exportItem2 as export items.
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String countryName = record.get("Country");
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(countryName + " exports both " + exportItem1 + " and " + exportItem2);
            }
        }
    }

    public void testListExportersTwoProducts() {
        String exportItem1 = "gold";
        String exportItem2 = "diamonds";
        CSVParser parser = createCSVParser();
        listExportersTwoProducts(parser, exportItem1, exportItem2);
    }

    //Write a method named numberOfExporters, which has two parameters,
    //parser is a CSVParser, and exportItem is a String. This method returns the number of countries that export exportItem.
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem))
                count++;
        }
        return count;
    }

    public void testNumberOfExporters() {
        String exportItem = "gold";
        CSVParser parser = createCSVParser();
        System.out.println("There are " + numberOfExporters(parser, exportItem) + " countries that exports " + exportItem);

    }

    //Write a void method named bigExporters that has two parameters, parser is a CSVParser,
    //and amount is a String in the format of a dollar sign, followed by an integer number with a comma
    //separator every three digits from the right. An example of such a string might be “$400,000,000”.
    //This method prints the names of countries and their Value amount for all countries whose Value (dollars)
    //string is longer than the amount string. You do not need to parse either string value as an integer,
    //just compare the lengths of the strings.
    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            String countryName = record.get("Country");
            String exportsValue = record.get("Value (dollars)");
            if (exportsValue.length() > amount.length())
                System.out.println(countryName + " " + exportsValue);
        }
    }

    public void testBigExporters() {
        String amount = "$999,999,999";
        CSVParser parser = createCSVParser();
        bigExporters(parser, amount);
    }

    public CSVParser createCSVParser() {
        FileResource fileResource = new FileResource("datasets/exportdata.csv");
        return fileResource.getCSVParser();
    }


}
