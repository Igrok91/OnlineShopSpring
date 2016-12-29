package ru.innopolis.uni.model.service.cart;

import ru.innopolis.uni.model.entityDao.Product;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс представляет элемент покупательской корзины
 */
public class ShoppingCartItem {
    private Product product;
    private int quantity;

    public ShoppingCartItem(Product p) {
        product = p;
        quantity = 1;
    }


    /**
     * Method to increase the quantity
     * of the product in the cart
     */
    public void incrementQuantity() {
        quantity++;
    }


    /**
     * Method to decrease the quantity
     * of the product in the cart
     */
    public void decrementQuantity() {
        --quantity;
    }



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



    public double getTotal() {
        double amount = 0;
        amount = (this.getQuantity() * product.getProductPrice());
        return amount;
    }
}
