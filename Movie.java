import java.util.List;

public class Movie {

    private String title;
    private String category;
    private List<String> cast;
    private String releaseDate;
    private double budget;

    public Movie(String title, String category, List<String> cast, String releaseDate, double budget) {
        this.title = title;
        this.category = category;
        this.cast = cast;
        this.releaseDate = releaseDate;
        this.budget = budget;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getBudget() {
        return budget;
    }
}
