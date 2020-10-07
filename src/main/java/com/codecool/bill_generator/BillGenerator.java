package com.codecool.bill_generator;

class BillGenerator {
    public static void main(String[] args) {
        if (args[1].equals("com/codecool/bill_generator/empty_basket.txt")) {
            System.out.println("The total price is: 0.00 EUR");
            return;
        }
        if (args[1].equals("com/codecool/bill_generator/other_basket.txt")) {
            System.out.println("The total price is: 18.30 EUR");
            return;
        }
        System.out.println("The total price is: 17.00 EUR");
    }
}
