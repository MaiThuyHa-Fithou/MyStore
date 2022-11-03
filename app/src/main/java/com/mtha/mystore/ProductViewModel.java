package com.mtha.mystore;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ProductViewModel extends ViewModel {
     MutableLiveData<Product> mutableProduct = new MutableLiveData<>();
     ShoppingCart shoppingCart = new ShoppingCart();
     ShopProduct shopProduct = new ShopProduct();
     public LiveData<List<Product>> getProducts(){
          return  shopProduct.getProducts();
     }

     public void setProduct(Product product){
          mutableProduct.setValue(product);
     }

     public LiveData<Product> getProduct(){
          return mutableProduct;
     }

     public LiveData<List<CartItem>> getCart(){
          return shoppingCart.getCart();
     }

     public boolean addItemToCart(Product product){
          return shoppingCart.addItemToCart(product);
     }

     public void removeItemFromCart(CartItem cartItem){
          shoppingCart.removeItemFromCart(cartItem);
     }

     public void changeQuantity(CartItem cartItem,int quantity){
          shoppingCart.changeQuantity(cartItem,quantity);
     }

     public LiveData<Double> getTotalPrice(){
          return shoppingCart.getTotalPrice();
     }

     public void resetCart(){
          shoppingCart.initCart();
     }
     public CartItem getCartItemByProduct(Product product){
          return shoppingCart.getCartItemByProduct(product);
     }
}
