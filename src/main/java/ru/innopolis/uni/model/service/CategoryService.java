package ru.innopolis.uni.model.service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.uni.model.dao.impl.ProductDaoImpl;
import ru.innopolis.uni.model.entityDao.pojo.Category;
import ru.innopolis.uni.model.entityDao.pojo.Subcategory;
import ru.innopolis.uni.model.entityDao.entityJPA.CategoryEntity;
import ru.innopolis.uni.model.entityDao.entityJPA.SubcategoryEntity;
import ru.innopolis.uni.model.repository.CategoryRepository;
import ru.innopolis.uni.model.repository.SubcategoryRepository;


import java.util.*;

/**
 * Created by Igor on 03.01.2017.
 * Класс определят сервис для получения данных по категориям из БД
 */
@Service
@Transactional
public class CategoryService {
    private static Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubcategoryRepository subcategoryRepository;
    @Autowired
    ProductDaoImpl productDao;

    private MapperFacade mapper;

    @Autowired
    public void setMapperFactory(MapperFactory mapperFactory) {
        this.mapper = mapperFactory.getMapperFacade();
    }
    public Map<String, List<Subcategory>> getCategoriesMap() {
        Map<String, List<Subcategory>> hashMap = new HashMap<>();
        Iterable<CategoryEntity> entities = categoryRepository.findAll();
        for (CategoryEntity category : entities) {
                Category cat = mapper.map(category, Category.class);
                Collection<SubcategoryEntity> list = subcategoryRepository.findByCategoryId(category.getIdcategory());
            Subcategory sub = null;
                List<Subcategory> categoryList = new ArrayList<>();
               for(SubcategoryEntity s : list) {
                    sub = mapper.map(s, Subcategory.class);
                    categoryList.add(sub);
                }
                hashMap.put(cat.getProductCategory(), categoryList);
        }
        return hashMap;
    }
}
