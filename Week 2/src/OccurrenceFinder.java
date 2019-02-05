public class OccurrenceFinder {
    public static void main(String[] args) {
        OccurrenceFinder occurrenceFinder = new OccurrenceFinder();
        occurrenceFinder.testHowMany();

    }


    //Write the method named howMany that has two String parameters named stringa and stringb.
    //This method returns an integer indicating how many times stringa appears in stringb,
    //where each occurrence of stringa must not overlap with another occurrence of it. For example,
    //the call howMany(“GAA”, “ATGAACGAATTGAATC”) returns 3 as GAA occurs 3 times. The call howMany(“AA”, “ATAAAA”) returns 2.
    //Note that the AA’s found cannot overlap.
    public int howMany(String stringa, String stringb) {
        int count = 0;
        String temp = stringb;
        int index;
        while (!temp.isEmpty()) {
            index = temp.indexOf(stringa);
            if (index == -1)
                return count;
            count++;
            temp = temp.substring(index + stringa.length());
        }

        return count;
    }

    //Write the void method testHowMany has no parameters.
    //Add code in here to call howMany with several examples and print the results.
    //Think carefully about what types of examples would be good to test to make sure your method works correctly.
    public void testHowMany() {
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        System.out.println("\"" + stringa + "\" appears " + howMany(stringa, stringb) + " times in the string \"" + stringb + "\"");

        stringa = "AA";
        stringb = "ATAAAA";
        System.out.println("\"" + stringa + "\" appears " + howMany(stringa, stringb) + " times in the string \"" + stringb + "\"");

    }
}
