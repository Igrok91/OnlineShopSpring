package ru.innopolis.uni.model.entityDao.pojo;

/**
 * Created by innopolis on 20.01.2017.
 */
public class Subcategory {
    private int idsubCategory;
    private String subCategoryName;
    private Category categoryByCategoryId;

    public int getIdsubCategory() {
        return idsubCategory;
    }

    public void setIdsubCategory(int idsubCategory) {
        this.idsubCategory = idsubCategory;
    }

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

        Subcategory that = (Subcategory) o;

        if (idsubCategory != that.idsubCategory) return false;
        if (subCategoryName != null ? !subCategoryName.equals(that.subCategoryName) : that.subCategoryName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idsubCategory;
        result = 31 * result + (subCategoryName != null ? subCategoryName.hashCode() : 0);
        return result;
    }

    public Category getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(Category categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }
}
