import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class ColdestDay {
    public static void main(String[] args) {
        ColdestDay coldestDay = new ColdestDay();
        //coldestDay.testColdestHourInFile();
        //coldestDay.testFileWithColdestTemperature();
        //coldestDay.testLowestHumidityInFile();
        //coldestDay.testLowestHumidityInManyFiles();
        coldestDay.testAverageTemperatureInFile();
        //coldestDay.testAverageTemperatureWithHighHumidityInFile();

    }

    //Write a method named coldestHourInFile that has one parameter,
    //a CSVParser named parser. This method returns the CSVRecord with the coldest temperature
    //in the file and thus all the information about the coldest temperature,
    //such as the hour of the coldest temperature. You should also write a void
    //method named testColdestHourInFile() to test this method and print out information
    //about that coldest temperature, such as the time of its occurrence.
    //NOTE: Sometimes there was not a valid reading at a specific hour,
    //so the temperature field says -9999. You should ignore these bogus temperature
    //values when calculating the lowest temperature.
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRecord : parser) {
            if (Double.parseDouble(currentRecord.get("TemperatureF")) == -9999)
                continue;
            if (coldestSoFar == null) {
                coldestSoFar = currentRecord;
            } else {
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
                if (coldestTemp > currentTemp) {
                    coldestSoFar = currentRecord;
                }
            }

        }
        return coldestSoFar;
    }

    public void testColdestHourInFile() {
        FileResource fileResource = new FileResource();
        CSVParser parser = fileResource.getCSVParser();
        CSVRecord coldestSoFar = coldestHourInFile(parser);
        String coldestTemp = coldestSoFar.get("TemperatureF");
        String time = coldestSoFar.get("TimeEDT");
        String humidity = coldestSoFar.get("Humidity");
        System.out.println("Coldest temp " + coldestTemp + " at " + time + " and the humidity was " + humidity);
    }


    //Write the method fileWithColdestTemperature that has no parameters.
    //This method should return a string that is the name of the file from selected files
    //that has the coldest temperature. You should also write a void method named
    //testFileWithColdestTemperature() to test this method. Note that after determining the
    //filename, you could call the method coldestHourInFile to determine the coldest temperature on that day.
    public String fileWithColdestTemperature() {
        String coldestFileName = "";
        CSVRecord coldestSoFar = null;
        DirectoryResource resource = new DirectoryResource();
        for (File file : resource.selectedFiles()) {
            FileResource fileResource = new FileResource(file);
            CSVParser parser = fileResource.getCSVParser();
            CSVRecord current = coldestHourInFile(parser);
            if (coldestSoFar == null) {
                coldestSoFar = current;
                coldestFileName = file.getName();
            } else {
                double currentTemp = Double.parseDouble(current.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (coldestTemp > currentTemp) {
                    coldestSoFar = current;
                    coldestFileName = file.getName();
                }
            }
        }
        return coldestFileName;
    }

    public void testFileWithColdestTemperature() {
        String coldestFileName = fileWithColdestTemperature();
        FileResource fileResource = new FileResource("datasets/2014/" + coldestFileName);
        CSVParser parser = fileResource.getCSVParser();
        CSVRecord coldestRecord = coldestHourInFile(parser);
        String coldestTemp = coldestRecord.get("TemperatureF");
        System.out.println("File: " + coldestFileName + " has the coldest temperature and it is: " + coldestTemp);
    }


    //Write a method named lowestHumidityInFile that has one parameter,
    //a CSVParser named parser. This method returns the CSVRecord that has the lowest humidity.
    //If there is a tie, then return the first such record that was found.
    //Note that sometimes there is not a number in the Humidity column but instead
    //there is the string “N/A”. This only happens very rarely.
    //You should check to make sure the value you get is not “N/A” before converting it to a number.
    //Also note that the header for the time is either TimeEST or TimeEDT,
    //depending on the time of year. You will instead use the DateUTC field
    //at the right end of the data file to get both the date and time of a temperature reading
    //You should also write a void method named testLowestHumidityInFile() to test this method that starts with these lines:
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityRecord = null;
        for (CSVRecord currentRecord : parser) {
            if (currentRecord.get("Humidity").equals("N/A"))
                continue;
            if (lowestHumidityRecord == null) {
                lowestHumidityRecord = currentRecord;
            } else {
                double lowestHumidity = Double.parseDouble(lowestHumidityRecord.get("Humidity"));
                double currentHumidity = Double.parseDouble(currentRecord.get("Humidity"));
                if (lowestHumidity > currentHumidity) {
                    lowestHumidityRecord = currentRecord;
                }
            }

        }
        return lowestHumidityRecord;
    }

    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csvRecord = lowestHumidityInFile(parser);
        String lowestHumidity = csvRecord.get("Humidity");
        String date = csvRecord.get("DateUTC");
        System.out.println("Lowest humidity is: " + lowestHumidity + " at " + date);

    }

    //Write the method lowestHumidityInManyFiles that has no parameters.
    //This method returns a CSVRecord that has the lowest humidity over all the files.
    //If there is a tie, then return the first such record that was found.
    //You should also write a void method named testLowestHumidityInManyFiles()
    //to test this method and to print the lowest humidity AND the time the lowest humidity occurred.
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        DirectoryResource resource = new DirectoryResource();
        for (File file : resource.selectedFiles()) {
            FileResource fileResource = new FileResource(file);
            CSVParser parser = fileResource.getCSVParser();
            CSVRecord current = lowestHumidityInFile(parser);
            if (lowestSoFar == null) {
                lowestSoFar = current;
            } else {
                double currentHumidity = Double.parseDouble(current.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));
                if (lowestHumidity > currentHumidity) {
                    lowestSoFar = current;
                }
            }
        }
        return lowestSoFar;
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidityRecord = lowestHumidityInManyFiles();
        String lowestHumidity = lowestHumidityRecord.get("Humidity");
        String date = lowestHumidityRecord.get("DateUTC");
        System.out.println("lowest humidity: " + lowestHumidity + " at " + date);
    }

    //Write the method averageTemperatureInFile that has one parameter,
    //a CSVParser named parser. This method returns a double that represents the average temperature in the file.
    //You should also write a void method named testAverageTemperatureInFile() to test this method.
    public double averageTemperatureInFile(CSVParser parser) {
        double tempSum = 0.0;
        int count = 0;
        for (CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            tempSum += temp;
            count++;
        }

        return tempSum / count;
    }

    public void testAverageTemperatureInFile() {
        FileResource fileResource = new FileResource();
        CSVParser parser = fileResource.getCSVParser();
        System.out.println("Average temperature is: " + averageTemperatureInFile(parser));
    }

    //Write the method averageTemperatureWithHighHumidityInFile that has two parameters,
    //a CSVParser named parser and an integer named value.
    //This method returns a double that represents the average temperature
    //of only those temperatures when the humidity was greater than or equal to value.
    //You should also write a void method named testAverageTemperatureWithHighHumidityInFile() to test this method.

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double tempSum = 0.0;
        int count = 0;
        for (CSVRecord record : parser) {
            int currentHumidity = Integer.parseInt(record.get("Humidity"));

            if (currentHumidity >= value) {
                double temp = Double.parseDouble(record.get("TemperatureF"));
                tempSum += temp;
                count++;
            }
        }
        return tempSum / count;
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fileResource = new FileResource();
        CSVParser parser = fileResource.getCSVParser();
        int lowestHumidity = 80;
        double averageTemp = averageTemperatureWithHighHumidityInFile(parser, lowestHumidity);
        System.out.println("Average temperature with humidity equal or greater to 80 is: " + averageTemp);
    }


}
