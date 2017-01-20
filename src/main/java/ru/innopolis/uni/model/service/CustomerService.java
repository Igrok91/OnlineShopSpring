package ru.innopolis.uni.model.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.model.dao.CustomerDao;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.dao.impl.CustomerDaoImpl;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс определят сервис для получения данных из БД и вычисления Бизнес логики
 */
@Service
public class CustomerService implements CustomerDao {
    @Autowired
    private CustomerDaoImpl customerDao;
   // @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public CustomerService(){

    }
    @Override
    public boolean registerCustomer(String email, String password)  throws DataBaseException {

        return customerDao.registerCustomer(email,bcryptEncoder.encode(password));
    }

    @Override
    public boolean verifyUser(String email, String password) throws DataBaseException {
        return customerDao.verifyUser(email,bcryptEncoder.encode(password));
    }

    private String crypt(String password) {
        String md5Hex = DigestUtils.md5Hex(password);
        return  md5Hex;
    }

}
