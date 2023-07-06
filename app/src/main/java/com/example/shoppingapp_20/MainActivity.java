package com.example.shoppingapp_20;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.shoppingapp_20.adapter.ItemsAdapter;
import com.example.shoppingapp_20.database.DatabaseHelper;
import com.example.shoppingapp_20.modelclass.CartModelClass;
import com.example.shoppingapp_20.modelclass.ItemModelClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ItemsAdapter itemsRecyclerViewAdapter;
//    private CategoryAdapter categoryRecyclerViewAdapter;
    public static ArrayList<ItemModelClass> itemsArrayList = new ArrayList<>();


    public static ArrayList<CartModelClass> cartArrayList;
    private RecyclerView recyclerViewCategoryList, recyclerViewItemsList;
    private Handler handler;
    private ActivityResultLauncher<Intent> cartActivityResultLauncher;
    private FloatingActionButton cartBtn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private DatabaseHelper databaseHelper;
    public static  ArrayList<CartModelClass> cartItems;
    public static HashMap<String, CartModelClass> hashMap = new HashMap<>();
    public static HashMap<String, Integer> itemsCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("settings",MODE_PRIVATE);
        boolean isOpenedFirstTime = sharedPreferences.getBoolean("isOpenedFirstTime",true);
        databaseHelper = DatabaseHelper.getInstance(MainActivity.this);
        Toast.makeText(this,""+itemsArrayList.toString(),Toast.LENGTH_SHORT).show();
        cartBtn = findViewById(R.id.cartBtn);

        initializeTableIfFirstTime(isOpenedFirstTime);
        if(!isOpenedFirstTime){
            Log.d("items", "items");
            itemsArrayList = (ArrayList<ItemModelClass>)databaseHelper.itemsDao().getAllItems();

        }
        recyclerViewItemsList();

        cartActivityLauncherSetup();




        itemsCost = itemsCost(itemsArrayList);

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartActivity();
            }
        });





    }

    private void cartActivityLauncherSetup() {
        cartActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        // Check the result code
                        if (result.getResultCode() == RESULT_OK) {
                            // Get the data from the intent
                            itemsRecyclerViewAdapter= new ItemsAdapter(MainActivity.this,itemsArrayList);
                            recyclerViewItemsList.setAdapter(itemsRecyclerViewAdapter);
                            itemsRecyclerViewAdapter.
                        }

                    }
                });
    }

    private void cartActivity() {

        Intent cartIntent = new Intent(MainActivity.this, CartActivity.class);
//        String jsonString = new Gson().toJson(hashMap);
//        cartIntent.putExtra("HashMap",jsonString);
        cartActivityResultLauncher.launch(cartIntent);
    }

    private void recyclerViewItemsList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewItemsList = findViewById(R.id.recyclerView2);
        recyclerViewItemsList.setLayoutManager(linearLayoutManager);
        recyclerViewItemsList.setOverScrollMode(View.SCROLL_CAPTURE_HINT_AUTO);

        itemsRecyclerViewAdapter= new ItemsAdapter(this,itemsArrayList);
        recyclerViewItemsList.setAdapter(itemsRecyclerViewAdapter);




    }

    private void initializeTableIfFirstTime(boolean isOpenedFirstTime) {
        if(isOpenedFirstTime == true){


            editor = sharedPreferences.edit();

//            byte[] image = getBytesFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cabbage));
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cabbage);
            String imageUrl = convertBitmapToString(bitmap);
            itemsArrayList.add(new ItemModelClass("Cabbage","kg","vegetable","vegetable",5,35));
//            image = getBytesFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cabbage));
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cabbage);
//            String convertBitmapToString(bitmap);
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cabbage);
            imageUrl = convertBitmapToString(bitmap);
            itemsArrayList.add(new ItemModelClass("Cauliflower","kg","cabbage","vegetable",5,35));

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cabbage);
            imageUrl = convertBitmapToString(bitmap);
            itemsArrayList.add(new ItemModelClass("Carrot","kg","cabbage","vegetable",6,35));

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cabbage);
            imageUrl = convertBitmapToString(bitmap);
            itemsArrayList.add(new ItemModelClass("Tomato","kg","cabbage","vegetable",7,35));

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cabbage);
            imageUrl = convertBitmapToString(bitmap);
            itemsArrayList.add(new ItemModelClass("Onions","kg","cabbage","vegetable",8,35));

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cabbage);
            imageUrl = convertBitmapToString(bitmap);
            itemsArrayList.add(new ItemModelClass("Potato","kg","cabbage","vegetable",9,35));



            for (int i = 0; i< itemsArrayList.size(); i++) {
                databaseHelper.itemsDao().insertItem(itemsArrayList.get(i));
            }

            editor.putBoolean("isOpenedFirstTime", false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                editor.apply();
            }
        }
    }

    // convert from bitmap to byte array
//    public byte[] getBytesFromBitmap(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
//        return stream.toByteArray();
//    }
//
//    public String getURLForResource (int resourceId) {
//        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
//        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +resourceId).toString();
//    }

    public String convertBitmapToString(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream); //compress to which format you want.
        byte[] byte_arr = stream.toByteArray();
        String imageStr = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.FROYO) {
            imageStr = Base64.encodeToString(byte_arr, 1);
        }
        return imageStr;
    }

    public HashMap<String,Integer> itemsCost(ArrayList<ItemModelClass> items){
        HashMap<String,Integer> tempItemsCost =new HashMap<>();
        for(int i = 0; i < items.size(); i++){
            tempItemsCost.put(items.get(i).getName(),items.get(i).getCost());
        }
        return tempItemsCost;
    }




}