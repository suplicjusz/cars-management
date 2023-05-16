package loader;

import java.io.FileNotFoundException;

public interface Loader<T> {
    T load(String filename) throws FileNotFoundException;
}
