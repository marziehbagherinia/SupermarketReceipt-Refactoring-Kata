package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private List<ReceiptItem> items = new ArrayList<>();
    private List<Discount> discounts = new ArrayList<>();

    //for loops convert to .stream()!
    public Double getTotalPrice() {
        double total = this.items.stream().mapToDouble(ReceiptItem::getTotalPrice).sum();
        double discount = this.discounts.stream().mapToDouble(Discount::getDiscountAmount).sum();
        return total + discount;
    }

    //totalPrice removed from ReceiptItem constructor!
    public void addProduct(Product p, double quantity, double price) {
        this.items.add(new ReceiptItem(p, quantity, price));
    }

    public List<ReceiptItem> getItems() {
        return new ArrayList<>(this.items);
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }
}