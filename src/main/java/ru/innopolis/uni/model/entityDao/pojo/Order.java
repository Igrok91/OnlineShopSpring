package ru.innopolis.uni.model.entityDao.pojo;

/**
 * Created by innopolis on 20.01.2017.
 */
public class Order {
    private int idOrder;
    private String mobileNumber;
    private String address;

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

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

        Order order = (Order) o;

        if (idOrder != order.idOrder) return false;
        if (mobileNumber != null ? !mobileNumber.equals(order.mobileNumber) : order.mobileNumber != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOrder;
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
