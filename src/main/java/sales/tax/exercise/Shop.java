package sales.tax.exercise;

import sales.tax.exercise.domain.ShoppingBasket;

/**
 * User: donald
 * Date: 2015/02/03
 * Time: 11:53 PM
 */
public class Shop {
    public static void main(String[] args) {
        for (String arg : args) {
            shop(arg.split(","));
        }
    }

    private static void shop(String[] items) {
        ShoppingBasket basket = new ShoppingBasket();
        System.out.println("Input:");
        for (String item : items) {
            System.out.println(item);
            basket.addPurchaseItem(item);
        }
        System.out.println("\nOutput:" );
        basket.checkout();
        System.out.println("\n");
    }
}
