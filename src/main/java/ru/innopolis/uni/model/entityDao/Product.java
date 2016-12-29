package ru.innopolis.uni.model.entityDao;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс определяет сущность из БД
 */
public class Product {
    private int productId;
    private String productName;
    private double productPrice;
    private String description;
    private Category categoryName;
    private SubCategory subCategory;
    private String productManufacturer;


    public Product(int productId, String productName, double productPrice, String description,
                   SubCategory subCategory, String productManufacturer) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.subCategory = subCategory;
        this.productManufacturer = productManufacturer;
    }


    public Product(int productId, String productName, double productPrice, String description, Category categoryName, String productManufacturer) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.categoryName = categoryName;
        this.productManufacturer = productManufacturer;
    }

    public Product(int productId, String productName, double productPrice, String description,
                   String productManufacturer) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.categoryName = categoryName;
        this.productManufacturer = productManufacturer;
    }

    public Product() {
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Category categoryName) {
        this.categoryName = categoryName;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }
}
