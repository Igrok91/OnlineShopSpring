package ru.innopolis.uni.model.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innopolis.uni.model.dao.CustomerDao;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.entityJPA.UserEntity;
import ru.innopolis.uni.model.entityDao.entityJPA.UserRolesEntity;
import ru.innopolis.uni.model.entityDao.entityJPA.UsersEntity;
import ru.innopolis.uni.model.repository.UserRepository;
import ru.innopolis.uni.model.repository.UserRolesRepository;
import ru.innopolis.uni.model.repository.UsersRepository;

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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    private UsersRepository usersRepository;


    /**\
     *
     * @param email Данные пользователя
     * @param password  Данные пользователя
     * @return <tt>true</tt> Если данные пользователя помещены в БД
     * @throws DataBaseException
     */
    @Override
    public boolean registerCustomer(String email, String password) throws DataBaseException{
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUsername(email);
        usersEntity.setPassword(password);
        usersEntity.setEnabled((byte) 1);
        UserRolesEntity entity = new UserRolesEntity();
        entity.setRole("ROLE_USER");
        entity.setUsersEntity(usersEntity);
       if ( userRepository.save(userEntity) != null && usersRepository.save(usersEntity) != null
               && userRolesRepository.save(entity) != null) {
           return true;
       }
       return false;

    }

}
