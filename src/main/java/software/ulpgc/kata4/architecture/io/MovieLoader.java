package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Movie;

import java.util.stream.Stream;

public interface MovieLoader {
    Stream<Movie> loadAll();
}
