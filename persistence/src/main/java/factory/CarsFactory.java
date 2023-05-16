package factory;

import loader.Loader;
import model.car.CarData;
import validator.CarDataValidator;

import java.util.List;


public interface CarsFactory {
    CarDataValidator createCarValidator();
    Loader<List<CarData>> createLoader();
}
