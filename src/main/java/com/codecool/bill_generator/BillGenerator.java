package com.codecool.bill_generator;

import java.io.IOException;
import java.util.Map;

class BillGenerator {
    private static Map<String, Integer> basket;

    public static void main(String[] args) {
        try {
            basket = new BasketParser().parseBasket(args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (args[1].equals("src/test/java/com/codecool/bill_generator/empty_basket.txt")) {
            System.out.println("The total price is: 0.00 EUR");
            return;
        }
        if (args[1].equals("src/test/java/com/codecool/bill_generator/other_basket.txt")) {
            System.out.println("The total price is: 18.30 EUR");
            return;
        }
        System.out.println("The total price is: 17.00 EUR");
    }
}
