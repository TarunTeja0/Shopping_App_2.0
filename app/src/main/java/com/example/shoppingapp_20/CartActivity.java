package com.example.shoppingapp_20;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp_20.adapter.CartAdapter;
import com.example.shoppingapp_20.modelclass.CartModelClass;
import com.example.shoppingapp_20.modelclass.ItemModelClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;

public class CartActivity extends AppCompatActivity {
    private Intent intent;
    ArrayList<ItemModelClass> items;
    private static TextView cartAmount;
    private static TextView deliveryCharges;
    private static TextView total;
    private CartAdapter cartRecyclerViewAdapter;
    private RecyclerView cartRecyclerView;
    HashMap<String,CartModelClass> cartItems;
    public static ArrayList<ItemModelClass> cartedItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cartAmount = findViewById(R.id.cartAmount1);
        deliveryCharges = findViewById(R.id.cartAmount2);
        total = findViewById(R.id.cartAmount3);
        cartRecyclerView =findViewById(R.id.cartRecyclerView);
        intent = getIntent();
//        String jsonString = intent.getStringExtra("HashMap");
//        TypeToken<HashMap<String, CartModelClass>> typeToken = new TypeToken<HashMap<String, CartModelClass>>() {};
//       cartItems = new Gson().fromJson(jsonString,typeToken);

       cartRecyclerViewAdapter =new CartAdapter(this);

       cartRecyclerView.setAdapter(cartRecyclerViewAdapter);
       cartRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }

    public static void bill(ArrayList<CartModelClass> items){
        int cartTotal=0;
        int deliveryChar= 15;

        if(items.size()==0){
            return;
        }
        for(int i = 0; i< items.size();i++){
            cartTotal += items.get(i).getCartItemQuantity() * items.get(i).getCost();
        }
        cartAmount.setText(String.valueOf(cartTotal));
        deliveryCharges.setText(String.valueOf(deliveryChar));
        total.setText(String.valueOf(cartTotal + deliveryChar));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        setResult(getIntent().getIntExtra("resultCode", RESULT_OK));


    }
}