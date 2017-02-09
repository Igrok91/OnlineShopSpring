package ru.innopolis.uni.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.uni.model.dao.ProductDao;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.dao.impl.ProductDaoImpl;
import ru.innopolis.uni.model.entityDao.pojo.Category;
import ru.innopolis.uni.model.entityDao.pojo.Products;
import ru.innopolis.uni.model.entityDao.pojo.Subcategory;

import java.util.List;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс определят сервис для получения данных из БД и вычисления Бизнес логики
 */
@Service
@Transactional
public class ProductService  implements ProductDao{

    @Autowired
    private ProductDaoImpl productDao;


    public ProductService() {

    }
    @Override
    public List<Products> getAllProducts() throws DataBaseException {

        return productDao.getAllProducts();
    }

    @Override
    public Products getProductDetails(int idproduct) throws DataBaseException {

        return productDao.getProductDetails(idproduct);
    }


    @Override
    public List<Products> getProductBySubCategory(String subCategory) throws DataBaseException {
        return productDao.getProductBySubCategory(subCategory);
    }

    public String getCategoryBySubCategory(String subCategory) throws DataBaseException {
        return productDao.getCategoryBySubCategory(subCategory);
    }
}
