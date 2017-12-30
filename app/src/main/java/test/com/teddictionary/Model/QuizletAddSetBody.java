package test.com.teddictionary.Model;

/**
 * Created by teddylin on 13/12/2017.
 */

public class QuizletAddSetBody {
    String title;
    String[] terms;
    String[] definitions;
    String lang_terms;
    String lang_definitions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getTerms() {
        return terms;
    }

    public void setTerms(String[] terms) {
        this.terms = terms;
    }

    public String[] getDefinitions() {
        return definitions;
    }

    public void setDefinitions(String[] definitions) {
        this.definitions = definitions;
    }

    public String getLang_terms() {
        return lang_terms;
    }

    public void setLang_terms(String lang_terms) {
        this.lang_terms = lang_terms;
    }

    public String getLang_definitions() {
        return lang_definitions;
    }

    public void setLang_definitions(String lang_definitions) {
        this.lang_definitions = lang_definitions;
    }
}
