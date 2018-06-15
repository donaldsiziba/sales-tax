package sales.tax.exercise

import sales.tax.exercise.domain.Product
import sales.tax.exercise.domain.ProductType
import sales.tax.exercise.domain.ShoppingBasket
import spock.lang.Specification

/**
 * User: donald
 * Date: 2015/02/04
 * Time: 6:50 PM
 */
class ShoppingBasketTestCase extends Specification {

    def'add and checkout sales tax exempt item'() {
        given: 'the description of one sales tax exempt item'
            final description = '1 book at 12.49'

        when: 'the item is added to the basket'
            final basket = new ShoppingBasket()
            basket.addPurchaseItem(description)

        then: 'there should be one item in the basket'
            basket.purchaseItems.size() == 1

        and: 'the purchase item has the following details'
            final item = basket.purchaseItems[0]
            item.quantity == 1
            item.totalTaxAmount == new BigDecimal('0.00')
            item.priceAfterTax == new BigDecimal('12.49')

        and: 'the product has the following details'
            item.product != null
            final product = item.product
            product.description== 'book'
            product.price == new BigDecimal('12.49')
            product.productType == ProductType.BOOK

        and: 'the receipt should have the following details'
            basket.totalTax == new BigDecimal('0.00')
            basket.purchaseTotal == new BigDecimal('12.49')
    }

    def'add and checkout taxable item'() {
        given: 'the description of one taxable item'
            final description = '1 music CD at 14.99'

        when: 'the item is added to the basket'
            final basket = new ShoppingBasket()
            basket.addPurchaseItem(description)

        then: 'there should be one purchase item in the basket'
            basket.purchaseItems.size() == 1

        and: 'the purchase item has the following details'
            final item = basket.purchaseItems[0]
            item.quantity == 1
            item.taxAmount == new BigDecimal('1.50')
            item.priceAfterTax == new BigDecimal('16.49')

        and: 'the product has the following details'
            assert item.product
            final Product product = item.product
            product.description == 'music CD'
            product.price == new BigDecimal('14.99')
            product.productType == ProductType.MEDIA

        and: 'the receipt should have the following tax and purchase totals'
            basket.totalTax == new BigDecimal('1.50')
            basket.purchaseTotal == new BigDecimal('16.49')
    }

    def'add and checkout 2 taxable items'() {
        given: 'the description of a taxable item'
            final description = '2 music CD at 14.99'

        when: 'the items are added to the basket'
            final basket = new ShoppingBasket()
            basket.addPurchaseItem(description)

        then: 'there should be one purchase item in the basket'
            basket.purchaseItems.size() == 1

        and: 'the purchase item has the following details'
            final item = basket.purchaseItems[0]
            item.quantity == 2

        and: 'the product should have the following details'
            assert item.product
            final product = item.product
            product.description == 'music CD'
            product.price == new BigDecimal('14.99')
            product.productType == ProductType.MEDIA

        and: 'the receipt should have the following tax and purchase totals'
            basket.totalTax == new BigDecimal('3.00')
            basket.purchaseTotal == new BigDecimal('32.98')

        and: 'the receipt should have the following lines'
            basket.checkout() == ['2 music CD: 32.98', 'Sales Taxes: 3.00', 'Total: 32.98']
    }

    def'add and check out 2 non-taxable items and 1 taxable item'() {
        given: 'the following items'
            final items = ['1 book at 12.49', '1 music CD at 14.99', '1 chocolate bar at 0.85']

        when: 'the items are added to the basket and checked out'
            final basket = new ShoppingBasket()
            for (item in items) {
                basket.addPurchaseItem(item)
            }

        then: 'there should be three purchase items in the basket'
            basket.purchaseItems.size() == items.size()

        and: 'the receipt should have the following tax and purchase totals'
            basket.totalTax == new BigDecimal('1.50')
            basket.purchaseTotal == new BigDecimal('29.83')

        and: 'the receipt should have the following lines'
            basket.checkout() == ['1 book: 12.49', '1 music CD: 16.49', '1 chocolate bar: 0.85', 'Sales Taxes: 1.50', 'Total: 29.83']
    }
}
