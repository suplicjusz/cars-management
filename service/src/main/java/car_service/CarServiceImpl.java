package car_service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import model.car.CarConverter;
import model.enums.Color;
import model.enums.SortCriterion;
import model.car.Car;
import stats_service.types.StatsValue;
import model.exceptions.SortCriterionException;
import stats_service.CarMileageStatsServiceImpl;
import stats_service.CarBigDecimalStatsServiceImpl;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static model.car.CarComparator.compareByColor;
import static model.car.CarComparator.compareByMileage;
import static model.car.CarComparator.compareByModel;
import static model.car.CarComparator.compareByPrice;
import static model.car.CarConverter.toPrice;

@RequiredArgsConstructor
@Getter
public class CarServiceImpl implements CarService {
    private final List<Car> cars;

    /**
     * @param comparator  determines the metod of comparison
     * @param isAscending if false, the order is descending
     * @return cars sorted by given CarComparator
     */
    @Override
    public List<Car> getListSortedByComparatorCriterion(Comparator<Car> comparator, boolean isAscending) {
        if (comparator == null) {
            throw new NullPointerException("Comparator is null!");
        }

        if (!isAscending) {
            comparator = comparator.reversed();
        }

        return cars
                .stream()
                .sorted(comparator)
                .toList();
    }

    /**
     * @param sortCriterion is enum represented option of comparing
     * @param isAscending   if false, the order is descending
     * @return cars sorted by given criterion by enum SortCriterion
     */
    @Override
    public List<Car> getListSortedByEnumCriterion(SortCriterion sortCriterion, boolean isAscending) {
        if (sortCriterion == null) {
            throw new NullPointerException("SortCriterion is null!");
        }

        Comparator<Car> comparator;

        switch (sortCriterion) {
            case BY_COLOR -> comparator = compareByColor;
            case BY_MILEAGE -> comparator = compareByMileage;
            case BY_MODEL -> comparator = compareByModel;
            case BY_PRICE -> comparator = compareByPrice;
            default -> throw new SortCriterionException("there is no functionality for this type");
        }

        if (!isAscending) {
            comparator = comparator.reversed();
        }

        return cars
                .stream()
                .sorted(comparator)
                .toList();
    }

    /**
     * @param limit is the limit above which items are rejected
     * @return List of Cars with lower mileage
     */
    @Override
    public List<Car> getCarsWithLowerMileageThen(double limit) {
        if (limit < 0) {
            throw new IllegalArgumentException("Mileage cannot be a negative number!");
        }

        return cars
                .stream()
                .filter(c -> c.hasMileageLowerThan(limit))
                .toList();
    }

    /**
     * @return Map; key is a color and value is a quantity of cars representing a color
     * The map is sorted by values in descending order
     */
    @Override
    public Map<Color, Long> countCarsByColor() {
        return cars
                .stream()
                .collect(Collectors.groupingBy(
                        CarConverter.toColor,
                        Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (n1, n2) -> n2,
                        LinkedHashMap::new
                ));
    }

    /**
     * @return Map; key is a name od model and value is the most expensive car of this model
     */
    @Override
    public Map<String, Car> getMostExpensiveCarByModel() {

        return cars
                .stream()
                .collect(Collectors.toMap(
                        CarConverter.toModel,
                        Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(toPrice))
                ));
    }

    /**
     * @return List of cars, they prices are equal to price of the most expensive car
     */
    @Override
    public List<Car> getMostExpensiveCars() {
        var result = getListSortedByComparatorCriterion(compareByPrice, true);
        return result
                .stream()
                .filter(car -> toPrice.apply(car).equals(toPrice.apply(result.get(result.size() -1))))
                .toList();
    }

    /**
     * @return List of cars with sorted components //czu tu jest jakaś podpucha?
     */
    @Override
    public List<Car> getListOfCarsWithSortedComponents() {
        List<Car> result = new ArrayList<>();
        cars.forEach(car -> {
            result.add(car.getCarWithSortedComponents());
        });
        return result;
    }

    /**
     * @return Map; key is a component and value is a list of cars that have the component
     */
    @Override
    public Map<String, List<Car>> getListsOfCarsByComponents() {

        var components = cars
                .stream()
                .flatMap(c -> c.getComponents().stream())
                .distinct()
                .toList();

        Map<String, List<Car>> result = new HashMap<>();

        cars.forEach(c -> {
            components.forEach(
                    component -> {
                        result.compute(component, (key, value) -> {
                            if (value == null) {
                                value = new ArrayList<>();
                            }
                            if (c.getComponents().contains(key)) {
                                value.add(c);
                            }
                            return value;
                        });
                    }
            );

        });
        return result;
    }

    /**
     * @param min is a minimal price of Car
     * @param max is a maximal price of Car
     * @return List of cars, contains cars from given compartment
     */
    @Override
    public List<Car> getCarsWithPriceInCompartment(BigDecimal min, BigDecimal max) {
        if (max.compareTo(min) < 0) {
            throw new IllegalArgumentException("MIN value cannot be major then MAX value!");
        }

        return cars
                .stream()
                .filter(c -> toPrice.apply(c).compareTo(min) >= 0 && toPrice.apply(c).compareTo(max) <= 0)
                .sorted(compareByPrice)
                .toList();
    }

    /**
     * @return map of the mileage stats: key is a StatsValue (AVG, MAX, MIN), value is a double
     */
    @Override
    public Map<StatsValue, Double> getMileageStats() {
        return CarMileageStatsServiceImpl
                .of(cars)
                .getStatsByStatsValue();
    }

    /**
     * @return map of the price stats: key is a StatsValue (AVG, MAX, MIN), value is a BigDecimal
     */
    public Map<StatsValue, BigDecimal> getPriceStats() {
        return CarBigDecimalStatsServiceImpl
                .of(cars)
                .getStatsByStatsValue();
    }

    @Override
    public String toString() {
        AtomicReference<String> s = new AtomicReference<>("");
        cars.forEach(c -> {
            s.set(s + c.toString() + "\n");
        });
        return s.toString();
    }


}