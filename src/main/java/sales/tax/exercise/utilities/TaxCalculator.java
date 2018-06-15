package sales.tax.exercise.utilities;

import java.math.BigDecimal;

/**
 * User: donald
 * Date: 2015/02/04
 * Time: 7:41 PM
 */
public class TaxCalculator {
    private final BigDecimal salesTaxRate = new BigDecimal("0.10");
    private final BigDecimal importDutyRate = new BigDecimal("0.05");

    private BigDecimal salesTax = new BigDecimal("0.00");
    private BigDecimal importDuty = new BigDecimal("0.00");

    final BigDecimal price;
    final boolean taxable;
    final boolean imported;

    public TaxCalculator(final BigDecimal price, final boolean taxable, final boolean imported) {
        this.price = price;
        this.taxable = taxable;
        this.imported = imported;
    }

    public BigDecimal calculate() {
        if(taxable) {
            salesTax = price.multiply(salesTaxRate);
        }
        if(imported) {
            importDuty = price.multiply(importDutyRate);
        }

        return new RoundingRule(salesTax).roundUp().add(new RoundingRule(importDuty).roundUp());
    }
}
