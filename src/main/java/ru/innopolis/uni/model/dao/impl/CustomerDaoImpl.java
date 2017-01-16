package ru.innopolis.uni.model.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.database.DBConnection;
import ru.innopolis.uni.model.dao.CustomerDao;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс, определяющий основные методы для взаимодействия с базой данных
 */

public class CustomerDaoImpl implements CustomerDao {
    private static Logger log = LoggerFactory.getLogger(CustomerDaoImpl.class);
    private Connection conn;



    /**\
     *
     * @param email Данные пользователя
     * @param password  Данные пользователя
     * @return <tt>true</tt> Если данные пользователя помещены в БД
     * @throws DataBaseException
     */
    @Override
    public boolean registerCustomer(String email, String password) throws DataBaseException{
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnecton();
            String sql = "insert into user(password, email) values(?,?)";
            String sqlS = "insert into users(username, password) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(2, email);
            ps.setString(1, password);


            int result = ps.executeUpdate();
            ps = conn.prepareStatement(sqlS);
            ps.setString(2, password);
            ps.setString(1, email);
            int resultS = ps.executeUpdate();
            if (result > 0 && resultS > 0) {
                return true;
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new DataBaseException();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.warn(e.getMessage());
                }
            }

        }
        return false;
    }


    /**
     *  This method is used to verify if the customer is registered or not
     * @param email Данные пользователя
     * @param password Данные пользователя
     * @return <tt>true</tt> Если данные пользователя совпадают с значением в БД
     * @throws DataBaseException
     */
    @Override
    public boolean verifyUser(String email, String password)  throws DataBaseException{
        conn = DBConnection.getConnecton();
        PreparedStatement ps = null;
        String sql = "select iduser from user where email=? AND password=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString("iduser") != null) {
                    return true;
                }
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new DataBaseException();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    log.warn(e.getMessage());
                }
            }

        }
        return false;
    }


}
