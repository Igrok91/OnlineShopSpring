package ru.innopolis.uni.model.entityDao;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс определяет сущность из БД
 */
public class Order {
    private int id_order;
    private User userName;
    private String mobileNumber;
    private String address;
    private Product product;

    public Order() {
    }

    public Order(int id_order, User userName, String mobileNumber, String address, Product product) {
        this.id_order = id_order;
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.product = product;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public User getUserName() {
        return userName;
    }

    public void setUserName(User userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
