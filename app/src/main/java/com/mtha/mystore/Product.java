package com.mtha.mystore;


import android.os.Parcel;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import java.io.Serializable;


public class Product implements Serializable {
    private String id;
    private String name;
    private double price;
    private String imageUrl;


    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        price = in.readDouble();
        imageUrl = in.readString();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product() {
    }

    public Product(String id, String name, double price,  String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.getPrice(), getPrice()) == 0 &&
                getId().equals(product.getId()) &&
                getName().equals(product.getName()) &&
                getImageUrl().equals(product.getImageUrl());
    }

    public static DiffUtil.ItemCallback<Product> itemCallback = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.equals(newItem);
        }
    };
}
