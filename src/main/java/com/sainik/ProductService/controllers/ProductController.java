package com.sainik.ProductService.controllers;

import com.sainik.ProductService.model.ProductRequest;
import com.sainik.ProductService.model.ProductResponse;
import com.sainik.ProductService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService prodService;
    @PostMapping
    public ResponseEntity<Long>  addProducts(@RequestBody ProductRequest productRequest){
        long productId = prodService.addProduct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long productId){
        ProductResponse productResponse = prodService.getProductById(productId);

        return new ResponseEntity<>(productResponse,HttpStatus.OK);

    }

}
