package com.example.productservice.restclients;

import com.example.productservice.model.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("GATEWAY-SERVICE")
public interface CouponClient {

    @GetMapping("/couponapi/coupons/{code}")
    Coupon getCoupon(@PathVariable("code") String code);


//    Coupon getCoupon(String couponCode);


//    Coupon getCoupon(@PathVariable("code") String code);
}
