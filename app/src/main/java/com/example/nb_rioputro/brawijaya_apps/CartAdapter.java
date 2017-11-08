package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

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
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {
        Cart cart = cartList.get(position);

        String jumlah = String.valueOf(cart.getJumlah_order());
        String total = String.valueOf(cart.getTotal_order());

        rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
        String rupiah = rupiahFormat.format(Double.parseDouble(total));

        holder.tvOrderFoodName.setText(cart.getName_food());
        holder.tvOrderFoodQuantity.append(jumlah);
        holder.tvOrderFoodTotal.append(total);

        Glide.with(holder.ivOrderFoodImage.getContext()).load(cart.getPict_food()).into(holder.ivOrderFoodImage);
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

        public ViewHolder(View itemView) {
            super(itemView);

            tvOrderFoodName = (TextView) itemView.findViewById(R.id.orderFoodName);
            tvOrderFoodQuantity = (TextView) itemView.findViewById(R.id.orderFoodQuantity);
            tvOrderFoodTotal = (TextView) itemView.findViewById(R.id.orderFoodTotal);
            ivOrderFoodImage = (ImageView) itemView.findViewById(R.id.orderFoodImage);

        }
    }
}
