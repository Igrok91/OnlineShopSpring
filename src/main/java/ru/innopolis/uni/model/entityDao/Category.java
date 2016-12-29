package ru.innopolis.uni.model.entityDao;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс определяет сущность из БД
 */
public class Category {
    private int categoryid;
    private String productCategory;

    public Category(int categoryid, String productCategory) {
        this.categoryid = categoryid;
        this.productCategory = productCategory;
    }

    public Category() {
    }

    public Category(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }


}
