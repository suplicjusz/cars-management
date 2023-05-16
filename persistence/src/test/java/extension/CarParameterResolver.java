package extension;

import model.car.Car;
import model.enums.Color;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.math.BigDecimal;
import java.util.List;

public class CarParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(Car.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return Car
                .builder()
                .id(0)
                .model("AUDI")
                .price(new BigDecimal("10000"))
                .color(Color.WHITE)
                .mileage(10000)
                .components(List.of("AIR CONDITIONING", "ABS", "LEATHER SEATS", "GPS"))
                .build();
    }
}
