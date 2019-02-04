public class Part3 {

    public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.testing();
    }

    public boolean twoOccurrences(String stringa, String stringb) {
        int firstIndex = stringb.indexOf(stringa);
        if (firstIndex == -1)
            return false;
        int secondIndex = stringb.indexOf(stringa, firstIndex + stringa.length());
        if (secondIndex == -1)
            return false;

        else
            return true;
    }

    public void testing() {
        String stringa = "by";
        String stringb = "A story by Abby Long";

        if (twoOccurrences(stringa, stringb))
            System.out.println("\"" + stringa + "\"" + " appears twice or more in " + "\"" + stringb + "\"");
        else
            System.out.println("\"" + stringa + "\"" + " appears less than two times in " + "\"" + stringb + "\"");

        stringa = "a";
        stringb = "banana";
        if (twoOccurrences(stringa, stringb))
            System.out.println("\"" + stringa + "\"" + " appears twice or more in " + "\"" + stringb + "\"");
        else
            System.out.println("\"" + stringa + "\"" + " appears less than two times in " + "\"" + stringb + "\"");

        stringa = "atg";
        stringb = "ctgtatgta";
        if (twoOccurrences(stringa, stringb))
            System.out.println("\"" + stringa + "\"" + " appears twice or more in " + "\"" + stringb + "\"");
        else
            System.out.println("\"" + stringa + "\"" + " appears less than two times in " + "\"" + stringb + "\"");

        stringa = "an";
        stringb = "banana";
        System.out.println("The part of string: " + stringb + " after " + "\"" + stringa + "\"" + " is: " + lastPart(stringa, stringb));

        stringa = "zoo";
        stringb = "forest";
        System.out.println("The part of string: " + stringb + " after " + "\"" + stringa + "\"" + " is: " + lastPart(stringa, stringb));

    }

    public String lastPart(String stringa, String stringb) {
        int stringaIndex = stringb.indexOf(stringa);
        if (stringaIndex == -1)
            return stringb;
        else
            return stringb.substring(stringaIndex + stringa.length());
    }
}
