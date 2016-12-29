package ru.innopolis.uni.model.dao;

import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.Category;
import ru.innopolis.uni.model.entityDao.Product;
import ru.innopolis.uni.model.entityDao.SubCategory;

import java.util.List;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Интерфейс определяет основные методы полученя данных из БД
 */
public interface ProductDao {
    /**
     *   Method to get all products available
     * @return List of product
     * @throws DataBaseException
     */
    List<Product> getAllProducts() throws DataBaseException;

    /**
     *  Method to get the required Product Details
     * @param idproduct
     * @return Product
     * @throws DataBaseException
     */
    Product getProductDetails(int idproduct) throws DataBaseException;


    /**
     * Method to get all the available Categories
     * @return List of category
     * @throws DataBaseException
     */
    List<Category> getAllCategories() throws DataBaseException;


    /**
     * Method to get all the available Subcategories under a Category
     * @param category
     * @return List of subcategory
     * @throws DataBaseException
     */
    List<SubCategory> getSubCategory(Category category) throws DataBaseException;

    /**
     * Method to get all the Products based on specified SubCategory
     * @param subCategory
     * @return
     * @throws DataBaseException
     */
    List<Product> getProductBySubCategory(String subCategory) throws DataBaseException;

    /**
     * Method to get all the Products based on specified Category
     * @param category
     * @return
     * @throws DataBaseException
     */
    List<Product> getProductByCategory(String category) throws DataBaseException;

    /**
     * Method to get Product Category
     * based on Sub Category
     * @param subCategory
     * @return Name of Category
     * @throws DataBaseException
     */
    String getCategoryBySubCategory(String subCategory) throws DataBaseException;

}
