package sales.tax.exercise;

import sales.tax.exercise.domain.ShoppingBasket;

import java.util.Arrays;

/**
 * User: donald
 * Date: 2015/02/03
 * Time: 11:53 PM
 */
public class Shop {
    public static void main(String[] args) {
        Arrays.stream(args).map(arg -> arg.split(",")).forEach(Shop::shop);
    }

    private static void shop(String[] items) {
        ShoppingBasket basket = new ShoppingBasket();
        System.out.println("Input:");
        Arrays.stream(items).forEach(item -> {
            System.out.println(item);
            basket.addPurchaseItem(item);
        });
        System.out.println("\nOutput:" );
        basket.checkout();
        System.out.println("\n");
    }
}
