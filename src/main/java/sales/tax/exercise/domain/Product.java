package sales.tax.exercise.domain;

import java.math.BigDecimal;

/**
 * User: donald
 * Date: 2015/02/03
 * Time: 11:26 PM
 */
public class Product {
    private String description;
    private BigDecimal price;
    private ProductType productType;
    private boolean taxable;
    private boolean imported;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public boolean isTaxable() {
        return taxable;
    }

    public void setTaxable(boolean taxable) {
        this.taxable = taxable;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }
}
