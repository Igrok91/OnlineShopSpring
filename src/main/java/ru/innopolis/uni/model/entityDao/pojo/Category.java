package ru.innopolis.uni.model.entityDao.pojo;

/**
 * Created by innopolis on 20.01.2017.
 */
public class Category {
    private int idcategory;
    private String productCategory;

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

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

        Category category = (Category) o;

        if (idcategory != category.idcategory) return false;
        if (productCategory != null ? !productCategory.equals(category.productCategory) : category.productCategory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcategory;
        result = 31 * result + (productCategory != null ? productCategory.hashCode() : 0);
        return result;
    }
}
