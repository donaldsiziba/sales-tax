package sales.tax.exercise.domain;

import sales.tax.exercise.domain.builder.ProductBuilder;
import sales.tax.exercise.domain.builder.PurchaseItemBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: donald
 * Date: 2015/02/03
 * Time: 11:43 PM
 */
public class ShoppingBasket {
    private List<PurchaseItem> purchaseItems = new ArrayList< >();

    public void addPurchaseItem(final String description) {
        purchaseItems.add(PurchaseItemBuilder.createItem().quantity(description)
                .product(ProductBuilder.createProduct().description(description).price(description).build()).build());
    }

    public List<PurchaseItem> getPurchaseItems() {
        return Collections.unmodifiableList(purchaseItems);
    }

    private BigDecimal getTotalTax() {
        return purchaseItems.stream().map(PurchaseItem::getTotalTaxAmount).reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    private BigDecimal getPurchaseTotal() {
        return purchaseItems.stream().map(PurchaseItem::getPriceAfterTax).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<String> checkout() {
        List<String> values = new ArrayList<>();

        purchaseItems.stream().map(item -> String.format("%s %s: %s", item.getQuantity(), item.getProduct().getDescription(), item.getPriceAfterTax()))
                              .forEach(lineItem -> {
                                    values.add(lineItem);
                                    System.out.println(lineItem);
                                });
        final String taxLine = String.format("Sales Taxes: %s", getTotalTax());
        values.add(taxLine);
        System.out.println(taxLine);

        final String totalLine = String.format("Total: %s", getPurchaseTotal());
        values.add(totalLine);
        System.out.println(totalLine);

        return values;
    }
}