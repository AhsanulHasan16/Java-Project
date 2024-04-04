import java.util.*;

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
            if (movie.getTitle().toLowerCase().contains(string.toLowerCase()) || movie.getCast().stream().map(String::toLowerCase).anyMatch(c -> c.contains(string.toLowerCase())) || movie.getCategory().toLowerCase().contains(string.toLowerCase()))
                movieList.add(movie);
        }

        Collections.sort(movieList, Comparator.comparing(Movie::getTitle));
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

    // Displaying User Details Alongside Their Favorites
    public void displayUserDetails(String userEmail) {
        System.out.println("Details Of " + userEmail + ":");
        List<Movie> userFavorites = favorites.get(userEmail);
        System.out.println(userEmail + "'s Favorites Are: ");
        for (Movie movie : userFavorites) {
            System.out.println(movie.getTitle());
        }
    }

    // Searching Only Favorites Movies
    public List<Movie> searchFavoritesMovies(String userEmail, String string) {
        List<Movie> movieList = new ArrayList<>();
        List<Movie> userFavorites = favorites.get(userEmail);

        for (Movie movie : userFavorites) {
            if (movie.getTitle().toLowerCase().contains(string.toLowerCase()) || movie.getCast().stream().map(String::toLowerCase).anyMatch(c -> c.contains(string.toLowerCase())) || movie.getCategory().toLowerCase().contains(string.toLowerCase()))
                movieList.add(movie);
        }

        Collections.sort(movieList, Comparator.comparing(Movie::getTitle));
        return movieList;
    }

}
