package test.com.teddictionary.Model;

/**
 * Created by teddylin on 01/01/2018.
 */

public class QuizletAddTermResponse {

    /**
     * id : 8519731928
     * term : testtest
     * definition : test
     * image : null
     * rank : 3
     */

    private long id;
    private String term;
    private String definition;
    private Object image;
    private int rank;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
