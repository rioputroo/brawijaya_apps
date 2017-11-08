package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

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
        holder.tvTest.setText(getItemCount());

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTest;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTest = (TextView)itemView.findViewById(R.id.tvTest);

        }
    }
}
