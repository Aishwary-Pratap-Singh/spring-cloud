package com.example.couponservice.controllers;

import com.example.couponservice.model.Coupon;
import com.example.couponservice.repos.CouponRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

    @Autowired
    CouponRepo repo;

    @RequestMapping(value = "/coupons", method = RequestMethod.POST)
    public Coupon create(@RequestBody Coupon coupon){
        return repo.save(coupon);
    }

    @RequestMapping(value = "/coupons/{code}",method = RequestMethod.GET)
    public Coupon getCoupon(@PathVariable("code") String code){
        return repo.findByCode(code);
    }

}
