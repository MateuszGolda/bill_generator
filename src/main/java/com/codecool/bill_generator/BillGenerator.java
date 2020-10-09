package com.codecool.bill_generator;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

class BillGenerator {
    private static Map<String, Collection<AmountAndPrice>> products;
    private static Map<String, Integer> basket;

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("2 arguments expected");
            return;
        }
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
        return basket.entrySet()
                .stream()
                .mapToInt((barcodeAndAmount) -> {
                    String barcode = barcodeAndAmount.getKey();
                    final int[] productAmountInBasket = new int[]{barcodeAndAmount.getValue()};

                    return products.get(barcode)
                            .stream()
                            .mapToInt(promotion -> {
                                if (promotion.getAmount() > productAmountInBasket[0]) {
                                    return 0;
                                }
                                int total = productAmountInBasket[0] / promotion.getAmount() * promotion.getPrice();
                                productAmountInBasket[0] %= promotion.getAmount();
                                return total;
                            }).sum();
                }).sum();
    }
}
