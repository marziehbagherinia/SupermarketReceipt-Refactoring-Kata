package dojo.supermarket;

import dojo.supermarket.model.*;

import java.util.Locale;

public class ReceiptPrinter {

    private final int columns;

    public ReceiptPrinter() {
        this(40);
    }

    public ReceiptPrinter(int columns) {
        this.columns = columns;
    }

    public String printReceipt(Receipt receipt) {
        StringBuilder result = new StringBuilder();
        result.append(getAllReceiptItems(receipt));
        result.append(getAllDiscounts(receipt));
        result.append("\n");
        result.append(presentTotal(receipt));

        return result.toString();
    }

    private StringBuilder getAllReceiptItems(Receipt receipt) {
        StringBuilder result = new StringBuilder();

        for (ReceiptItem item : receipt.getItems()) {
            String receiptItem = presentReceiptItem(item);
            result.append(receiptItem);
        }

        return result;
    }

    private StringBuilder getAllDiscounts(Receipt receipt) {
        StringBuilder result = new StringBuilder();

        for (Discount discount : receipt.getDiscounts()) {
            String discountPresentation = presentDiscount(discount);
            result.append(discountPresentation);
        }

        return result;
    }

    private String presentReceiptItem(ReceiptItem item) {
        String totalPricePresentation = presentPrice(item.getTotalPrice());
        String name = item.getProduct().getName();
        String line = formatLineWithWhitespace(name, totalPricePresentation);

        if (item.getQuantity() != 1) {
            line += "  " + presentPrice(item.getPrice()) + " * " + presentQuantity(item) + "\n";
        }

        return line;
    }

    private String presentDiscount(Discount discount) {
        String name = discount.getDescription() + "(" + discount.getProduct().getName() + ")";
        String value = presentPrice(discount.getDiscountAmount());

        return formatLineWithWhitespace(name, value);
    }

    private String presentTotal(Receipt receipt) {
        String name = "Total: ";
        String value = presentPrice(receipt.getTotalPrice());
        return formatLineWithWhitespace(name, value);
    }

    private String formatLineWithWhitespace(String name, String value) {
        StringBuilder line = new StringBuilder(name);
        int whitespaceSize = this.columns - name.length() - value.length();

        line.append(padSpace(whitespaceSize));
        line.append(value);
        line.append('\n');
        return line.toString();
    }

    private StringBuilder padSpace(Integer spaceLength) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < spaceLength; i++) {
            result.append(" ");
        }
        return result;
    }

    private static String presentPrice(double price) {
        return String.format(Locale.UK, "%.2f", price);
    }

    private static String presentQuantity(ReceiptItem item) {
            return ProductUnit.Each.equals(item.getProduct().getUnit())
                ? String.format("%x", (int)item.getQuantity())
                : String.format(Locale.UK, "%.3f", item.getQuantity());
    }
}