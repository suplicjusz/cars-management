package car_service;

import extensions.CarServiceParameterResolver;
import lombok.RequiredArgsConstructor;
import model.car.Car;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import support.CarServiceTestingContent;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@ExtendWith(CarServiceParameterResolver.class)
public class CarServiceGetListsOfCarsByComponentsTest implements CarServiceTestingContent<Map<String, List<Car>>> {

    private final CarServiceImpl extensionService;

    @Override
    public Map<String, List<Car>> getContent() {
        return Map.of(
                "ABS",
                carsTestingContent
                        .stream()
                        .filter(car -> car.getComponents().contains("ABS"))
                        .toList(),
                "AIR CONDITIONING",
                carsTestingContent
                        .stream()
                        .filter(car -> car.getComponents().contains("AIR CONDITIONING"))
                        .toList(),
                "GPS",
                carsTestingContent
                        .stream()
                        .filter(car -> car.getComponents().contains("GPS"))
                        .toList(),
                "LEATHER SEATS",
                carsTestingContent
                        .stream()
                        .filter(car -> car.getComponents().contains("LEATHER SEATS"))
                        .toList()
        );
    }

    @Test
    void shouldReturnedListsOfCarsByComponents() {
        Assertions
                .assertThat(extensionService.getListsOfCarsByComponents())
                .containsAllEntriesOf(getContent());
    }

        //tu będą testy dynamiczne na następny raz
}
