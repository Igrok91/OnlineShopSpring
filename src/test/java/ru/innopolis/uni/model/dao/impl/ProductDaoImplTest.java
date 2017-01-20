         package ru.innopolis.uni.model.dao.impl;

         import org.junit.Before;
         import org.junit.Test;
         import ru.innopolis.uni.model.dao.ProductDao;

         import java.util.List;

         import static org.junit.Assert.*;

/**
 * Created by innopolis on 28.12.2016.
 */
public class ProductDaoImplTest {
    private ProductDao productDao;

    @Before
    public void setUp() throws Exception {

        productDao = new ProductDaoImpl();

    }

    @Test
    public void getAllProducts() throws Exception {
        List<Product> productList = productDao.getAllProducts();
        assertNotNull(productList);

    }

    @Test
    public void getProductDetails() throws Exception {
        Product product = productDao.getProductDetails(1);
        assertNotNull(product);
        assertEquals(1,product.getProductId());
    }

 /*   @Test
    public void getAllCategories() throws Exception {
        List<Category> list = productDao.getAllCategories();
        assertNotNull(list);
    }*/

    @Test
    public void getSubCategory() throws Exception {
        Category category = new Category();
        category.setCategoryid(1);
        category.setProductCategory("Бакалея");
        List<SubCategory> list = productDao.getSubCategory(category);
        assertNotNull(list);

    }

    @Test
    public void getProductBySubCategory() throws Exception {
        List<Product> productList = productDao.getProductBySubCategory("11");
        assertNotNull(productList);
    }

    @Test
    public void getProductByCategory() throws Exception {
        List<Product> productList = productDao.getProductByCategory("1");
        assertNotNull(productList);
    }



}
