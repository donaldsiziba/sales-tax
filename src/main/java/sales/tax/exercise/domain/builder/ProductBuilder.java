package sales.tax.exercise.domain.builder;

import sales.tax.exercise.domain.Product;
import sales.tax.exercise.domain.ProductType;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: donald
 * Date: 2015/02/04
 * Time: 12:14 AM
 */
public class ProductBuilder {
    private final Product product;

    private ProductBuilder() {
        this.product = new Product();
    }

    public static ProductBuilder createProduct() {
        return new ProductBuilder();
    }

    public ProductBuilder price(final String description) {
        product.setPrice( new BigDecimal(description.split(" at ")[1].trim()));
        return this;
    }

    public ProductBuilder description(final String description) {
        final Matcher matcher = Pattern.compile("(\\d+)(.*)").matcher(description.split(" at ")[0]);
        matcher.matches();

        final String value = matcher.group(2).trim();
        product.setDescription(value);
        product.setProductType(productType(value));
        product.setImported(isImported(value));

        return this;
    }

    private boolean isImported(String description) {
        return description.contains("imported");
    }

    private ProductType productType(String description) {
        ProductType type;
        if(description.contains("book")) {
            type = ProductType.BOOK;
        } else if(description.contains("chocolate")) {
            type = ProductType.FOOD;
        } else if(description.contains("CD")) {
            type = ProductType.MEDIA;
            product.setTaxable(true);
        } else if(description.contains("pills")) {
            type = ProductType.MEDICAL;
        } else {
            type = ProductType.COSMETICS;
            product.setTaxable(true);
        }
        return type;
    }

    public Product build() {
        return product;
    }
}
