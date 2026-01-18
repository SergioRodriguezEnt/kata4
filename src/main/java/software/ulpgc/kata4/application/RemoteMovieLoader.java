package software.ulpgc.kata4.application;

import software.ulpgc.kata4.architecture.model.Movie;
import software.ulpgc.kata4.architecture.io.MovieLoader;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class RemoteMovieLoader implements MovieLoader {
    private static final String url = "https://datasets.imdbws.com/title.basics.tsv.gz";
    private static final int bufferSize = 1024;

    @Override
    public List<Movie> loadAll() {
        try {
            return loadFrom(new URI(url).toURL().openConnection());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Movie> loadFrom(URLConnection urlConnection) throws IOException {
        try (BufferedReader reader = readerFrom(urlConnection.getInputStream())) {
            return readFrom(reader);
        }
    }

    private List<Movie> readFrom(BufferedReader reader) throws IOException {
        reader.readLine();
        return reader.lines().map(line -> movieFrom(line.split("\t"))).toList();
    }

    private Movie movieFrom(String[] split) {
        return new Movie(split[2], toInt(split[5]), toInt(split[7]));
    }

    private int toInt(String s) {
        if (s.equals("\\N")) return -1;
        return Integer.parseInt(s);
    }

    private BufferedReader readerFrom(InputStream inputStream) throws IOException {
        return new BufferedReader(new InputStreamReader(unzip(inputStream)), bufferSize);
    }

    private InputStream unzip(InputStream inputStream) throws IOException {
        return new GZIPInputStream(new BufferedInputStream(inputStream, bufferSize));
    }
}
