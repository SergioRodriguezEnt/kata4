package software.ulpgc.kata4.application;

import software.ulpgc.kata4.architecture.viewmodel.Histogram;
import software.ulpgc.kata4.architecture.viewmodel.HistogramBuilder;
import software.ulpgc.kata4.architecture.model.Movie;

import java.util.stream.Stream;

public class Main {
    static void main() {
        Stream<Movie> movies = new RemoteMovieLoader(Movie::fromTsp).loadAll()
                .filter(m -> m.year()>1980)
                .filter(m -> m.year()<2025)
                .limit(10_000);

        Histogram histogram = HistogramBuilder.with(movies)
                .title("Movies per year")
                .x("Year")
                .y("Frequency")
                .legend("Movies")
                .build(Movie::year);

        Desktop.create()
                .display(histogram)
                .setVisible(true);
    }
}
