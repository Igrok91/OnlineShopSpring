package ru.innopolis.uni.model.entityDao.entityJPA;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by innopolis on 19.01.2017.
 */
@Entity
@Table(name = "category", schema = "online_shop", catalog = "")
public class CategoryEntity {
    private int idcategory;
    private String productCategory;
    private Collection<ProductsEntity> productssByIdcategory;

    private Collection<SubcategoryEntity> subcategoriesByIdcategory;

    @Id
    @Column(name = "idcategory", nullable = false)
    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    @Basic
    @Column(name = "productCategory", nullable = false, length = 95)
    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (idcategory != that.idcategory) return false;
        if (productCategory != null ? !productCategory.equals(that.productCategory) : that.productCategory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcategory;
        result = 31 * result + (productCategory != null ? productCategory.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "categoryByCategoryName")
    public Collection<ProductsEntity> getProductssByIdcategory() {
        return productssByIdcategory;
    }

    public void setProductssByIdcategory(Collection<ProductsEntity> productssByIdcategory) {
        this.productssByIdcategory = productssByIdcategory;
    }

    @OneToMany(mappedBy = "categoryByCategoryId")
    public Collection<SubcategoryEntity> getSubcategoriesByIdcategory() {
        return subcategoriesByIdcategory;
    }

    public void setSubcategoriesByIdcategory(Collection<SubcategoryEntity> subcategoriesByIdcategory) {
        this.subcategoriesByIdcategory = subcategoriesByIdcategory;
    }
}
