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
        assertTrue(app.users.contains("ahsanul2051@gmail.com"));
    }

    @Test
    void searchMoviesTest() {
        Movie movie1 = new Movie("Movie1", "Action", Arrays.asList("X", "Y"), "1-4-2020", 400000);
        Movie movie2 = new Movie("MovieName2", "Horror", Arrays.asList("Z", "H"), "1-4-2020", 400000);
        Movie movie3 = new Movie("Film3", "Comedy", Arrays.asList("J", "K"), "1-4-2020", 400000);

        app.movies.add(movie1);
        app.movies.add(movie2);
        app.movies.add(movie3);

        List<Movie> movieList = app.searchMovies("Movie");
        assertEquals(2, movieList.size());
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

        app.addFavorites("ahsanul2051@gmail.com", movie1);
        app.addFavorites("ahsanul2051@gmail.com", movie2);

//        assertTrue(app.favorites.get("ahsanul2051@gmail.com").contains(movie1));
//        assertTrue(app.favorites.get("ahsanul2051@gmail.com").contains(movie2));

        List<Movie> favs = app.favorites.get("ahsanul2051@gmail.com");
        assertTrue(favs.containsAll(Arrays.asList(movie1, movie2)));
    }

    @Test
    void removeFavoritesTest() {
        Movie movie1 = new Movie("Movie1", "Action", Arrays.asList("X", "Y"), "1-4-2020", 400000);
        Movie movie2 = new Movie("MovieName2", "Horror", Arrays.asList("Z", "H"), "1-4-2020", 400000);
        Movie movie3 = new Movie("Film3", "Comedy", Arrays.asList("J", "K"), "1-4-2020", 400000);

        app.addFavorites("ahsanul2051@gmail.com", movie1);
        app.addFavorites("ahsanul2051@gmail.com", movie2);

        app.removeFavorites("ahsanul2051@gmail.com", movie1);
        assertTrue(app.favorites.get("ahsanul2051@gmail.com").contains(movie2));
        assertFalse(app.favorites.get("ahsanul2051@gmail.com").contains(movie1));

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
        Movie movie1 = new Movie("Movie1", "Action", Arrays.asList("X", "Y"), "1-4-2020", 400000);
        Movie movie2 = new Movie("MovieName2", "Horror", Arrays.asList("Z", "H"), "1-4-2020", 400000);
        Movie movie3 = new Movie("Film3", "Comedy", Arrays.asList("J", "K"), "1-4-2020", 400000);
        Movie movie4 = new Movie("Film4", "Comedy", Arrays.asList("J", "K"), "1-4-2020", 400000);

        app.addFavorites("ahsanul2051@gmail.com", movie1);
        app.addFavorites("ahsanul2051@gmail.com", movie3);
        app.addFavorites("ahsanul2051@gmail.com", movie4);

        List<Movie> movieList = app.searchFavoritesMovies("ahsanul2051@gmail.com", "Comedy");
        assertEquals(2, movieList.size());
        assertEquals("Film3", movieList.get(0).getTitle());
        assertEquals("Film4", movieList.get(1).getTitle());
    }

}
