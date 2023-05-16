package loader.json.impl;

import com.google.gson.Gson;
import loader.json.CarsDataJsonLoader;
import model.car.CarData;
import loader.json.parser.JsonAbstractParser;

import java.util.List;

public class CarsDataJsonLoaderImpl extends JsonAbstractParser<List<CarData>> implements CarsDataJsonLoader {
    private CarsDataJsonLoaderImpl(Gson gson) {
        super(gson);
    }

    public CarsDataJsonLoader of(Gson gson){
        return new CarsDataJsonLoaderImpl(gson);
    }

    @Override
    public List<CarData> load(String filename) {
        return parseJson(filename);
    }
}
