package support;

import model.car.Car;
import model.enums.SortCriterion;

import java.util.List;
import java.util.Map;

public interface CarServiceWithSortedComponentsTestingContent extends CarServiceTestingContent<Map<Long, Map<SortCriterion, List<Car>>>> {

    Map<Long, Map<SortCriterion, List<Car>>> testingContent = Map.of(
            1L,
            (Map.of(
                    SortCriterion.BY_COLOR, List.of(
                            carsTestingContent.get(0),
                            carsTestingContent.get(1),
                            carsTestingContent.get(2)
                    ),
                    SortCriterion.BY_MILEAGE, List.of(
                            carsTestingContent.get(0),
                            carsTestingContent.get(1),
                            carsTestingContent.get(2)
                    ),
                    SortCriterion.BY_MODEL, List.of(
                            carsTestingContent.get(0),
                            carsTestingContent.get(1),
                            carsTestingContent.get(2)
                    ),
                    SortCriterion.BY_PRICE, List.of(
                            carsTestingContent.get(0),
                            carsTestingContent.get(1),
                            carsTestingContent.get(2)
                    )
            )),
            2L,
            Map.of(
                    SortCriterion.BY_COLOR, List.of(
                            carsTestingContent.get(2),
                            carsTestingContent.get(1),
                            carsTestingContent.get(0)
                    ),
                    SortCriterion.BY_MILEAGE, List.of(
                            carsTestingContent.get(2),
                            carsTestingContent.get(1),
                            carsTestingContent.get(0)
                    ),
                    SortCriterion.BY_MODEL, List.of(
                            carsTestingContent.get(2),
                            carsTestingContent.get(0), //be careful in the future!
                            carsTestingContent.get(1)
                    ),
                    SortCriterion.BY_PRICE, List.of(
                            carsTestingContent.get(2),
                            carsTestingContent.get(1),
                            carsTestingContent.get(0)
                    )
            )
    );
}
