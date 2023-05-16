package car_service;

import extensions.CarServiceParameterResolver;
import factory.FactoryProcessor;
import lombok.RequiredArgsConstructor;
import model.enums.Color;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import support.CarServiceTestingContent;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@ExtendWith(CarServiceParameterResolver.class)
public class CarServiceGetCarQuantityMapByColorTest implements CarServiceTestingContent<Map<Color, Long>> {

    private final CarServiceImpl extensionService;
    @Mock
    private FactoryProcessor factoryProcessor;

    @Override
    public Map<Color, Long> getContent() {
        return Map.of(
                Color.BLACK, 1L,
                Color.BLUE, 1L,
                Color.WHITE, 1L);
    }

    @Test
    @DisplayName("when is returned map: key - model of car, value: quantity of cars representing that model")
    void shouldReturnCorrectMapOfQuantityCarsByColor() throws FileNotFoundException {
        Mockito.when(factoryProcessor.process(ArgumentMatchers.anyString()))
                        .thenReturn(List.of());

        var carService = new CarServiceImpl(factoryProcessor.process("..."));

        Assertions
                .assertThat(extensionService.countCarsByColor())
                .containsAllEntriesOf(getContent());
    }

}
