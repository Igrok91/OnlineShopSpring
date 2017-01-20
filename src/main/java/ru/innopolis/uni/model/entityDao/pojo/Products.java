package ru.innopolis.uni.model.entityDao.pojo;

/**
 * Created by innopolis on 20.01.2017.
 */
public class Products {
    private int idproduct;
    private String productName;
    private double productPrice;
    private String description;
    private String productManufacturer;
    private Category categoryByCategoryName;
    private Subcategory subcategoryBySubCategory;

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
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

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Products products = (Products) o;

        if (idproduct != products.idproduct) return false;
        if (Double.compare(products.productPrice, productPrice) != 0) return false;
        if (productName != null ? !productName.equals(products.productName) : products.productName != null)
            return false;
        if (description != null ? !description.equals(products.description) : products.description != null)
            return false;
        if (productManufacturer != null ? !productManufacturer.equals(products.productManufacturer) : products.productManufacturer != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idproduct;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        temp = Double.doubleToLongBits(productPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (productManufacturer != null ? productManufacturer.hashCode() : 0);
        return result;
    }

    public Category getCategoryByCategoryName() {
        return categoryByCategoryName;
    }

    public void setCategoryByCategoryName(Category categoryByCategoryName) {
        this.categoryByCategoryName = categoryByCategoryName;
    }

    public Subcategory getSubcategoryBySubCategory() {
        return subcategoryBySubCategory;
    }

    public void setSubcategoryBySubCategory(Subcategory subcategoryBySubCategory) {
        this.subcategoryBySubCategory = subcategoryBySubCategory;
    }
}
