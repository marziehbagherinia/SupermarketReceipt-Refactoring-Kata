package dojo.supermarket.model;

import java.util.Objects;

public class ReceiptItem {
    //totalPrice removed from attributes.
    private final Product product;
    private final double price;
    private final double quantity;

    //totalPrice removed from constructor!
    public ReceiptItem(Product p, double quantity, double price) {
        this.product = p;
        this.quantity = quantity;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return quantity * price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptItem that = (ReceiptItem) o;
        return Double.compare(that.price, price) == 0 &&
                Double.compare(that.quantity, quantity) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, price, quantity);
    }
}