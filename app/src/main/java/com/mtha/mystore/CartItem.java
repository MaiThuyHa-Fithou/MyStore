package com.mtha.mystore;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.io.Serializable;

public class CartItem implements Serializable {
    Product product;
    int quantity;

    public CartItem() {
    }

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public CartItem(Product product) {
        this.product = product;
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(this==obj)
            return true;
        if(obj==null||getClass()!=obj.getClass())
            return false;
        CartItem cartItem=(CartItem) obj;
        return getQuantity()==cartItem.getQuantity()
                && getProduct()==cartItem.getProduct();
    }

   public static DiffUtil.ItemCallback<CartItem> itemItemCallback = new DiffUtil.ItemCallback<CartItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull CartItem oldItem, @NonNull CartItem newItem) {
            return oldItem.getQuantity() ==newItem.getQuantity();
        }

        @Override
        public boolean areContentsTheSame(@NonNull CartItem oldItem, @NonNull CartItem newItem) {
            return oldItem.equals(newItem);
        }
    };
}
