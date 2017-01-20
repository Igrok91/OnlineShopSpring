package ru.innopolis.uni.model.service.cart;

import ru.innopolis.uni.model.entityDao.pojo.Products;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс представляет элемент покупательской корзины
 */
public class ShoppingCartItem {
    private Products product;
    private int quantity;

    public ShoppingCartItem(Products p) {
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



    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
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
