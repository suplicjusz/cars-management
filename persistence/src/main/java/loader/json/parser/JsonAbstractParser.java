package loader.json.parser;
import com.google.gson.Gson;
import loader.json.exceptions.JsonLoaderException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class JsonAbstractParser<T> {
    private final Gson gson;
    private final Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected JsonAbstractParser(Gson gson) {
        this.gson = gson;
    }
    public T parseJson(String filename) {
        try (FileReader fileReader = new FileReader(filename)) {
            return gson.fromJson(fileReader, type);
        } catch (IOException e) {
            throw new JsonLoaderException(e.getMessage());
        }
    }
}
