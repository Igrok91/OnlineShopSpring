package ru.innopolis.uni.model.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.innopolis.uni.model.entityDao.entityJPA.ProductsEntity;
import ru.innopolis.uni.model.entityDao.pojo.Products;

import java.util.List;

/**
 * Created by innopolis on 19.01.2017.
 */
public interface ProductsRepository extends CrudRepository<ProductsEntity, Integer> {
 //@Query("select b.idproduct, b.description,b.productManufacturer,b.productPrice, b.productName from  ProductsEntity b where b.idproduct = :id")
    ProductsEntity findByIdproduct( int id);

    List<ProductsEntity> findBySubcategoryBySubCategory_IdsubCategory(int categoryName);
}
