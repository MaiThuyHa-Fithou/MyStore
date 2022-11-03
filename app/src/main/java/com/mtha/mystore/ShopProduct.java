package com.mtha.mystore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopProduct {
    MutableLiveData<List<Product>> mutableProductList = new MutableLiveData<>();
    public LiveData<List<Product>> getProducts(){
        if (mutableProductList.getValue()==null){
        //    mutableProductList = new MutableLiveData<>();
            //load product list
            loadProducts();
        }
        return mutableProductList;
    }

    private void loadProducts(){
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(UUID.randomUUID().toString(), "iMac 21", 1299,  "https://bucket.nhanh.vn/store1/57746/ps/20191231/3141201964152_4k%20-19.jpg" ));
        productList.add(new Product(UUID.randomUUID().toString(), "iPad Air", 799,  "https://bucket.nhanh.vn/store1/57746/ps/20200121/apple-10-5inch-ipad-air.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPad Pro", 999,  "https://bucket.nhanh.vn/store1/57746/album/9959/ipadpro-11inch-2021_thumb.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPhone 13", 699,  "https://bucket.nhanh.vn/store1/57746/album/9896/iphone13-2021_thumb.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPhone 13 Pro", 999,  "https://bucket.nhanh.vn/store1/57746/album/9896/iphone13-pro-2021_thumb.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "iPhone 13 Pro Max", 1099,  "https://bucket.nhanh.vn/store1/57746/album/9896/iphone13-promax-2021_thumb.jpg"));
        productList.add(new Product(UUID.randomUUID().toString(), "MacBook Air", 999,  "https://bucket.nhanh.vn/store1/57746/ps/20201125/MGN63-Macbook-Air-2020-M1-Gray-MacSG1.jpg"));
        mutableProductList.setValue(productList);
    }
}
