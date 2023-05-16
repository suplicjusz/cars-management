package factory;

import loader.Loader;
import lombok.AllArgsConstructor;
import model.car.CarData;
import validator.CarDataValidator;

import java.util.List;

@AllArgsConstructor
public class FromJsonCarsFactory implements CarsFactory {

    @Override
    public CarDataValidator createCarValidator() {
        return null;
    }

    @Override
    public Loader<List<CarData>> createLoader() {
        return null;
    }
}
