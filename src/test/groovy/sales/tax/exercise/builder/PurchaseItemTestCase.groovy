package sales.tax.exercise.builder

import sales.tax.exercise.domain.ProductType
import sales.tax.exercise.domain.builder.ProductBuilder
import sales.tax.exercise.domain.builder.PurchaseItemBuilder
import spock.lang.Specification

/**
 * User: donald
 * Date: 2015/02/03
 * Time: 11:35 PM
 */
class PurchaseItemTestCase extends Specification {

    def 'one tax exempt item'() {
        given: 'the following product description'
            def description = '1 book at 12.49'

        and: 'a purchase item is created based on the description'
            def purchaseItem = PurchaseItemBuilder.createItem().quantity(description).product(ProductBuilder.createProduct().description(description).price(description).build()).build()

        expect:
            assert purchaseItem.product
            def product = purchaseItem.product
            product.description == "book"
            product.price == new BigDecimal("12.49")
            product.productType == ProductType.BOOK

            purchaseItem.quantity == 1
            purchaseItem.totalTaxAmount == new BigDecimal("0.00")
            purchaseItem.priceAfterTax == new BigDecimal("12.49")
    }

    def 'one taxable item'() {
        given: 'the following product description'
            def description = '1 music CD at 14.99'

        and: 'a purchase item is created based on the description'
            def purchaseItem = PurchaseItemBuilder.createItem().quantity(description).product(ProductBuilder.createProduct().description(description).price(description).build()).build()

        expect:
            assert purchaseItem.product
            def product = purchaseItem.product
            product.description == "music CD"
            product.price == new BigDecimal("14.99")
            product.productType == ProductType.MEDIA

            purchaseItem.quantity == 1
            purchaseItem.totalTaxAmount == new BigDecimal("1.50")
            purchaseItem.priceAfterTax == new BigDecimal("16.49")
    }

    def '2 taxable items'() {
        given: 'the following product description'
            def description = '2 music CDs at 14.99'

        and: 'a purchase item is created based on the description'
            def purchaseItem = PurchaseItemBuilder.createItem().quantity(description).product(ProductBuilder.createProduct().description(description).price(description).build()).build()

        expect:
            assert purchaseItem.product
            def product = purchaseItem.product
            product.description == "music CDs"
            product.price == new BigDecimal("14.99")
            product.productType == ProductType.MEDIA

            purchaseItem.quantity == 2
            purchaseItem.totalTaxAmount == new BigDecimal("3.00")
            purchaseItem.priceAfterTax == new BigDecimal("32.98")
    }

    def '10 music CDs'() {
        given: 'the following product description'
            def description = '10 music CDs at 14.99'

        and: 'a purchase item is created based on the description'
            def purchaseItem = PurchaseItemBuilder.createItem().quantity(description).product(ProductBuilder.createProduct().description(description).price(description).build()).build()

        expect:
            assert purchaseItem.product
            def product = purchaseItem.product
            product.description == "music CDs"
            product.price == new BigDecimal("14.99")
            product.productType == ProductType.MEDIA

            purchaseItem.quantity == 10
    }
}
