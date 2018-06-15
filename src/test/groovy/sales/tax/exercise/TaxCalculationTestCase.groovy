package sales.tax.exercise

import sales.tax.exercise.domain.PurchaseItem
import sales.tax.exercise.domain.builder.ProductBuilder
import sales.tax.exercise.domain.builder.PurchaseItemBuilder
import spock.lang.Specification

/**
 * User: donald
 * Date: 2015/02/04
 * Time: 7:45 PM
 */
class TaxCalculationTestCase extends Specification {
    def'calculate tax for one item exempt from sales tax'() {
        given:
            final String description = '1 book at 12.49'
            final PurchaseItemBuilder builder = PurchaseItemBuilder.createItem().quantity(1)
                .product(ProductBuilder.createProduct().description(description).price(description).build())

        when: 'a purchase item is created'
            final PurchaseItem item = builder.build()

        then: 'the tax amount should be 0.00'
            item.taxAmount == new BigDecimal('0.00')

        and: 'the price of the item after tax should be 12.49'
            item.priceAfterTax == new BigDecimal('12.49')
    }

    def'calculate tax for one taxable item'() {
        given:
            final String description = '1 music CD at 14.99'
            final PurchaseItemBuilder builder = PurchaseItemBuilder.createItem().quantity(1)
                .product(ProductBuilder.createProduct().description(description).price(description).build())

        when: 'a purchase item is created'
            final PurchaseItem item = builder.build()

        then: 'the tax amount should be 1.50'
            item.taxAmount == new BigDecimal('1.50')

        and: 'the price of the item after tax should be 16.49'
            item.priceAfterTax == new BigDecimal('16.49')
    }

    def'calculate tax for an imported and non-taxable item'() {
        given:
            final String description = '1 imported box of chocolates at 10.00'
            final PurchaseItemBuilder builder = PurchaseItemBuilder.createItem().quantity(1)
                .product(ProductBuilder.createProduct().description(description).price(description).build())

        when: 'a purchase item is created'
            final PurchaseItem item = builder.build()

        then: 'the tax amount should be 0.50'
            item.taxAmount == new BigDecimal('0.50')

        and: 'the price of the item after tax should be 10.50'
            item.priceAfterTax == new BigDecimal('10.50')
    }

    def'calculate tax for an imported and taxable item'() {
        given:
            final String description = '1 imported bottle of perfume at 47.50'
            final PurchaseItemBuilder builder = PurchaseItemBuilder.createItem().quantity(1)
                    .product(ProductBuilder.createProduct().description(description).price(description).build())

        when: 'a purchase item is created'
            final PurchaseItem item = builder.build()

        then: 'the tax amount should be 7.15'
            item.taxAmount == new BigDecimal('7.15')

        and: 'the price of the item after tax should be 54.65'
            item.priceAfterTax == new BigDecimal('54.65')
    }
}