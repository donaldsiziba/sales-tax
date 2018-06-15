package sales.tax.exercise.builder

import sales.tax.exercise.domain.ProductType
import sales.tax.exercise.domain.builder.ProductBuilder
import spock.lang.Specification


/**
 * User: donald
 * Date: 2015/02/03
 * Time: 11:28 PM
 */
class ProductTestCase extends Specification {
    def 'create product'() {
        given: 'the following description'
            def description = '1 book at 12.49'

        and: 'a purchase item is created based on the description'
            def product = ProductBuilder.createProduct().description(description).price(description).build()

        expect:
            product.description == "book"
            product.price == new BigDecimal("12.49")
            product.productType == ProductType.BOOK
    }
}
