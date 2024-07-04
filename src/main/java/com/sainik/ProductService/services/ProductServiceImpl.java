package com.sainik.ProductService.services;

import com.sainik.ProductService.entities.Product;
import com.sainik.ProductService.model.ProductRequest;
import com.sainik.ProductService.model.ProductResponse;
import com.sainik.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository prodRepo;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding product");
        Product product = Product.builder().productName(productRequest.getProductName()).price(productRequest.getPrice()).quantity(productRequest.getQuantity()).build();
        prodRepo.save(product);
        log.info("Saved Product to db");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Retrieving Products");
        Product product  = prodRepo.findById(productId).orElseThrow(()->new RuntimeException("Product with id is not found"));
        ProductResponse productResponse = new ProductResponse();

        BeanUtils.copyProperties(product,productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("start of reducing quantity");
        Product product = prodRepo.findById(productId).orElseThrow(()->new RuntimeException("Product is not found"));

        if(product.getQuantity()<quantity){
            throw new RuntimeException("Please check the quantity again for reducing as it is greater than the quantity of the product");
        }

        product.setQuantity(product.getQuantity()-quantity);
        prodRepo.save(product);
        log.info("product quantity updated successfully");
    }
}
