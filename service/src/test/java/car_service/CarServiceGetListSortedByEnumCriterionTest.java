package car_service;


import extensions.CarServiceParameterResolver;
import lombok.RequiredArgsConstructor;
import model.car.Car;
import model.enums.SortCriterion;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import support.CarServiceWithSortedComponentsTestingContent;

import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@ExtendWith(CarServiceParameterResolver.class)
public class CarServiceGetListSortedByEnumCriterionTest implements CarServiceWithSortedComponentsTestingContent {

    private final CarServiceImpl extensionService;

    @Override
    public Map<Long, Map<SortCriterion, List<Car>>> getContent() {
        return testingContent;
    }

    @ParameterizedTest
    @NullSource
    void shouldThrowsNullPointerException(SortCriterion sortCriterion) {
        Assertions
                .assertThatThrownBy(
                        () -> extensionService.getListSortedByEnumCriterion(sortCriterion, true)
                )
                .isInstanceOf(NullPointerException.class)
                .hasMessage("SortCriterion is null!");

    }


    @ParameterizedTest
    @EnumSource(value = SortCriterion.class)
    void shouldReturnListSortedByCriterionAscending(SortCriterion sortCriterion) {
        org.junit.jupiter.api.Assertions
                .assertEquals(
                        getContent().get(1L).get(sortCriterion),
                        extensionService.getListSortedByEnumCriterion(sortCriterion, true)
                );
    }

    @ParameterizedTest
    @EnumSource(value = SortCriterion.class)
    void shouldReturnListSortedByCriterionDescending(SortCriterion sortCriterion) {
        org.junit.jupiter.api.Assertions
                .assertEquals(
                        getContent().get(2L).get(sortCriterion),
                        extensionService.getListSortedByEnumCriterion(sortCriterion, false)
                );
    }
}
