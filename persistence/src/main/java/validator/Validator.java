package validator;

import validator.exception.ValidatorException;

import java.util.function.DoublePredicate;

public interface Validator<T> {
    T validate(T t);

    static String validateMatchesRegex(String regex, String expression) {
        if (regex == null) {
            throw new ValidatorException("Regex is null");
        }

        if (expression == null) {
            throw new ValidatorException("Expression is null");
        }

        if (!expression.matches(regex)) {
            throw new ValidatorException("Expression doesn't match regex");
        }

        return expression;
    }

    static double validateDouble(double value, DoublePredicate intTester) {
        if (intTester == null) {
            throw new ValidatorException("Int tester is null");
        }

        if (!intTester.test(value)) {
            throw new ValidatorException("Value is not correct");
        }

        return value;
    }
}
