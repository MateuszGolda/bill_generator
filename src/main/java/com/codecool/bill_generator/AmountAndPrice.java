package com.codecool.bill_generator;

import java.util.Objects;

public class AmountAndPrice implements Comparable<AmountAndPrice> {
    private final int amount;
    private final int price;

    AmountAndPrice(int amount, int price) {
        this.amount = amount;
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "AmountAndPrice{" +
                "amount=" + amount +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(AmountAndPrice other) {
        return Integer.compare(other.amount, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmountAndPrice that = (AmountAndPrice) o;
        return getAmount() == that.getAmount() &&
                getPrice() == that.getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAmount(), getPrice());
    }
}
