package ru.innopolis.uni.model.entityDao.entityJPA;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by innopolis on 19.01.2017.
 */
@Entity
@Table(name = "subcategory", schema = "online_shop", catalog = "")
public class SubcategoryEntity {
    private int idsubCategory;
    private String subCategoryName;
    private Collection<ProductsEntity> productssByIdsubCategory;
    private CategoryEntity categoryByCategoryId;

    @Id
    @Column(name = "idsubCategory", nullable = false)
    public int getIdsubCategory() {
        return idsubCategory;
    }

    public void setIdsubCategory(int idsubCategory) {
        this.idsubCategory = idsubCategory;
    }

    @Basic
    @Column(name = "subCategoryName", nullable = true, length = 95)
    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubcategoryEntity entity = (SubcategoryEntity) o;

        if (idsubCategory != entity.idsubCategory) return false;
        if (subCategoryName != null ? !subCategoryName.equals(entity.subCategoryName) : entity.subCategoryName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idsubCategory;
        result = 31 * result + (subCategoryName != null ? subCategoryName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "subcategoryBySubCategory")
    public Collection<ProductsEntity> getProductssByIdsubCategory() {
        return productssByIdsubCategory;
    }

    public void setProductssByIdsubCategory(Collection<ProductsEntity> productssByIdsubCategory) {
        this.productssByIdsubCategory = productssByIdsubCategory;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "idcategory")
    public CategoryEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(CategoryEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }
}
