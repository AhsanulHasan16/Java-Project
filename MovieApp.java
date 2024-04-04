import java.util.ArrayList;
import java.util.List;

public class MovieApp {

    // Main Application Class
    public List<Movie> movies;
    public List<String> users;

    public MovieApp() {
        this.movies = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    // User Registration
    public void userRegistration(String email) {
        users.add(email);
    }

    // Searching Movies
    public List<Movie> searchMovies(String string) {
        List<Movie> movieList = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getTitle().contains(string) || movie.getCast().contains(string) || movie.getCategory().contains(string))
                movieList.add(movie);
        }
        
        return movieList;
    }

    // Showing Movie Details
    public void movieDetails(Movie movie) {
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Cast: " + String.join(",", movie.getCast()));
        System.out.println("Category: " + movie.getCategory());
        System.out.println("Release Date: " + movie.getReleaseDate());
        System.out.println("Budget: $" + movie.getBudget());
    }


}
