import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieAppTest {

    // Tests
    private MovieApp app;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void init() {
        app = new MovieApp();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void userRegistrationTest() {
        app.userRegistration("ahsanul2051@gmail.com");
        app.userRegistration("ahsanul2052@gmail.com");
        app.userRegistration("ahsanul2053@gmail.com");
        assertTrue(app.users.contains("ahsanul2051@gmail.com"));
        assertTrue(app.users.contains("ahsanul2052@gmail.com"));
        assertTrue(app.users.contains("ahsanul2053@gmail.com"));
        assertFalse(app.users.contains("ahsanul2054@gmail.com"));
    }

    @Test
    void searchMoviesTest() {
        Movie movie1 = new Movie("Movie1", "Action", Arrays.asList("Khan", "Maria"), "1-4-2020", 400000);
        Movie movie2 = new Movie("AMovieName2", "Horror", Arrays.asList("Zakir", "Helen"), "1-4-2020", 400000);
        Movie movie3 = new Movie("Film3", "Comedy", Arrays.asList("Jihad", "Khan"), "1-4-2020", 400000);
        Movie movie4 = new Movie("XFilm4", "Horror", Arrays.asList("Jahid", "Khan"), "1-4-2020", 400000);
        Movie movie5 = new Movie("GFilm5", "Horror", Arrays.asList("Joker", "Kiran"), "1-4-2020", 400000);

        app.movies.add(movie1);
        app.movies.add(movie2);
        app.movies.add(movie3);
        app.movies.add(movie4);
        app.movies.add(movie5);

        List<Movie> movieList = app.searchMovies("HORROR");
        assertEquals(3, movieList.size());
        assertEquals("AMovieName2", movieList.get(0).getTitle());
        assertEquals("GFilm5", movieList.get(1).getTitle());
        assertEquals("XFilm4", movieList.get(2).getTitle());
    }

    @Test
    void movieDetailsTest() {
        Movie movie1 = new Movie("Movie1", "Action", Arrays.asList("X", "Y"), "1-4-2020", 400000);
        Movie movie2 = new Movie("MovieName2", "Horror", Arrays.asList("Z", "H"), "1-4-2020", 400000);

        app.movieDetails(movie2);
        String expectedOutput = "Title: MovieName2\r\nCast: Z,H\r\nCategory: Horror\r\nRelease Date: 1-4-2020\r\nBudget: $400000.0\r\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void addFavoritesTest() {
        Movie movie1 = new Movie("Movie1", "Action", Arrays.asList("X", "Y"), "1-4-2020", 400000);
        Movie movie2 = new Movie("MovieName2", "Horror", Arrays.asList("Z", "H"), "1-4-2020", 400000);
        Movie movie3 = new Movie("Film3", "Comedy", Arrays.asList("J", "K"), "1-4-2020", 400000);
        Movie movie4 = new Movie("Film4", "Horror", Arrays.asList("Jahid", "Khan"), "1-4-2020", 400000);
        Movie movie5 = new Movie("Film5", "Horror", Arrays.asList("Joker", "Kiran"), "1-4-2020", 400000);

        app.addFavorites("ahsanul2051@gmail.com", movie1);
        app.addFavorites("ahsanul2051@gmail.com", movie2);
        app.addFavorites("ahsanul2051@gmail.com", movie4);

        List<Movie> favs = app.favorites.get("ahsanul2051@gmail.com");
        assertTrue(favs.containsAll(Arrays.asList(movie1, movie2, movie4)));
        assertFalse(favs.contains(movie3));
    }

    @Test
    void removeFavoritesTest() {
        Movie movie1 = new Movie("Movie1", "Action", Arrays.asList("X", "Y"), "1-4-2020", 400000);
        Movie movie2 = new Movie("MovieName2", "Horror", Arrays.asList("Z", "H"), "1-4-2020", 400000);
        Movie movie3 = new Movie("Film3", "Comedy", Arrays.asList("J", "K"), "1-4-2020", 400000);
        Movie movie4 = new Movie("Film4", "Horror", Arrays.asList("Jahid", "Khan"), "1-4-2020", 400000);
        Movie movie5 = new Movie("Film5", "Horror", Arrays.asList("Joker", "Kiran"), "1-4-2020", 400000);

        app.addFavorites("ahsanul2051@gmail.com", movie1);
        app.addFavorites("ahsanul2051@gmail.com", movie2);
        app.addFavorites("ahsanul2051@gmail.com", movie4);
        app.addFavorites("ahsanul2051@gmail.com", movie5);

        app.removeFavorites("ahsanul2051@gmail.com", movie1);
        app.removeFavorites("ahsanul2051@gmail.com", movie5);

        assertTrue(app.favorites.get("ahsanul2051@gmail.com").contains(movie4));
        assertFalse(app.favorites.get("ahsanul2051@gmail.com").contains(movie5));

    }

    @Test
    void displayUserDetailsTest() {
        app.userRegistration("ahsanul2051@gmail.com");

        Movie movie1 = new Movie("Movie1", "Action", Arrays.asList("X", "Y"), "1-4-2020", 400000);
        Movie movie2 = new Movie("MovieName2", "Horror", Arrays.asList("Z", "H"), "1-4-2020", 400000);
        Movie movie3 = new Movie("Film3", "Comedy", Arrays.asList("J", "K"), "1-4-2020", 400000);

        app.addFavorites("ahsanul2051@gmail.com", movie1);
        app.addFavorites("ahsanul2051@gmail.com", movie2);

        app.displayUserDetails("ahsanul2051@gmail.com");
        String expectedOutput = "Details Of ahsanul2051@gmail.com:\r\nahsanul2051@gmail.com's Favorites Are: \r\nMovie1\r\nMovieName2\r\n";
        assertEquals(expectedOutput, outputStream.toString());

    }

    @Test
    void searchFavoritesMoviesTest() {
        Movie movie1 = new Movie("AMovie1", "Horror", Arrays.asList("Xavier", "Kabir"), "1-4-2020", 400000);
        Movie movie2 = new Movie("MovieName2", "Horror", Arrays.asList("Zakir", "Helen"), "1-4-2020", 400000);
        Movie movie3 = new Movie("Film3", "Comedy", Arrays.asList("Jihad", "Khan"), "1-4-2020", 400000);
        Movie movie4 = new Movie("Film4", "Horror", Arrays.asList("Jahid", "Khan"), "1-4-2020", 400000);
        Movie movie5 = new Movie("Film5", "Comedy", Arrays.asList("Joker", "Kiran"), "1-4-2020", 400000);

        app.addFavorites("ahsanul2051@gmail.com", movie1);
        app.addFavorites("ahsanul2051@gmail.com", movie2);
        app.addFavorites("ahsanul2051@gmail.com", movie3);
        app.addFavorites("ahsanul2051@gmail.com", movie4);
        app.addFavorites("ahsanul2051@gmail.com", movie5);

        List<Movie> movieList = app.searchFavoritesMovies("ahsanul2051@gmail.com", "horror");
        assertEquals(3, movieList.size());
        assertEquals("AMovie1", movieList.get(0).getTitle());
        assertEquals("Film4", movieList.get(1).getTitle());
        assertEquals("MovieName2", movieList.get(2).getTitle());
    }

}
