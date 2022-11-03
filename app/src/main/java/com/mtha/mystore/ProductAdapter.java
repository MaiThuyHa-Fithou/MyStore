package com.mtha.mystore;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    List<Product> products;
    ProductViewModel productViewModel;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
        productViewModel = new ViewModelProvider((AppCompatActivity) context).get(ProductViewModel.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phone, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.product_name.setText(product.getName());
        holder.product_price.setText(product.getPrice() + "");
        loadImage(holder.product_img, product.getImageUrl());
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //them san pham vao gio hang
                if (productViewModel.addItemToCart(product)) {
                    Log.i("AddToCart","success");
                } else {
                    Log.i("AddToCart","failed");
                }
                notifyDataSetChanged();

            }
        });
        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //xoa san pham vua them khoi gio hang
                CartItem cartItem = productViewModel.getCartItemByProduct(product);
                productViewModel.removeItemFromCart(cartItem);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static void loadImage(ImageView imageView, String imageUrl) {
        Glide.with(imageView)
                .load(imageUrl)
                .fitCenter()
                .into(imageView);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView product_name;
        TextView product_price;
        ImageView product_img;
        ImageButton addToCart;
        ImageButton removeItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.product_name = itemView.findViewById(R.id.product_name);
            this.product_price = itemView.findViewById(R.id.product_price);
            this.product_img = itemView.findViewById(R.id.product_image);
            this.addToCart = itemView.findViewById(R.id.addToCart);
            this.removeItem = itemView.findViewById(R.id.removeItem);
        }
    }
}
