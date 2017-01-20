package ru.innopolis.uni.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.innopolis.uni.model.entityDao.entityJPA.CategoryEntity;

import java.util.List;

/**
 * Created by innopolis on 19.01.2017.
 */
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {
            @Query("select c.idcategory, c.productCategory from CategoryEntity c")
            List<CategoryEntity> findAllCategory();
}
