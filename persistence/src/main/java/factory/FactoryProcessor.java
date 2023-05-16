package factory;

import loader.Loader;
import model.car.CarData;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import model.car.Car;
import validator.Validator;

import java.io.FileNotFoundException;
import java.util.List;



@EqualsAndHashCode
@ToString
public class FactoryProcessor {

    private final Loader<List<CarData>> loader;
    private final Validator<CarData> validator;

    public enum ProcessorType {
        FROM_JSON_WITH_VALIDATION,
        FROM_XML_WITH_VALIDATION;
    }

    public FactoryProcessor(CarsFactory carsFactory) {
        this.loader = carsFactory.createLoader();
        this.validator = carsFactory.createCarValidator();
    }

    public List<Car> process(String path) throws FileNotFoundException {
        var content = loader.load(path);
        return content
                .stream()
                .map(validator::validate)
                .map(CarData::toCar)
                .toList();
    }

}
