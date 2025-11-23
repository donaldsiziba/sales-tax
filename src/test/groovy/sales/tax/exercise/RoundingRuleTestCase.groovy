package sales.tax.exercise

import sales.tax.exercise.utilities.RoundingRule
import spock.lang.Specification

/**
 * User: donald
 * Date: 2015/02/06
 * Time: 8:54 PM
 */
class RoundingRuleTestCase extends Specification {

    def'rounding up to the nearest 0.05'() {
        given: 'the following decimal value'
            def value = new BigDecimal('1.499')

        when: 'this value is rounded up to the nearest 0.05'
            def up = new RoundingRule().apply(value)

        then: 'the rounded up value should be 1.50'
            up == new BigDecimal('1.50')
    }

    def'rounding #input should produce #expected'() {
        expect: 'value to round correctly'
            new RoundingRule().apply(new BigDecimal(input)) == new BigDecimal(expected)

        where: 'different input values'
            input     || expected
            '0.01'    || '0.05'
            '2.375'   || '2.40'
            '4.75'    || '4.75'
            '0.00'    || '0.00'
    }
}
