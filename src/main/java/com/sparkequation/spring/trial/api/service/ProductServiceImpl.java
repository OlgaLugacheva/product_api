package com.sparkequation.spring.trial.api.service;

import com.sparkequation.spring.trial.api.model.Product;
import com.sparkequation.spring.trial.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> GetProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product GetProduct(String id) {
        return productRepository.findById(Integer.parseInt(id)).orElseThrow(() -> new EntityNotFoundException());
    }

    @Override
    public Product SaveProduct(Product product) {

        if (product.getRating() > 8.0) {
            product.setFeatured(true);
        }
        return  productRepository.saveAndFlush(product);
    }

    @Override
    public void DeleteProduct(String id) {
        productRepository.findById(Integer.parseInt(id)).ifPresent(productRepository::delete);
    }

    private void validate(boolean cond, String err) throws Exception {
        if (!cond) {
            throw new Exception(err);
        }
    }
}
