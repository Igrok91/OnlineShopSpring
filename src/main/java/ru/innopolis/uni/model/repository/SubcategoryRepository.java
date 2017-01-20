package ru.innopolis.uni.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.innopolis.uni.model.entityDao.entityJPA.CategoryEntity;
import ru.innopolis.uni.model.entityDao.entityJPA.SubcategoryEntity;

import java.util.List;

/**
 * Created by innopolis on 19.01.2017.
 */
public interface SubcategoryRepository extends CrudRepository<SubcategoryEntity, Integer> {
        @Query("select b from SubcategoryEntity b where b.categoryByCategoryId.idcategory = :category" )
         List<SubcategoryEntity> findByCategoryId(@Param("category") int category);
}
