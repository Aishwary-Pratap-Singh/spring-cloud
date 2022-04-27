package com.example.productservice.controller;

import com.example.productservice.model.Coupon;
import com.example.productservice.model.Product;
import com.example.productservice.repo.ProductRepo;
import com.example.productservice.restclients.CouponClient;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productapi")
public class ProductRestController {

    @Autowired
    CouponClient couponClient;
    @Autowired
    private ProductRepo repo;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @Retry(name="product-api", fallbackMethod = "handleError")
    public Product create(@RequestBody Product product){
        Coupon coupon = couponClient.getCoupon(product.getCouponCode());
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return repo.save(product);
    }

    public Product handleError(Product product, Exception exception){
        System.out.println("Inside handle Error");
        return product;
    }

}
