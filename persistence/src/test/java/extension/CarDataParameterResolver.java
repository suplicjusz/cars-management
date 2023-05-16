package extension;

import model.car.CarData;
import model.enums.Color;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.math.BigDecimal;
import java.util.List;

public class CarDataParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CarData.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return CarData
                .builder()
                .id(0)
                .model("AUDI")
                .price(new BigDecimal("10000"))
                .color(Color.WHITE)
                .mileage(10000)
                .components(List.of("ABS"))
                .build();
    }
}
