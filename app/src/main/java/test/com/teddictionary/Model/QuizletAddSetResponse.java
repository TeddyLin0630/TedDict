package test.com.teddictionary.Model;

import java.util.List;

/**
 * Created by teddylin on 13/12/2017.
 */

public class QuizletAddSetResponse {

    /**
     * id : 253519331
     * url : /253519331/test-flash-cards/
     * title : test
     * created_by : yuhsiung_lin
     * term_count : 2
     * created_date : 1513180758
     * modified_date : 1513180758
     * published_date : 1513180758
     * has_images : false
     * subjects : []
     * visibility : public
     * editable : only_me
     * has_access : true
     * can_edit : true
     * description :
     * lang_terms : en
     * lang_definitions : en
     * password_use : 0
     * password_edit : 0
     * access_type : 2
     * creator_id : 18107832
     * creator : {"username":"yuhsiung_lin","account_type":"free","profile_image":"/a/i/animals/49.cU5V.jpg","id":18107832}
     * class_ids : []
     * terms : [{"id":8394661519,"term":"test","definition":"test","image":null,"rank":0},{"id":8394661520,"term":"test","definition":"test","image":null,"rank":1}]
     * set_id : 253519331
     */

    private int id;
    private String url;
    private String title;
    private String created_by;
    private int term_count;
    private int created_date;
    private int modified_date;
    private int published_date;
    private boolean has_images;
    private String visibility;
    private String editable;
    private boolean has_access;
    private boolean can_edit;
    private String description;
    private String lang_terms;
    private String lang_definitions;
    private int password_use;
    private int password_edit;
    private int access_type;
    private int creator_id;
    private CreatorBean creator;
    private int set_id;
    private List<?> subjects;
    private List<?> class_ids;
    private List<TermsBean> terms;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public int getTerm_count() {
        return term_count;
    }

    public void setTerm_count(int term_count) {
        this.term_count = term_count;
    }

    public int getCreated_date() {
        return created_date;
    }

    public void setCreated_date(int created_date) {
        this.created_date = created_date;
    }

    public int getModified_date() {
        return modified_date;
    }

    public void setModified_date(int modified_date) {
        this.modified_date = modified_date;
    }

    public int getPublished_date() {
        return published_date;
    }

    public void setPublished_date(int published_date) {
        this.published_date = published_date;
    }

    public boolean isHas_images() {
        return has_images;
    }

    public void setHas_images(boolean has_images) {
        this.has_images = has_images;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public boolean isHas_access() {
        return has_access;
    }

    public void setHas_access(boolean has_access) {
        this.has_access = has_access;
    }

    public boolean isCan_edit() {
        return can_edit;
    }

    public void setCan_edit(boolean can_edit) {
        this.can_edit = can_edit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getPassword_use() {
        return password_use;
    }

    public void setPassword_use(int password_use) {
        this.password_use = password_use;
    }

    public int getPassword_edit() {
        return password_edit;
    }

    public void setPassword_edit(int password_edit) {
        this.password_edit = password_edit;
    }

    public int getAccess_type() {
        return access_type;
    }

    public void setAccess_type(int access_type) {
        this.access_type = access_type;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public CreatorBean getCreator() {
        return creator;
    }

    public void setCreator(CreatorBean creator) {
        this.creator = creator;
    }

    public int getSet_id() {
        return set_id;
    }

    public void setSet_id(int set_id) {
        this.set_id = set_id;
    }

    public List<?> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<?> subjects) {
        this.subjects = subjects;
    }

    public List<?> getClass_ids() {
        return class_ids;
    }

    public void setClass_ids(List<?> class_ids) {
        this.class_ids = class_ids;
    }

    public List<TermsBean> getTerms() {
        return terms;
    }

    public void setTerms(List<TermsBean> terms) {
        this.terms = terms;
    }

    public static class CreatorBean {
        /**
         * username : yuhsiung_lin
         * account_type : free
         * profile_image : /a/i/animals/49.cU5V.jpg
         * id : 18107832
         */

        private String username;
        private String account_type;
        private String profile_image;
        private int id;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAccount_type() {
            return account_type;
        }

        public void setAccount_type(String account_type) {
            this.account_type = account_type;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class TermsBean {
        /**
         * id : 8394661519
         * term : test
         * definition : test
         * image : null
         * rank : 0
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
}
