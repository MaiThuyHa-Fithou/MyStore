package com.mtha.mystore;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    MutableLiveData<List<CartItem>> mutableCart =new MutableLiveData<>();
    MutableLiveData<Double> mutableTotalPrice = new MutableLiveData<>();
    public LiveData<List<CartItem>> getCart(){
        if(mutableCart.getValue()==null){
            //init cart
            initCart();
        }
        return mutableCart;
    }

    public void initCart(){
        mutableCart.setValue(new ArrayList<CartItem>());
        //tinh tong thanh tien
        calculateCartTotal();
    }

    public boolean addItemToCart(Product product){
        if(mutableCart.getValue()==null){
            //gio hang rong
            initCart();
        }
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        for(CartItem cartItem: cartItemList){
            //san pham da co trong gio hang, cap nhat so luong len 1
            if(cartItem.getProduct().getId().equals(product.getId())){
                if(cartItem.getQuantity()==5){
                    return false;
                }
                int index = cartItemList.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity()+1);
                cartItemList.set(index,cartItem);//update lai doi tuong cartitem
                //cap nhat gio hang
                mutableCart.setValue(cartItemList);
                calculateCartTotal();
                return  true;
            }
        }
        //chua co san pham trong gio hang
        //them san pham do vao gio hang
        CartItem cartItem = new CartItem(product,1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        return  true;
    }
    public void removeItemFromCart(CartItem cartItem){
        if(mutableCart.getValue()==null){
            return;
        }
        //xoa san pham
        List<CartItem> cartItems = new ArrayList<>(mutableCart.getValue());
       if(cartItems.remove(cartItem)){
           //update lai gio hang
           Log.i("DONE", "cart item da xoa");
           mutableCart.setValue(cartItems);
           calculateCartTotal();
       }else{
           Log.i("REMOVE_CART_ITEM", "cart item khong co trong ds de xoa");
       }
    }

    public void changeQuantity(CartItem cartItem, int quantity){
        if(mutableCart.getValue()==null)
            return;
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        CartItem upCartItem = new CartItem(cartItem.getProduct(),quantity);
        cartItemList.set(cartItemList.indexOf(cartItem),upCartItem);
        //cap nhat lai gio hang
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    public LiveData<Double> getTotalPrice(){
        if(mutableTotalPrice.getValue()==null)
            mutableTotalPrice.setValue(0.0);
        return mutableTotalPrice;
    }
    public CartItem getCartItemByProduct(Product product){
        if(mutableCart.getValue()==null)
            return null;
        List<CartItem> cartItemList = new ArrayList<>(mutableCart.getValue());
        for (CartItem item:cartItemList){
            if(item.getProduct().equals(product)){
                return item;
            }
        }
        return null;
    }
    private void calculateCartTotal(){
        if(mutableCart.getValue()==null)
            return;
        double total=0.0;
        List<CartItem> cartItems = mutableCart.getValue();
        for (CartItem item: cartItems) {
            total +=item.getProduct().getPrice()*item.getQuantity();
        }
        mutableTotalPrice.setValue(total);
    }
}
