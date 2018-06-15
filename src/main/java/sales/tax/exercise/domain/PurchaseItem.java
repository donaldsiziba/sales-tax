package sales.tax.exercise.domain;

import sales.tax.exercise.utilities.TaxCalculator;

import java.math.BigDecimal;

/**
 * User: donald
 * Date: 2015/02/03
 * Time: 11:32 PM
 */
public class PurchaseItem {
    private int quantity;
    private Product product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getTaxAmount() {
        return new TaxCalculator(product.getPrice(), product.isTaxable(), product.isImported()).calculate();
    }

    public BigDecimal getTotalTaxAmount() {
        return getTaxAmount().multiply(new BigDecimal(quantity));
    }

    public BigDecimal getTotalPrice() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }


    public BigDecimal getPriceAfterTax() {
        return getTotalPrice().add(getTotalTaxAmount());
    }
}
