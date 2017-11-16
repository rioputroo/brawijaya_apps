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
        Cart cart = cartList.get(position);

        final String jumlah = String.valueOf(cart.getJumlah_order());
        final Double total = Double.valueOf(cart.getTotal_order());
        final int id = cart.getId();
        Log.d("ID di cartadapter: ", String.valueOf(id));

        rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);


        //holder.numberOrderQuantity.setNumber(jumlah);
        rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
        String rupiah = rupiahFormat.format(total);


        final int pricefood = cart.getPrice_food();

//        Glide.with(holder.ivOrderFoodImage.getContext()).load(cart.getPict_food()).into(holder.ivOrderFoodImage);
//        holder.tvOrderFoodTotal.append(rupiah);
//        holder.tvOrderFoodName.setText(cart.getName_food());
//        holder.tvOrderFoodQuantity.append(jumlah);

        Glide.with(holder.ivOrderFoodImage.getContext()).load(R.drawable.eggbread_2).into(holder.ivOrderFoodImage);
        holder.tvOrderFoodTotal.append("99,000");
        holder.tvOrderFoodName.setText("Club Sandwich");
        holder.tvOrderFoodQuantity.append("2");

        holder.imbRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder adLogout = new AlertDialog.Builder(view.getContext());
                adLogout.setMessage("Are you sure you want to remove this order? ");
                adLogout.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringRequest removeOrder = new StringRequest(Request.Method.POST, Config.REMOVE_ORDER, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("responseDelete: ", response);
                                if (response.equals("true")) {
                                    Intent reload = new Intent(view.getContext(), MyOrderActivity.class);
                                    view.getContext().startActivity(reload);
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("id", String.valueOf(id));

                                return params;
                            }
                        };
                        removeOrder.setRetryPolicy(new RetryPolicy() {
                            @Override
                            public int getCurrentTimeout() {
                                return 50000;
                            }

                            @Override
                            public int getCurrentRetryCount() {
                                return 50000;
                            }

                            @Override
                            public void retry(VolleyError error) throws VolleyError {

                            }
                        });

                        RequestQueue rq = Volley.newRequestQueue(view.getContext());
                        rq.add(removeOrder);
                    }
                });

                adLogout.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = adLogout.create();
                alertDialog.show();
            }
        });


//        holder.numberOrderQuantity.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
//            @Override
//            public void onValueChange(ElegantNumberButton view, int oldValue, final int newValue) {
//                Log.d("cek jumlah newval: ", jumlah);
//                Log.d("cek jumlah newval:", String.valueOf(newValue));
//                //Toast.makeText(view.getContext(), String.valueOf(newValue), Toast.LENGTH_SHORT).show();
//                Log.d("TAG-number: ", String.format("oldValue: %d   newValue: %d", oldValue, newValue));
//                if (!jumlah.equals(String.valueOf(newValue)) || jumlah.equals(String.valueOf(newValue)) ) {
//                    final int jumlahbaru = newValue * pricefood;
//                    rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
//                    final String rupiahBaru = rupiahFormat.format(jumlahbaru);
//
//
//                    StringRequest changeNumber = new StringRequest(Request.Method.POST, Config.UPDATE_ORDER_QTY, new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            holder.tvOrderFoodTotal.setText("IDR " + rupiahBaru);
//                            Log.d("changeNumb:", response);
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.d("errChangeNumb: ", error.toString());
//                        }
//                    }) {
//                        @Override
//                        protected Map<String, String> getParams() throws AuthFailureError {
//                            Map<String, String> params = new HashMap<>();
//                            params.put("id", String.valueOf(id));
//                            Log.d("id di map: ", String.valueOf(id));
//                            params.put("total_order", String.valueOf(jumlahbaru));
//                            params.put("jumlah_order", String.valueOf(newValue));
//
//                            return params;
//                        }
//                    };
//
//                    changeNumber.setRetryPolicy(new RetryPolicy() {
//                        @Override
//                        public int getCurrentTimeout() {
//                            return 50000;
//                        }
//
//                        @Override
//                        public int getCurrentRetryCount() {
//                            return 50000;
//                        }
//
//                        @Override
//                        public void retry(VolleyError error) throws VolleyError {
//
//                        }
//                    });
//
//                    RequestQueue rq = Volley.newRequestQueue(view.getContext());
//                    rq.add(changeNumber);
//
//                } else if (oldValue == 1 && newValue == 0) {
//                    int jumlahA = pricefood;
//                    rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
//                    String rupiahBaru = rupiahFormat.format(jumlahA);
//                    holder.tvOrderFoodTotal.setText("IDR " + rupiahBaru);
//                } else if (oldValue == 0 && newValue == 1) {
//                    int jumlahB = pricefood;
//                    rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
//                    String rupiahBaru = rupiahFormat.format(jumlahB);
//                    holder.tvOrderFoodTotal.setText("IDR " + rupiahBaru);
//                } else if (oldValue == 2 && newValue == 1) {
//                    int jumlahB = pricefood;
//                    rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
//                    String rupiahBaru = rupiahFormat.format(jumlahB);
//                    holder.tvOrderFoodTotal.setText("IDR " + rupiahBaru);
//                }
//            }
//        });

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
        //        private ElegantNumberButton numberOrderQuantity;
        private ImageButton imbRemove;

        public ViewHolder(View itemView) {
            super(itemView);

            tvOrderFoodName = (TextView) itemView.findViewById(R.id.orderFoodName);
            tvOrderFoodQuantity = (TextView) itemView.findViewById(R.id.orderFoodQuantity);
//            numberOrderQuantity = (ElegantNumberButton) itemView.findViewById(R.id.number_order_quantity);
            tvOrderFoodTotal = (TextView) itemView.findViewById(R.id.orderFoodTotal);
            ivOrderFoodImage = (ImageView) itemView.findViewById(R.id.orderFoodImage);
            imbRemove = (ImageButton) itemView.findViewById(R.id.imbRemove);

        }
    }
}
