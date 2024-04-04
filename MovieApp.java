import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieApp {

    // Main Application Class
    public List<Movie> movies;
    public List<String> users;
    public Map<String, List<Movie>> favorites;

    public MovieApp() {
        this.movies = new ArrayList<>();
        this.users = new ArrayList<>();
        this.favorites = new HashMap<>();
    }

    // User Registration
    public void userRegistration(String email) {
        users.add(email);
        favorites.put(email, new ArrayList<>());
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

    // Adding Movies To Favorites
    public void addFavorites(String userEmail, Movie movie) {
        List<Movie> userFavorites = favorites.get(userEmail);
        if (userFavorites == null) userFavorites = new ArrayList<>();
        userFavorites.add(movie);
        favorites.put(userEmail, userFavorites);
    }

    // Removing Movies From Favorites
    public void removeFavorites(String userEmail, Movie movie) {
        List<Movie> userFavorites = favorites.get(userEmail);
        if (userFavorites == null) System.out.println("This user has no favorite movies");
        userFavorites.remove(movie);
        favorites.put(userEmail, userFavorites);
    }


}
