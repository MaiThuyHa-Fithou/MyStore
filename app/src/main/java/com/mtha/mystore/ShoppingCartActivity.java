package com.mtha.mystore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCartActivity extends AppCompatActivity {
    RecyclerView rcvCartItems;
    ProductViewModel productViewModel;
    ShoppingCartAdapter shoppingCartAdapter;
    List<CartItem> cartItemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        rcvCartItems = findViewById(R.id.shopping_cart_recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ShoppingCartActivity.this);
        rcvCartItems.setLayoutManager(layoutManager);
        rcvCartItems.setHasFixedSize(true);
        productViewModel = new ViewModelProvider(ShoppingCartActivity.this).get(ProductViewModel.class);
        cartItemList =getIntent().getExtras().getParcelableArrayList("list");
        shoppingCartAdapter= new ShoppingCartAdapter(ShoppingCartActivity.this,cartItemList);
        rcvCartItems.setAdapter(shoppingCartAdapter);
    }
}