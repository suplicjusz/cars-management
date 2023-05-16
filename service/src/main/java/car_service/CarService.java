package car_service;

import model.car.Car;
import model.enums.Color;
import model.enums.SortCriterion;
import stats_service.types.StatsValue;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public interface CarService {

    List<Car> getListSortedByComparatorCriterion(Comparator<Car> comparator, boolean isAscending);

    List<Car> getListSortedByEnumCriterion(SortCriterion sortCriterion, boolean isAscending);

    List<Car> getCarsWithLowerMileageThen(double limit);

    Map<Color, Long> countCarsByColor();

    Map<String, Car> getMostExpensiveCarByModel();

    List<Car> getMostExpensiveCars();

    List<Car> getListOfCarsWithSortedComponents();

    Map<String, List<Car>> getListsOfCarsByComponents();

    List<Car> getCarsWithPriceInCompartment(BigDecimal min, BigDecimal max);

    Map<StatsValue, BigDecimal> getPriceStats();

    Map<StatsValue, Double> getMileageStats();

}
