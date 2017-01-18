package ru.innopolis.uni.model.entityDao.entityJPA;

import javax.persistence.*;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс определяет сущность из БД
 */
@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "findAllCategory", query = "select e from ECategory e")
})
public class ECategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idcategory;

    private String productCategory;

    public ECategory(int categoryid, String productCategory) {
        this.idcategory = categoryid;
        this.productCategory = productCategory;
    }

    public ECategory() {
    }

    public ECategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int categoryid) {
        this.idcategory = categoryid;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }


}
