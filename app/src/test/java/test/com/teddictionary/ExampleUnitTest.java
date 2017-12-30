package test.com.teddictionary;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        StringBuffer result = new StringBuffer();
        try {
            Document dictDoc = Jsoup.connect(String.format(Constants.MERRIAM_WEBSTER_LEARNER_URL, "test")).ignoreContentType(true).get();
            Elements dtElements = dictDoc.getElementsByTag("dt");
            for (Element e : dtElements) {
                System.out.println(e.html().replaceAll("<it>.*\n.*\n.*","")
                        .replaceAll("<vi>","")
                        .replaceAll("</vi>","")
                .replaceAll(" <phrase>", ""));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}