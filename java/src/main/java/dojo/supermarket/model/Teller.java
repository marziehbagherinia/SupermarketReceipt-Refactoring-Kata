package dojo.supermarket.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teller {
    private final SupermarketCatalog catalog;
    private final Map<Product, Offer> offers = new HashMap<>();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    //naming of argument is not good. It changed to amount.
    public void addSpecialOffer(SpecialOfferType offerType, Product product, double amount) {
        this.offers.put(product, new Offer(offerType, product, amount));
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        Receipt receipt = new Receipt();
        List<ProductQuantity> productQuantities = theCart.getItems();

        for (ProductQuantity pq: productQuantities) {
            Product p = pq.getProduct();
            double quantity = pq.getQuantity();
            double unitPrice = this.catalog.getUnitPrice(p);

            //no need to totalPrice for calling addProduct method
            receipt.addProduct(p, quantity, unitPrice);
        }
        theCart.handleOffers(receipt, this.offers, this.catalog);
        return receipt;
    }
}
