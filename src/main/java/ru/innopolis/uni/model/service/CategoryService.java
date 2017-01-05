package ru.innopolis.uni.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.entityDao.Category;
import ru.innopolis.uni.model.entityDao.SubCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Igor on 03.01.2017.
 * Класс определят сервис для получения данных по категориям из БД
 */
@Service
public class CategoryService {
    private static Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    ProductService service;
    public Map<String, List<SubCategory>> getCategoriesMap() {
        Map<String, List<SubCategory>> hashMap = new HashMap<>();

        List<Category> categoryList = null;

        try {
            categoryList = service.getAllCategories();
        } catch (DataBaseException e) {
            log.warn(e.message());
        }

        for (Category category : categoryList) {

            try {
                hashMap.put(category.getProductCategory(), service.getSubCategory(category));

            } catch (DataBaseException e) {
                log.warn(e.message());
            }

        }
        return hashMap;
    }
}
