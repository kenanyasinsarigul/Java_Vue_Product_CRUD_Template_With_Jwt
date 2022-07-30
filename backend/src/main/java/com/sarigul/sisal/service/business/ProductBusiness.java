package com.sarigul.sisal.service.business;

import com.sarigul.sisal.entity.Product;
import com.sarigul.sisal.repository.ProductJpaDao;
import com.sarigul.sisal.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductBusiness implements ProductService {

    private final ProductJpaDao dao;

    public ProductBusiness(ProductJpaDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Product> getAll() {
        return dao.findAll();
    }

    @Override
    public Product save(Product product) {
        return dao.save(product);
    }

    @Override
    public Product update(Product product) throws Exception{
        Optional pr=dao.findById(product.getId());
        if(!pr.isPresent()){
            throw new Exception("Böyle bir ürün yok!");
        }
        return dao.save(product);
    }

    @Override
    public void deleteById(Long id) throws Exception{
        Optional pr=dao.findById(id);
        if(!pr.isPresent()){
            throw new Exception("Böyle bir ürün yok!");
        }
        dao.deleteById(id);
    }
}
