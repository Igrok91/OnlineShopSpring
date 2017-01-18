package ru.innopolis.uni.model.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.innopolis.uni.database.DBConnection;
import ru.innopolis.uni.model.dao.ProductDao;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.Category;
import ru.innopolis.uni.model.entityDao.Product;
import ru.innopolis.uni.model.entityDao.SubCategory;
import ru.innopolis.uni.model.entityDao.entityJPA.ECategory;

import javax.persistence.*;
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
    private List<Product> products = null;
    private List<Category> categories = null;
    private List<SubCategory> subCategories = null;
    private String categoryName;

    private static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("persistenceUnit");


    /**
     *   Method to get all products available
     * @return List of product
     * @throws DataBaseException
     */
    @Override
    public List<Product> getAllProducts()  throws DataBaseException {
        Connection conn = null;
        Statement ps = null;
        ResultSet rs = null;
        String sql;
        conn = DBConnection.getConnecton();
        System.out.println(conn == null);
        sql = "select * from products";
        products = new ArrayList<Product>();

        try {
            ps = conn.createStatement();
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                SubCategory subCategory = new SubCategory();
                subCategory.setProductCategory( rs.getString("subCategory"));

                Product p = new Product(rs.getInt("idproduct"),
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("description"),
                        rs.getString("productManufacturer"));
                products.add(p);
            }

        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new DataBaseException();
        } finally {

                try {
                    if (rs != null ) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException e) {
                    log.warn(e.getMessage());
                }
            }
        return products;
    }



    /**
     *  Method to get the required Product Details
     * @param idproduct
     * @return Product
     * @throws DataBaseException
     */
    @Override
    public Product getProductDetails(int idproduct)  throws DataBaseException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        conn = DBConnection.getConnecton();
        Product p = new Product();
        ru.innopolis.uni.model.entityDao.Category cat = new ru.innopolis.uni.model.entityDao.Category();
        SubCategory sub = new SubCategory();
        sql = "select productName,productPrice,description,categoryName,subCategory," +
                "productManufacturer from products where idproduct=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idproduct);
            rs = ps.executeQuery();
            while (rs.next()) {
                cat.setCategoryid(rs.getInt(4));
                sub.setCategoryid(rs.getInt(5));
                p.setProductId(idproduct);
                p.setProductName(rs.getString(1));
                p.setProductPrice(rs.getDouble(2));
                p.setDescription(rs.getString(3));
                p.setCategoryName(cat);
                p.setSubCategory(sub);
                p.setProductManufacturer(rs.getString(6));
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new DataBaseException();
        } finally {
            try {
                if (rs != null ) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                log.warn(e.getMessage());
            }

        }
        return p;
    }

    //

    /**
     * Method to get all the available Categories
     * @return List of category
     * @throws DataBaseException
     */
    @Override
    public List<Category> getAllCategories()  throws DataBaseException {

        EntityManager em = emf.createEntityManager();
        String sql = "select * from category";
        List<ECategory> list = em.createNamedQuery("findAllCategory",ECategory.class).getResultList();

        categories = new ArrayList<Category>();
       for (ECategory e : list) {

            Category c = new Category(e.getProductCategory());
            c.setCategoryid(e.getIdcategory());
            categories.add(c);
        }
       /* Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        conn = DBConnection.getConnecton();
        sql = "select idcategory, productCategory from category";
        categories = new ArrayList<ECategory>();

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ECategory c = new ECategory(rs.getString("productCategory"));
                c.setCategoryid(rs.getInt("idcategory"));
                categories.add(c);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new DataBaseException();
        } finally {
            try {
                if (rs != null ) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                log.warn(e.getMessage());
            }
        }*/
        return categories;
    }

    //

    /**
     * Method to get all the available Subcategories under a ECategory
     * @param category
     * @return List of subcategory
     * @throws DataBaseException
     */
    @Override
    public List<SubCategory> getSubCategory(ru.innopolis.uni.model.entityDao.Category category) throws DataBaseException  {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        conn = DBConnection.getConnecton();

        sql = "SELECT idsubCategory,subCategoryName FROM subcategory  where category_id = ? ";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, category.getCategoryid());
            rs = ps.executeQuery();
            subCategories = new ArrayList<>();
            while (rs.next()) {
                SubCategory subCategory = new SubCategory();
                subCategory.setProductCategory(rs.getString("subCategoryName"));
                subCategory.setCategoryid(rs.getInt("idsubCategory"));
                subCategories.add(subCategory);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new DataBaseException();
        } finally {

            try {
                if (rs != null ) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                log.warn(e.getMessage());
            }
        }
        return subCategories;
    }

    //

    /**
     * Method to get all the Products based on specified SubCategory
     * @param subCategory
     * @return
     * @throws DataBaseException
     */
    @Override
    public List<Product> getProductBySubCategory(String subCategory)   throws DataBaseException{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        conn = DBConnection.getConnecton();
        sql = "select idproduct, productName,productPrice,description,categoryName," +
                "productManufacturer from products where subCategory=?";
        products = new ArrayList<Product>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(subCategory));
            rs = ps.executeQuery();
            while (rs.next()) {
                SubCategory cat = new SubCategory();
                cat.setProductCategory(rs.getString(5));
                Product p = new Product(rs.getInt(1), rs.getString(2),
                        rs.getDouble(3), rs.getString(4),cat ,
                        rs.getString(6));
                products.add(p);
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new DataBaseException();
        } finally {

            try {
                if (rs != null ) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                log.warn(e.getMessage());
            }
        }
        return products;
    }

    /**
     * Method to get all the Products based on specified ECategory
     * @param category
     * @return
     * @throws DataBaseException
     */
    @Override
    public List<Product> getProductByCategory(String category)  throws DataBaseException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql;
        conn = DBConnection.getConnecton();
        sql = "select idproduct, productName,productPrice,description,categoryName," +
                "productManufacturer from products where categoryName=?";
        products = new ArrayList<Product>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            rs = ps.executeQuery();
            while (rs.next()) {
                ru.innopolis.uni.model.entityDao.Category cat = new ru.innopolis.uni.model.entityDao.Category();
                cat.setProductCategory(rs.getString(5));
                Product p = new Product(rs.getInt(1), rs.getString(2),
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
        }
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
        Connection conn = null;
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

        }
        return categoryName;
    }

}
