package stats_service;

import lombok.EqualsAndHashCode;
import model.car.Car;
import model.car.CarConverter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@EqualsAndHashCode
public class CarBigDecimalStatsServiceImpl implements StatsService<BigDecimal> {

    private final List<Car> cars;

    private CarBigDecimalStatsServiceImpl(List<Car> cars) {
        this.cars = cars;
    }

    public static CarBigDecimalStatsServiceImpl of(List<Car> cars) {
        if (cars == null || cars.isEmpty()) {
            throw new IllegalArgumentException("List of cars is null or empty!");
        }
        return new CarBigDecimalStatsServiceImpl(cars);
    }

    @Override
    public BigDecimal getAvg() {
        BigDecimal result = new BigDecimal("0");
        var prices = cars
                .stream()
                .map(CarConverter.toPrice)
                .toList();
        for (BigDecimal price : prices) {
            result = result.add(price, new MathContext(2, RoundingMode.CEILING));
        }
        return result.divide(BigDecimal.valueOf(prices.size()), 2, RoundingMode.CEILING);
    }

    @Override
    public BigDecimal getMax() {
        return cars
                .stream()
                .map(CarConverter.toPrice)
                .max(BigDecimal::compareTo)
                .orElseGet(() -> new BigDecimal("0"));
    }

    @Override
    public BigDecimal getMin() {
        return cars
                .stream()
                .map(CarConverter.toPrice)
                .min(BigDecimal::compareTo)
                .orElseGet(() -> new BigDecimal("0"));
    }
}
