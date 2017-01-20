package ru.innopolis.uni.model.entityDao.entityJPA;

import javax.persistence.*;

/**
 * Created by innopolis on 19.01.2017.
 */
@Entity
@Table(name = "order", schema = "online_shop", catalog = "")
public class OrderEntity {
    private int idOrder;
    private String mobileNumber;
    private String address;
    private UserEntity userByUserName;
    private ProductsEntity productsByProduct;

    @Id
    @Column(name = "idOrder", nullable = false)
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Basic
    @Column(name = "mobileNumber", nullable = false, length = 45)
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 45)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (idOrder != that.idOrder) return false;
        if (mobileNumber != null ? !mobileNumber.equals(that.mobileNumber) : that.mobileNumber != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOrder;
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userName", referencedColumnName = "iduser", nullable = false)
    public UserEntity getUserByUserName() {
        return userByUserName;
    }

    public void setUserByUserName(UserEntity userByUserName) {
        this.userByUserName = userByUserName;
    }

    @ManyToOne
    @JoinColumn(name = "product", referencedColumnName = "idproduct", nullable = false)
    public ProductsEntity getProductsByProduct() {
        return productsByProduct;
    }

    public void setProductsByProduct(ProductsEntity productsByProduct) {
        this.productsByProduct = productsByProduct;
    }
}
