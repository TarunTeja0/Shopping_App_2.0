package com.example.shoppingapp_20.adapter;

import static com.example.shoppingapp_20.MainActivity.hashMap;
import static com.example.shoppingapp_20.MainActivity.itemsArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.example.shoppingapp.Domain.ItemModelClass;
//import com.example.shoppingapp.R;
import com.example.shoppingapp_20.R;
import com.example.shoppingapp_20.modelclass.CartModelClass;
import com.example.shoppingapp_20.modelclass.ItemModelClass;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<ItemModelClass> items;

    //created an array for quantity to each item in the list
    public static int[] quantity;


    // Constructor
    public ItemsAdapter(Context context, ArrayList<ItemModelClass> items) {
        this.context = context;
        this.items = itemsArrayList;
        this.quantity = new int[items.size()];
    }

    //this method is the send quantity of each item selected by user.
    public int[] getItemsQuantity(){
        return quantity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        final CartModelClass[] cartItem = new CartModelClass[1];

        ItemModelClass item = items.get(position);

        holder.itemName.setText(item.getName());

        holder.itemStock.setText(String.valueOf(item.getStock()));

        if(hashMap.containsKey(item.getName())){
            cartItem[0] = hashMap.get(item.getName());
            assert cartItem[0] != null;
            holder.itemCount.setText(String.valueOf(cartItem[0].getCartItemQuantity()));
        }
            if(holder.itemCount.getText().toString().equals("0")){
                holder.itemMinusBtn.setVisibility(View.INVISIBLE);
            }


        holder.itemMinusBtn.setOnClickListener(v -> {

            cartItem[0].setCartItemQuantity(cartItem[0].getCartItemQuantity()-1);
            holder.itemCount.setText(String.valueOf(cartItem[0].getCartItemQuantity()));

            if(holder.itemCount.getText().toString().equals("0")) {
                holder.itemMinusBtn.setVisibility(View.INVISIBLE);
            }
        });

        holder.itemPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!hashMap.containsKey(item.getName())){
                    hashMap.put(item.getName(), new CartModelClass(item.getIndex(), item.getName(), 1, item.getStock(),item.getCost()));
                    cartItem[0] = hashMap.get(item.getName());
                    assert cartItem[0] != null;
                    holder.itemCount.setText(String.valueOf(cartItem[0].getCartItemQuantity()));
                }else {
                    if ((cartItem[0].getCartItemQuantity() + 1) <= item.getStock()) {
                        cartItem[0].setCartItemQuantity(cartItem[0].getCartItemQuantity() + 1);
                        holder.itemCount.setText(String.valueOf(cartItem[0].getCartItemQuantity()));
                    } else {
                        Toast.makeText(context, "No More Stocks", Toast.LENGTH_SHORT).show();
                    }
                    if (cartItem[0].getCartItemQuantity() > 0) {
                        holder.itemMinusBtn.setVisibility(View.VISIBLE);
                    }
                }
            }
        });








//        if(quantity[position]==0){
//            CartModelClass cartItem = new CartModelClass();
//            cartItem.setCartId(position);
//        }
//        ItemModelClass item = items.get(position);
//        int quantityCount= 0;
//        //image
////        String picUrl = item.getImageUrl();
////        int drawableResourceId =  holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
//        Glide.with(holder.itemView.getContext())
//                .load(R.drawable.cabbage)
//                .into(holder.itemImage);
//        //item name
//        holder.itemName.setText(item.getName());
//        //invisibling minus
//
//
////        if(quantity[position] == 0){
//            if(holder.itemCount.getText().toString().equals("0")){
//                holder.itemMinusBtn.setVisibility(View.INVISIBLE);
//            }
////        }
////        else{
////            holder.itemCount.setText();
////            if(holder.itemCount.getText().toString().equals("0")){
////                holder.itemMinusBtn.setVisibility(View.INVISIBLE);
////            }
//
//
//
////        minus
////        holder.itemMinusBtn.setOnClickListener(v -> {
////
////
//////            quantity[position]--;
//////            holder.itemCount.setText(String.valueOf(quantity[position]));
//////            item.setQuantity(quantity[position]);
////            if(holder.itemCount.getText().toString().equals("0")) {
////                holder.itemMinusBtn.setVisibility(View.INVISIBLE);
////            }
////        });
//
//        //plus
////        holder.itemPlusBtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if(quantity[position]+1>0){
////                    holder.itemMinusBtn.setVisibility(View.VISIBLE);
////                }
////                if(quantity[position]+1 > item.getStock()){
////                    Toast.makeText(context,"No More Stocks", Toast.LENGTH_SHORT).show();
////                }
////                else{
////                    quantity[position]++;
////                    Log.d("plus","++");
////                    item.setQuantity(quantity[position]);
////                    Log.d("plus",""+item.getQuantity());
////                    holder.itemCount.setText(String.valueOf(quantity[position]));
////                }
////
////            }
////        });
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return items.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView itemImage;
        private final TextView itemName;
        private final TextView itemCount;
        private final ImageButton itemPlusBtn, itemMinusBtn;
        private final TextView itemStock;

        //todo when error occurs remove final from variables above;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.itemImage);
            itemName = itemView.findViewById(R.id.itemName);
            itemCount = itemView.findViewById(R.id.itemCount);
            itemPlusBtn = itemView.findViewById(R.id.plusBtn);
            itemMinusBtn = itemView.findViewById(R.id.minusBtn);
            itemStock = itemView.findViewById(R.id.stockTv);
        }
    }
}