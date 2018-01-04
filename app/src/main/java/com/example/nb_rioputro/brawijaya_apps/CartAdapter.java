package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by nb-rioputro on 11/8/2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<Cart> cartList;
    NumberFormat rupiahFormat;
    int pos = 1;
    Context context;


    public CartAdapter(Context context) {
        this.context = context;
    }

    public CartAdapter(List<Cart> cartList) {
        this.cartList = cartList;
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder holder, int position) {
        final Cart cart = cartList.get(position);

        final String jumlah = String.valueOf(cart.getJumlah_order());
        final Double total = Double.valueOf(cart.getTotal_order());
        final String id = String.valueOf(cart.getId());
        Log.d("ID di cartadapter: ", id);

        rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
//        String priceNew =
//
//
//        //holder.numberOrderQuantity.setNumber(jumlah);
//        rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
        String rupiah = NumberFormat.getNumberInstance(Locale.US).format(total);


        final int pricefood = cart.getPrice_food();

        Glide.with(holder.ivOrderFoodImage.getContext()).load(cart.getPict_food()).into(holder.ivOrderFoodImage);
        holder.tvOrderFoodTotal.append("IDR " + rupiah);
        holder.tvOrderFoodName.setText(cart.getName_food());
//        holder.tvOrderFoodQuantity.append("Quantity: " + jumlah);
        holder.numberOrderQuantity.setNumber(jumlah);

        if (cart.getNote().isEmpty()) {
            holder.tvNote.setText("Tidak ada note");
        } else {
            holder.tvNote.setText("Note : " + cart.getNote());
        }

        holder.imbRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //Toast.makeText(view.getContext(), String.valueOf(cart.getCurrentProducts()), Toast.LENGTH_SHORT).show();
                //Toast.makeText(view.getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure to remove this order?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NewCart.remove(cart.getCurrentProducts());
                        Intent intent = new Intent(view.getContext(), MyOrderActivity.class);

                        view.getContext().startActivity(intent);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.show();
            }
        });

        holder.numberOrderQuantity.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(final ElegantNumberButton view, int oldValue, final int newValue) {
                Log.d("cek jumlah newval: ", jumlah);
                Log.d("cek jumlah newval:", String.valueOf(newValue));
                //Toast.makeText(view.getContext(), String.valueOf(newValue), Toast.LENGTH_SHORT).show();
                Log.d("TAG-number: ", String.format("oldValue: %d   newValue: %d", oldValue, newValue));

                if (newValue == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Confirm");
                    builder.setMessage("Are you sure to remove this order?");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            NewCart.remove(cart.getCurrentProducts());
                            Intent intent = new Intent(view.getContext(), MyOrderActivity.class);

                            view.getContext().startActivity(intent);
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                            holder.numberOrderQuantity.setNumber(String.valueOf(1));
                        }
                    });

                    builder.show();

                } else {
                    NewCart.update(cart.getCurrentProducts(), newValue);
                    view.getContext().startActivity(new Intent(view.getContext().getApplicationContext(), MyOrderActivity.class));
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOrderFoodName;
        private TextView tvOrderFoodQuantity;
        private TextView tvOrderFoodTotal;
        private ImageView ivOrderFoodImage;
        private TextView tvNote;
        private ElegantNumberButton numberOrderQuantity;
        private ImageButton imbRemove;

        public ViewHolder(View itemView) {
            super(itemView);

            tvOrderFoodName = (TextView) itemView.findViewById(R.id.orderFoodName);
            tvOrderFoodQuantity = (TextView) itemView.findViewById(R.id.orderFoodQuantity);
            numberOrderQuantity = (ElegantNumberButton) itemView.findViewById(R.id.number_order_quantity);
            tvOrderFoodTotal = (TextView) itemView.findViewById(R.id.orderFoodTotal);
            ivOrderFoodImage = (ImageView) itemView.findViewById(R.id.orderFoodImage);
            imbRemove = (ImageButton) itemView.findViewById(R.id.imbRemove);
            tvNote = (TextView) itemView.findViewById(R.id.tvNote);

        }
    }
}
