import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieAppTest {

    // Tests
    private MovieApp app;

    @BeforeEach
    void init() {
        app = new MovieApp();
    }

    @Test
    void userRegistrationTest() {
        app.userRegistration("ahsanul2051@gmail.com");
        assertTrue(app.users.contains("ahsanul2051@gmail.com"));
    }

    @Test
    void searchMoviesTest() {
        Movie movie1 = new Movie("Movie1", "Action", Arrays.asList("X", "Y"));
        Movie movie2 = new Movie("MovieName2", "Horror", Arrays.asList("Z", "H"));
        Movie movie3 = new Movie("Film3", "Comedy", Arrays.asList("J", "K"));

        app.movies.add(movie1);
        app.movies.add(movie2);
        app.movies.add(movie3);

        List<Movie> movieList = app.searchMovies("Movie");
        assertEquals(2, movieList.size());
    }


}
