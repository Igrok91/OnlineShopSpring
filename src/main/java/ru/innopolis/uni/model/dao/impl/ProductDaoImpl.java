package ru.innopolis.uni.model.dao.impl;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import ru.innopolis.uni.database.DBConnection;
import ru.innopolis.uni.model.dao.ProductDao;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.pojo.Category;
import ru.innopolis.uni.model.entityDao.pojo.Products;
import ru.innopolis.uni.model.entityDao.pojo.Subcategory;
import ru.innopolis.uni.model.entityDao.entityJPA.ProductsEntity;
import ru.innopolis.uni.model.repository.ProductsRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс, определяющий методы для получения данных из БД, о
 * пределяющие содержимое пользовательского интерфейса
 */


public class ProductDaoImpl implements ProductDao {
    private static Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);
    private List<Products> products = null;
    private List<Category> categories = null;
    private List<Subcategory> subCategories = null;
    private String categoryName;

    @Autowired
    private ProductsRepository productsRepository;

    private MapperFacade mapperFactory;
    @Autowired
    public void setMapperFactory(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory.getMapperFacade();
    }


    /**
     *   Method to get all products available
     * @return List of product
     * @throws DataBaseException
     */
    @Override
    public List<Products> getAllProducts()  throws DataBaseException {
        Iterable<ProductsEntity> productsEntities = productsRepository.findAll();
        List<Products> list = new ArrayList<>();
        for (ProductsEntity p : productsEntities){
            Products product = mapperFactory.map(p, Products.class);
            System.out.println(product.getIdproduct());
            list.add(product);
        }
        return list;
    }



    /**
     *  Method to get the required Product Details
     * @param idproduct
     * @return Product
     * @throws DataBaseException
     */
    @Override
    public Products getProductDetails(int idproduct)  throws DataBaseException {
        ProductsEntity productsEntity = productsRepository.findByIdproduct(idproduct);
        Products product = mapperFactory.map(productsEntity, Products.class);
        return product;
    }

    //

    /**
     * Method to get all the available Categories
     * @return List of category
     * @throws DataBaseException
     */
    @Override
    public List<Category> getAllCategories()  throws DataBaseException {
        return null;
    }

    //

    /**
     * Method to get all the available Subcategories under a ECategory
     * @param category
     * @return List of subcategory
     * @throws DataBaseException
     */
    @Override
    public List<Subcategory> getSubCategory(Category category) throws DataBaseException  {

        return null;
    }

    //

    /**
     * Method to get all the Products based on specified SubCategory
     * @param subCategory
     * @return
     * @throws DataBaseException
     */
    @Override
    public List<Products> getProductBySubCategory(String subCategory)   throws DataBaseException{

       List<ProductsEntity> list = productsRepository.findBySubcategoryBySubCategory_IdsubCategory(Integer.parseInt(subCategory));
        List<Products> productsList = new ArrayList<>();
        for(ProductsEntity p : list) {
            Products products = mapperFactory.map(p, Products.class);
            productsList.add(products);
        }
        return productsList;
    }

    /**
     * Method to get all the Products based on specified ECategory
     * @param category
     * @return
     * @throws DataBaseException
     */
    @Override
    public List<Products> getProductByCategory(String category)  throws DataBaseException {


       /* Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        conn = DBConnection.getConnecton();
        sql = "select idproduct, productName,productPrice,description,categoryName," +
                "productManufacturer from products where categoryName=?";
        products = new ArrayList<Products>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category cat = new Category();
                cat.setProductCategory(rs.getString(5));
                Products p = new Products(rs.getInt(1), rs.getString(2),
                        rs.getDouble(3), rs.getString(4),cat ,
                        rs.getString(6));
                products.add(p);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new DataBaseException();
        } finally {
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                        log.warn(e.getMessage());
                    }
                }
        }*/
        return products;
    }




    /**
     * Method to get Product ECategory
     * based on Sub ECategory
     * @param subCategory
     * @return Name of ECategory
     * @throws DataBaseException
     */
    @Override
    public String getCategoryBySubCategory(String subCategory)  throws DataBaseException{
     /*   Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        conn = DBConnection.getConnecton();
        sql = "select category_id from subcategory where subCategoryName=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, subCategory);
            rs = ps.executeQuery();
            while (rs.next()) {
                categoryName = rs.getString("category_id");
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new DataBaseException();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
                log.warn(e.getMessage());
            }

        }*/
        return categoryName;
    }

}
