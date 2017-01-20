package ru.innopolis.uni.model.dao;

import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.pojo.Category;
import ru.innopolis.uni.model.entityDao.pojo.Products;
import ru.innopolis.uni.model.entityDao.pojo.Subcategory;

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
    List<Products> getAllProducts() throws DataBaseException;

    /**
     *  Method to get the required Product Details
     * @param idproduct
     * @return Product
     * @throws DataBaseException
     */
    Products getProductDetails(int idproduct) throws DataBaseException;


    /**
     * Method to get all the available Categories
     * @return List of category
     * @throws DataBaseException
     */
    List<Category> getAllCategories() throws DataBaseException;


    /**
     * Method to get all the available Subcategories under a ECategory
     * @param category
     * @return List of subcategory
     * @throws DataBaseException
     */
    List<Subcategory> getSubCategory(Category category) throws DataBaseException;

    /**
     * Method to get all the Products based on specified SubCategory
     * @param subCategory
     * @return
     * @throws DataBaseException
     */
    List<Products> getProductBySubCategory(String subCategory) throws DataBaseException;

    /**
     * Method to get all the Products based on specified ECategory
     * @param category
     * @return
     * @throws DataBaseException
     */
    List<Products> getProductByCategory(String category) throws DataBaseException;

    /**
     * Method to get Product ECategory
     * based on Sub ECategory
     * @param subCategory
     * @return Name of ECategory
     * @throws DataBaseException
     */
    String getCategoryBySubCategory(String subCategory) throws DataBaseException;

}
