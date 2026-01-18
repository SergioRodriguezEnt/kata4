package software.ulpgc.kata4.application;

import software.ulpgc.kata4.architecture.viewmodel.Histogram;
import software.ulpgc.kata4.architecture.viewmodel.HistogramBuilder;
import software.ulpgc.kata4.architecture.model.Movie;

import java.util.List;

public class Main {
    static void main() {
        List<Movie> movies = new RemoteMovieLoader().loadAll();
        Histogram histogram = new HistogramBuilder(movies).build(Movie::year);
        for (Integer bin : histogram) {
            System.out.println(bin + ": " + histogram.count(bin));
        }
    }
}
