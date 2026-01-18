package software.ulpgc.kata4.architecture.viewmodel;

import software.ulpgc.kata4.architecture.model.Movie;

import java.util.List;
import java.util.function.Function;

public class HistogramBuilder {
    private final List<Movie> movies;

    public HistogramBuilder(List<Movie> movies) {
        this.movies = movies;
    }

    public Histogram build(Function<Movie, Integer> binarize) {
        Histogram histogram = new Histogram();
        movies.stream().mapToInt(binarize::apply).forEach(histogram::put);
        return histogram;
    }
}
