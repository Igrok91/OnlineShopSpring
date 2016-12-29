package ru.innopolis.uni.model.dao;

import ru.innopolis.uni.model.dao.daoException.DataBaseException;

/**
 * Created by  Igor Ryabtsev on 28.12.2016.
 * Интерфейс определяет методы ригистрации и проверки пользователя
 */
public interface CustomerDao {
    /**
     *This method is used to register the customer
     * @param email Данные пользователя
     * @param password  Данные пользователя
     * @return <tt>true</tt> Если данные пользователя помещены в БД
     * @throws DataBaseException
     */
    boolean registerCustomer(String email, String password)  throws DataBaseException;

    /**
     *  This method is used to verify if the customer is registered or not
     * @param email Данные пользователя
     * @param password Данные пользователя
     * @return <tt>true</tt> Если данные пользователя совпадают с значением в БД
     * @throws DataBaseException
     */
    boolean verifyUser(String email, String password)  throws DataBaseException;

}
