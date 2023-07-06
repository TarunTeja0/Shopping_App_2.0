package com.example.shoppingapp_20.adapter;

//import static com.example.shoppingapp.CartActivity.cartedItems;
import static com.example.shoppingapp_20.MainActivity.hashMap;
import static com.example.shoppingapp_20.MainActivity.itemsCost;

import android.content.Context;
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
import com.example.shoppingapp_20.CartActivity;
import com.example.shoppingapp_20.MainActivity;
import com.example.shoppingapp_20.R;
import com.example.shoppingapp_20.modelclass.CartModelClass;

import java.util.ArrayList;
import java.util.HashMap;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<CartModelClass> items;

    private  ArrayList<Integer> ArrayItemsWhichQuantityGreaterThanZero = new ArrayList<>();

    // Constructor
    public CartAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>(hashMap.values());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_each_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CartModelClass item = items.get(position);

        holder.cartName.setText(item.getCartItemName());

        holder.cartCount.setText(String.valueOf(item.getCartItemQuantity()));

        int[] cost = {((int) Integer.parseInt(holder.cartCount.getText().toString())) * item.getCost()};
        holder.cartCost.setText(String.valueOf(cost[0]));

        CartActivity.bill(items);

        holder.cartMinusBtn.setOnClickListener(v -> {

            item.setCartItemQuantity(item.getCartItemQuantity() - 1);
            holder.cartCount.setText(String.valueOf(item.getCartItemQuantity()));

            if (holder.cartCount.getText().toString().equals("0")) {
                holder.cartMinusBtn.setVisibility(View.INVISIBLE);
            }

            cost[0] = ((int) Integer.parseInt(holder.cartCount.getText().toString())) * item.getCost();
            holder.cartCost.setText(String.valueOf(cost[0]));

            CartActivity.bill(items);
        });

        holder.cartPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.cartCount.setText(String.valueOf(item.getCartItemQuantity()));

                if ((item.getCartItemQuantity() + 1) <= item.getStock()) {
                    item.setCartItemQuantity(item.getCartItemQuantity() + 1);
                    holder.cartCount.setText(String.valueOf(item.getCartItemQuantity()));
                } else {
                    Toast.makeText(context, "No More Stocks", Toast.LENGTH_SHORT).show();
                }
                if (item.getCartItemQuantity() > 0) {
                    holder.cartMinusBtn.setVisibility(View.VISIBLE);
                }

                cost[0] = ((int) Integer.parseInt(holder.cartCount.getText().toString())) * item.getCost();
                holder.cartCost.setText(String.valueOf(cost[0]));

                CartActivity.bill(items);
            }
        });
    }



    //checking is count of item quantity greater than 0
        //ikkada carted ante item yokka quantity greater than 0 unte carted
        //cartedItemArrayIndex - cIAI
//         int cIAI = getCartedItemArrayIndex(position);
        //image
//        String picUrl = items.get(position).getImageUrl();
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .into(holder.cartImage);
//        //item name
//        holder.cartName.setText(items.get(position).getName());
//        holder.cartCount.setText(String.valueOf(items.get(position).getQuantity()));
//
//        //item cost*quantity
//        int cost = ((int) Integer.parseInt(holder.cartCount.getText().toString())) * items.get(position).getCost();
//        holder.cartCost.setText(String.valueOf(cost));
//
//        //minus
//        holder.cartMinusBtn.setOnClickListener(v -> {
//            items.get(position).setQuantity(items.get(position).getQuantity() - 1);
//            int quantity = items.get(position).getQuantity();
//            holder.cartCount.setText(String.valueOf(quantity));
//
//            if (holder.cartCount.getText().toString().equals("0")) {
//                holder.cartMinusBtn.setVisibility(View.INVISIBLE);
//            }
//            holder.cartCost.setText(String.valueOf(items.get(position).getCost() * Integer.parseInt(holder.cartCount.getText().toString())));
////            items lo unna item ni teeskuni andholo unna index ni telusukuntaam, ee index manam itemsArrayList lo ki item ni add chesinapdu create chesaam, dheenini use cheskuni manam itemsArraylist lo unna item index kanukkovachu. aa index value ni id ki assign chesthunnam
//            int id = items.get(position).getIndex();
////            items lo unna item yokka quantity ni telsukoniki quantity variable ni use chesa
//            int itemQuantity = items.get(position).getQuantity();
//            //main array lo quantity updating
//            itemsArrayList.get(id).setQuantity(itemQuantity);
//            CartActivity.bill(items);
//        });
//
//        //plus
//        holder.cartPlusBtn.setOnClickListener(v -> {
//            items.get(position).setQuantity(items.get(position).getQuantity() + 1);
//            int quantity = items.get(position).getQuantity();
//            if (quantity > 0) {
//                holder.cartMinusBtn.setVisibility(View.VISIBLE);
//            }
//            if (quantity > items.get(position).getStock()) {
//                items.get(position).setQuantity(quantity - 1);
//                Toast.makeText(context, "No More Stock", Toast.LENGTH_SHORT).show();
//            } else {
//                holder.cartCount.setText(String.valueOf(quantity));
//            }
//            holder.cartCost.setText(String.valueOf(items.get(position).getCost() * Integer.parseInt(holder.cartCount.getText().toString())));
//            Toast.makeText(context, "" + items.get(position).getQuantity(), 0).show();
////            items lo unna item ni teeskuni andholo unna index ni telusukuntaam, ee index manam itemsArrayList lo ki item ni add chesinapdu create chesaam, dheenini use cheskuni manam itemsArraylist lo unna item index kanukkovachu. aa index value ni id ki assign chesthunnam
//            int id = items.get(position).getIndex();
////            items lo unna item yokka quantity ni telsukoniki quantity variable ni use chesa
//            int itemQuantity = items.get(position).getQuantity();
//            //main array lo quantity updating
//            itemsArrayList.get(id).setQuantity(itemQuantity);
//            CartActivity.bill(items);
//        });


//    private int getCartedItemArrayIndex(int position) {
//        return ArrayItemsWhichQuantityGreaterThanZero.get(position);
//    }




    @Override
        public int getItemCount() {
//            int size = getCartedItemsCount();
//this method is used for showing number of card items in recycler view
            return items.size();
        }

        // View holder class for initializing of your views such as TextView and Imageview
        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final ImageView cartImage;
            private final TextView cartName;
            private final TextView cartCount;
            private final ImageButton cartPlusBtn, cartMinusBtn;
            private final TextView cartCost;

            //todo when error occurs remove final from variables above;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                cartImage = itemView.findViewById(R.id.itemImage);
                cartName = itemView.findViewById(R.id.itemName);
                cartCount = itemView.findViewById(R.id.itemCount);
                cartPlusBtn = itemView.findViewById(R.id.plusBtn);
                cartMinusBtn = itemView.findViewById(R.id.minusBtn);
                cartCost = itemView.findViewById(R.id.itemCost);
            }
        }


//        private int getCartedItemsCount() {
//            int count = 0;
//            for (int i = 0; i < items.size(); i++) {
//                if (items.get(i).getQuantity() > 0) {
//                    count++;
//                    //while counting carted items, pushing those indexes into ArrayItemsWhichQuantityGreaterThanZero
//                    ArrayItemsWhichQuantityGreaterThanZero.add(i);
//                }
//            }
//            return ArrayItemsWhichQuantityGreaterThanZero.size();
//        }

}