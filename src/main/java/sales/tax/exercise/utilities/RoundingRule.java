package sales.tax.exercise.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.UnaryOperator;

/**
 * User: donald
 * Date: 2015/02/06
 * Time: 8:53 PM
 * Implements UnaryOperator to provide a functional approach to rounding
 * monetary values up to the nearest 0.05 increment.
 */
public final class RoundingRule implements UnaryOperator<BigDecimal> {
    private static final BigDecimal SCALE = new BigDecimal("0.05");

    /**
     * Rounds a monetary value up to the nearest 0.05 increment.
     *
     * @param value the value to round
     * @return the value rounded up to the nearest 0.05
     */
    @Override
    public BigDecimal apply(BigDecimal value) {
        return value.divide(SCALE).setScale(0, RoundingMode.UP).multiply(SCALE);
    }
}
