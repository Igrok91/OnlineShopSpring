package ru.innopolis.uni.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.model.dao.ProductDao;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.dao.impl.ProductDaoImpl;
import ru.innopolis.uni.model.entityDao.Category;
import ru.innopolis.uni.model.entityDao.Product;
import ru.innopolis.uni.model.entityDao.SubCategory;

import java.util.List;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс определят сервис для получения данных из БД и вычисления Бизнес логики
 */
@Service
public class ProductService  implements ProductDao{
    @Autowired
    private ProductDaoImpl productDao;
    public ProductService() {

    }
    @Override
    public List<Product> getAllProducts() throws DataBaseException {
        return productDao.getAllProducts();
    }

    @Override
    public Product getProductDetails(int idproduct) throws DataBaseException {
        return productDao.getProductDetails(idproduct);
    }

    @Override
    public List<Category> getAllCategories() throws DataBaseException {
        return productDao.getAllCategories();
    }

    @Override
    public List<SubCategory> getSubCategory(Category category) throws DataBaseException {
        return productDao.getSubCategory(category);
    }

    @Override
    public List<Product> getProductBySubCategory(String subCategory) throws DataBaseException {
        return productDao.getProductBySubCategory(subCategory);
    }

    @Override
    public List<Product> getProductByCategory(String category) throws DataBaseException {
        return productDao.getProductByCategory(category);
    }

    @Override
    public String getCategoryBySubCategory(String subCategory) throws DataBaseException {
        return productDao.getCategoryBySubCategory(subCategory);
    }
}
