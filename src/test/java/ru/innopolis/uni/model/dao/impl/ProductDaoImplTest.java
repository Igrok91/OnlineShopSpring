         package ru.innopolis.uni.model.dao.impl;

         import org.junit.Before;
         import org.junit.Test;
         import org.springframework.beans.factory.annotation.Autowired;
         import org.springframework.context.ApplicationContext;
         import org.springframework.context.support.ClassPathXmlApplicationContext;
         import org.springframework.stereotype.Component;
         import ru.innopolis.uni.model.dao.ProductDao;
         import ru.innopolis.uni.model.entityDao.pojo.Category;
         import ru.innopolis.uni.model.entityDao.pojo.Products;
         import ru.innopolis.uni.model.entityDao.pojo.Subcategory;

         import javax.persistence.Column;
         import java.util.List;

         import static org.junit.Assert.*;

/**
 * Created by innopolis on 28.12.2016.
 */

public class ProductDaoImplTest {

    private ProductDao productDao;

  /*@Before
    public void setUp() throws Exception {



        productDao = new ProductDaoImpl();

    }
    @Test
    public void getAllProducts() throws Exception {
        List<Products> productList = productDao.getAllProducts();
        assertNotNull(productList);

    }*/

   /* @Test
    public void getProductDetails() throws Exception {
        Products product = productDao.getProductDetails(1);
        assertNotNull(product);
        assertEquals(1,product.getIdproduct());
    }

 *//*   @Test
    public void getAllCategories() throws Exception {
        List<Category> list = productDao.getAllCategories();
        assertNotNull(list);
    }*//*


    @Test
    public void getProductBySubCategory() throws Exception {
        List<Products> productList = productDao.getProductBySubCategory("11");
        assertNotNull(productList);
    }

    @Test
    public void getProductByCategory() throws Exception {
        List<Products> productList = productDao.getProductBySubCategory("1");
        assertNotNull(productList);
    }

*/

}
