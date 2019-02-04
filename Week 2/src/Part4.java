import edu.duke.URLResource;

public class Part4 {
    public static void main(String[] args) {
        Part4 part4 = new Part4();
        part4.findYouTubeLinks();
    }

    public String findYouTubeLinks() {
        URLResource urlResource = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String word : urlResource.words()) {
            if (word.toLowerCase().contains("youtube.com")) {
                int firstQuoteIndex = word.indexOf("\"");
                int secondQuoteIndex = word.indexOf("\"", firstQuoteIndex + 1);
                String url = word.substring(firstQuoteIndex + 1, secondQuoteIndex);
                System.out.println(url + "\n");
            }
        }

        return "";
    }


}
