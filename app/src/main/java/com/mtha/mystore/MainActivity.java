package com.mtha.mystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rcvProduct;
    ProductViewModel productViewModel;
    TextView cart_size;
    ProductAdapter productAdapter;
    List<Product> productList;
    int cartQuantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcvProduct = findViewById(R.id.products_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rcvProduct.setLayoutManager(layoutManager);
        rcvProduct.setHasFixedSize(true);
        productViewModel = new ViewModelProvider(MainActivity.this).get(ProductViewModel.class);
        productList = productViewModel.getProducts().getValue();
        cart_size = findViewById(R.id.cart_size);
        productAdapter = new ProductAdapter(MainActivity.this,productList);
        rcvProduct.setAdapter(productAdapter);
        productViewModel.getCart().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                int quantity =0;
                for(CartItem cartItem: cartItems)
                    quantity +=cartItem.quantity;
                cartQuantity = quantity;
            //    invalidateOptionsMenu();
                cart_size.setText(cartQuantity+"");
            }
        });
    }

    public void onShowCart(View view) {
        Intent intent = new Intent(MainActivity.this,ShoppingCartActivity.class);
        Bundle bundle = new Bundle();

        bundle.putParcelableArrayList("list", (ArrayList<CartItem>) productViewModel.getCart().getValue());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}