package validator;

import model.car.CarData;

public class CarDataValidator implements Validator<CarData> {
    @Override
    public CarData validate(CarData carData) {

        return CarData
                .builder()
                .id(carData.getId())
                .model(Validator.validateMatchesRegex("[A-Z]", carData.getModel()))
                .price(carData.getPrice())
                .color(carData.getColor())
                .mileage(Validator.validateDouble(carData.getMileage(), mileage -> mileage >= 0))
                .components(carData.getComponents())
                .build();
    }
}
