package ru.innopolis.uni.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innopolis.uni.model.dao.ProductDao;
import ru.innopolis.uni.model.dao.daoException.DataBaseException;
import ru.innopolis.uni.model.dao.impl.ProductDaoImpl;
import ru.innopolis.uni.model.entityDao.Category;
import ru.innopolis.uni.model.entityDao.SubCategory;

import javax.servlet.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Igor Ryabtsev on 28.12.2016.
 * Класс фильтра, который фильтрует подкатегории в соответствии с категориями
 */

public class ProductConfigurationFilter implements Filter {

	ProductDaoImpl service;
	private static Logger log = LoggerFactory.getLogger(ProductConfigurationFilter.class);
	private ServletContext context;

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("Configuring Products and Categories");
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		 HashMap<String, List<SubCategory>> hashMap=new HashMap<>(1);


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

			this.context.log("Category Names available are:"+category.getProductCategory());
		}

		this.context.setAttribute("categories", hashMap);
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
	@Override
	public void destroy() {
	}

}
