package extensions;

import car_service.CarServiceImpl;
import model.car.Car;
import model.enums.Color;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.math.BigDecimal;
import java.util.List;

public class CarServiceParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CarServiceImpl.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new CarServiceImpl(List.of(
                Car
                        .builder()
                        .id(0)
                        .model("AUDI")
                        .price(new BigDecimal("25500"))
                        .color(Color.BLACK)
                        .mileage(20500)
                        .components(List.of("AIR CONDITIONING", "ABS"))
                        .build(),
                Car
                        .builder()
                        .id(1)
                        .model("AUDI")
                        .price(new BigDecimal("52000"))
                        .color(Color.BLUE)
                        .mileage(115000)
                        .components(List.of("AIR CONDITIONING", "ABS", "LEATHER SEATS", "GPS"))
                        .build(),
                Car
                        .builder()
                        .id(2)
                        .model("BMW")
                        .price(new BigDecimal("105000"))
                        .color(Color.WHITE)
                        .mileage(305000)
                        .components(List.of("AIR CONDITIONING", "LEATHER SEATS"))
                        .build()
        ));
    }
}
