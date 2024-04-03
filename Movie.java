import java.util.List;

public class Movie {

    private String title;
    private String category;
    private List<String> cast;

    public Movie(String title, String category, List<String> cast) {
        this.title = title;
        this.category = category;
        this.cast = cast;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public List<String> getCast() {
        return cast;
    }

}
