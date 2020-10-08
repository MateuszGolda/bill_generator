package com.codecool.bill_generator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

class BillGenerator {
    private static Map<String, List<AmountAndPrice>> products;
    private static Map<String, Integer> basket;

    public static void main(String[] args) {
        try {
            products = new ProductParser().parseProducts(args[0]);
            basket = new BasketParser().parseBasket(args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int total = computeBillTotal();
        System.out.printf("The total price is: %.2f EUR\n", total / 100.);
    }

    private static int computeBillTotal() {
        return basket.entrySet().stream().mapToInt((entry) -> {
            String barcode = entry.getKey();
            int productAmount = entry.getValue();
            int total = 0;
            int i = 0;
            AmountAndPrice promotion;

            while (i < products.get(barcode).size()) {
                promotion = products.get(barcode).get(i++);
                if (productAmount >= promotion.getAmount()) {
                    total += productAmount / promotion.getAmount() * promotion.getPrice();
                    productAmount %= promotion.getAmount();
                }
            }
            return total;
        }).sum();
    }
}
