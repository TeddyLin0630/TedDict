package test.com.teddictionary.net;

import android.os.AsyncTask;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import test.com.teddictionary.Constants;
import test.com.teddictionary.Model.Definition;
import test.com.teddictionary.event.MessageEvent;

/**
 * Created by teddylin on 05/12/2017.
 */

public class SearchAsycTask extends AsyncTask<String, Void, Boolean> {
    @Override
    protected Boolean doInBackground(String... strings) {
        ArrayList<Definition> definitions = new ArrayList<>();
        try {
            String xml = String.format(Constants.MERRIAM_WEBSTER_LEARNER_URL, strings[0]);
            Document doc = Jsoup.connect(xml).ignoreContentType(true).ignoreContentType(true).get();

            for (Element e : doc.body().select("entry[id]")) {

                for (Element e1 : e.select("dt")) {

                    if (e1.textNodes().isEmpty()) {
                        continue;
                    }

                    Definition definition = new Definition();

                    if (e1.textNodes().get(0).toString().length() < 3) {
                        continue;
                    }
                    definition.setDefinicion(e1.textNodes().get(0).toString());

                    StringBuilder examples = new StringBuilder();
                    for (Element e2 : e1.select("vi")) {
                        examples.append("-" + e2.text() + "\n");
                    }
                    definition.setExample(examples.toString());
                    definitions.add(definition);
                }
            }

            if (definitions.size() == 0) {
                Definition definition = new Definition();
                definition.setDefinicion("Not found.");
                definitions.add(definition);
            }
        } catch (IOException e) {
            Log.d("SearchAsyctask", e.getMessage());
        }
        EventBus.getDefault().post(new MessageEvent.GetDefitionResult(definitions));
        return true;
    }
}
