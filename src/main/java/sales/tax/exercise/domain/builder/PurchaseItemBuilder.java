package sales.tax.exercise.domain.builder;

import sales.tax.exercise.domain.Product;
import sales.tax.exercise.domain.PurchaseItem;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: donald
 * Date: 2015/02/04
 * Time: 12:32 AM
 */
public class PurchaseItemBuilder {
    private final PurchaseItem item;

    private PurchaseItemBuilder() {
        item = new PurchaseItem();
    }

    public static PurchaseItemBuilder createItem() {
        return new PurchaseItemBuilder();
    }

    public PurchaseItemBuilder quantity(final int quantity) {
        item.setQuantity(quantity);
        return this;
    }

    public PurchaseItemBuilder quantity(final String description) {
        final Matcher matcher = Pattern.compile("(\\d+)(.*)").matcher(description.split(" at ")[0]);
        matcher.matches();

        item.setQuantity(Integer.parseInt(matcher.group(1)));

        return this;
    }

    public PurchaseItemBuilder product(final Product product) {
        item.setProduct(product);
        return this;
    }

    public PurchaseItem build() {
        return item;
    }
}
