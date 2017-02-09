package ru.innopolis.uni.model.repository;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.uni.model.entityDao.entityJPA.UserRolesEntity;

/**
 * Created by innopolis on 21.01.2017.
 */
public interface UserRolesRepository extends CrudRepository<UserRolesEntity, Integer> {
}
