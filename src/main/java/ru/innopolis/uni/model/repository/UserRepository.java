package ru.innopolis.uni.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.uni.model.entityDao.entityJPA.UserEntity;

/**
 * Created by innopolis on 19.01.2017.
 */
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
}
