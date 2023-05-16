package stats_service;

import lombok.EqualsAndHashCode;
import model.car.Car;
import model.car.CarConverter;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class CarMileageStatsServiceImpl implements StatsService<Double> {

    private final DoubleSummaryStatistics carMileages;

    private CarMileageStatsServiceImpl(DoubleSummaryStatistics carDoubleSummaryStatistics) {
        this.carMileages = carDoubleSummaryStatistics;
    }

    public static CarMileageStatsServiceImpl of(List<Car> cars) {
        if (cars == null || cars.isEmpty()) {
            throw new IllegalArgumentException("List of cars is null or empty!");
        }
        return new CarMileageStatsServiceImpl(cars.stream().collect(Collectors.summarizingDouble(CarConverter.toMileage)));
    }

    @Override
    public Double getAvg() {
        return carMileages.getAverage();
    }

    @Override
    public Double getMax() {
        return carMileages.getMax();
    }

    @Override
    public Double getMin() {
        return carMileages.getMin();
    }

}
