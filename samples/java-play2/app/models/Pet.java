package models;

import play.db.ebean.Model;

import java.util.ArrayList;
import java.util.List;

public class Pet extends Model {
    private Long id = 0L;
    //private Category category;
    private String name;
    private List<String> photoUrls = new ArrayList<String>();
    //private List<Tag> tags = new ArrayList<Tag>();
    private String status;

    public static Pet findById(Long id) {
        return new Pet();
    }
}
