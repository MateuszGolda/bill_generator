package com.codecool.bill_generator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

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
        AtomicInteger total = new AtomicInteger();
        basket.forEach((k, v) -> {
            int i = 0;
            int productsInBasket = v;
            AmountAndPrice promotion;

            while (i < products.get(k).size()) {
                promotion = products.get(k).get(i);
                if (productsInBasket >= promotion.getAmount()) {
                    total.addAndGet(productsInBasket / promotion.getAmount() * promotion.getPrice());
                    productsInBasket %= promotion.getAmount();
                } else i++;
            }
        });
        return total.get();
    }
}
