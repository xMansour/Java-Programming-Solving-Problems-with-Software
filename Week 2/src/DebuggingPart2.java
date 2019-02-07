public class DebuggingPart2 {
    public static void main(String[] args) {
        DebuggingPart2 debuggingPart2 = new DebuggingPart2();
        debuggingPart2.test();
    }

    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            //to fix the stringOutOfBoundsException
            if (index >= input.length() - 3) {
                break;
            }
            String found = input.substring(index + 1, index + 4);
            System.out.println("\"" + found + "\"" + " at index: " + index);
            //index+3 to take the other occurrence of abc that is immediately after abc
            index = input.indexOf("abc", index + 3);
            System.out.println("index after updating: " + index);
        }
    }

    public void test() {
        //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
        findAbc("abcabcabcabca");
    }
}


