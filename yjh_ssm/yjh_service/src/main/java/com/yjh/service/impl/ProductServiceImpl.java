package com.yjh.service.impl;

import com.yjh.dao.ProductDao;
import com.yjh.pojo.Product;
import com.yjh.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> findAll() throws Exception{
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception{
        productDao.save(product);
    }
}
