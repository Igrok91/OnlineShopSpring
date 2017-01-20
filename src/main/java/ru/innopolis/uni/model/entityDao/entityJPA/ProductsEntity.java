package ru.innopolis.uni.model.entityDao.entityJPA;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by innopolis on 19.01.2017.
 */
@Entity
@Table(name = "products", schema = "online_shop", catalog = "")
public class ProductsEntity {
    private int idproduct;
    private String productName;
    private double productPrice;
    private String description;
    private String productManufacturer;
    private Collection<OrderEntity> ordersByIdproduct;
    private CategoryEntity categoryByCategoryName;
    private SubcategoryEntity subcategoryBySubCategory;

    @Id
    @Column(name = "idproduct", nullable = false)
    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    @Basic
    @Column(name = "productName", nullable = false, length = 45)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "productPrice", nullable = false, precision = 0)
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 45)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "productManufacturer", nullable = true, length = 45)
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

        ProductsEntity that = (ProductsEntity) o;

        if (idproduct != that.idproduct) return false;
        if (Double.compare(that.productPrice, productPrice) != 0) return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (productManufacturer != null ? !productManufacturer.equals(that.productManufacturer) : that.productManufacturer != null)
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

    @OneToMany(mappedBy = "productsByProduct")
    public Collection<OrderEntity> getOrdersByIdproduct() {
        return ordersByIdproduct;
    }

    public void setOrdersByIdproduct(Collection<OrderEntity> ordersByIdproduct) {
        this.ordersByIdproduct = ordersByIdproduct;
    }

    @ManyToOne
    @JoinColumn(name = "categoryName", referencedColumnName = "idcategory", nullable = false)
    public CategoryEntity getCategoryByCategoryName() {
        return categoryByCategoryName;
    }

    public void setCategoryByCategoryName(CategoryEntity categoryByCategoryName) {
        this.categoryByCategoryName = categoryByCategoryName;
    }

    @ManyToOne
    @JoinColumn(name = "subCategory", referencedColumnName = "idsubCategory", nullable = false)
    public SubcategoryEntity getSubcategoryBySubCategory() {
        return subcategoryBySubCategory;
    }

    public void setSubcategoryBySubCategory(SubcategoryEntity subcategoryBySubCategory) {
        this.subcategoryBySubCategory = subcategoryBySubCategory;
    }
}
