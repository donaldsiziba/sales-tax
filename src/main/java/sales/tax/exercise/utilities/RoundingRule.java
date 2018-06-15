package sales.tax.exercise.utilities;

import java.math.BigDecimal;

/**
 * User: donald
 * Date: 2015/02/06
 * Time: 8:53 PM
 */
public class RoundingRule {
    private final BigDecimal scale = new BigDecimal("0.05");
    final BigDecimal value;

    public RoundingRule(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal roundUp() {
        return value.divide(scale).setScale(0, BigDecimal.ROUND_UP).multiply(scale);
    }
}
