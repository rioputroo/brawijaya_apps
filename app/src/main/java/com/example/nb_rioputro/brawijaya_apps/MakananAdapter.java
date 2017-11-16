package com.example.nb_rioputro.brawijaya_apps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import static com.example.nb_rioputro.brawijaya_apps.UserDetails.nama;
import static com.example.nb_rioputro.brawijaya_apps.UserDetails.password;

/**
 * Created by nb-rioputro on 11/16/2017.
 */

public class MakananAdapter extends RecyclerView.Adapter<MakananAdapter.ViewHolder> {
    private List<Makanan> mknList;
    int pos = 1;
    Context context;


    public MakananAdapter(Context context) {
        this.context = context;
    }


    public MakananAdapter(List<Makanan> mknList) {
        this.mknList = mknList;
    }

    @Override
    public MakananAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MakananAdapter.ViewHolder holder, int position) {
        final Makanan makanan = mknList.get(position);

        holder.food_name.setText(makanan.getFoodname());
        holder.food_price.append(makanan.getCustprice());
        Glide.with(holder.food_image.getContext()).load(makanan.getFoodimage()).into(holder.food_image);

        holder.order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.order.setVisibility(View.GONE);
                holder.numberButton.setVisibility(View.VISIBLE);
                holder.cancel.setVisibility(View.VISIBLE);
                holder.add.setVisibility(View.VISIBLE);
                holder.etNote.setVisibility(View.VISIBLE);
            }
        });

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.numberButton.setVisibility(View.GONE);
                holder.cancel.setVisibility(View.GONE);
                holder.add.setVisibility(View.GONE);
                holder.etNote.setVisibility(View.GONE);
                holder.order.setVisibility(View.VISIBLE);
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Toast.makeText(view.getContext().getApplicationContext(), "Added to cart!", Toast.LENGTH_LONG).show();
//                SharedPreferences sp = view.getContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//                final String username = sp.getString(Config.USERNAME_SHARED_PREF, "error getting id");
//                Log.d("sp username", username);
//
//                //final String username = UserDetails.username;
//
//                final String foodid = makanan.getFoodid();
//                final String note = holder.etNote.getText().toString();
//                final String foodname = makanan.getFoodname();
//                final int quantity = Integer.parseInt(holder.numberButton.getNumber());
//                final String price = makanan.getCustprice().replace(",", "").trim();
//
//                int ext_price = Integer.parseInt(price);
//                int total = ext_price * quantity;
//
//                Log.d("Total", String.valueOf(total));
//
//                final String status = "cart";
//
//                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("cart");
//                String cartId = mDatabase.push().getKey();
//                Cart cart = new Cart(username, foodid, note, quantity, total, status);
//                mDatabase.child(cartId).setValue(cart);
//
//                holder.numberButton.setVisibility(View.GONE);
//                holder.cancel.setVisibility(View.GONE);
//                holder.add.setVisibility(View.GONE);
//                holder.etNote.setVisibility(View.GONE);
//                holder.order.setVisibility(View.VISIBLE);

//                String url = "https://brawijaya-be227.firebaseio.com/cart.json";
//
//                StringRequest userRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Firebase reference = new Firebase("https://brawijaya-be227.firebaseio.com/cart");
//
//
//                            reference.child(username).child("foodid").setValue(foodid);
//                            reference.child(username).child("foodname").setValue(foodname);
//                            reference.child(username).child("note").setValue(note);
//                            reference.child(username).child("quantity").setValue(quantity);
//                            reference.child(username).child("price").setValue(price);
//                            reference.child(username).child("status").setValue(status);
//
//                            Toast.makeText(view.getContext().getApplicationContext(), "Added to cart!", Toast.LENGTH_LONG).show();
//                            //startActivity(new Intent(SignupActivity.this, LoginActivity.class));
//                            holder.numberButton.setVisibility(View.GONE);
//                            holder.cancel.setVisibility(View.GONE);
//                            holder.add.setVisibility(View.GONE);
//                            holder.etNote.setVisibility(View.GONE);
//                            holder.order.setVisibility(View.VISIBLE);
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(view.getContext().getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
//                    }
//                });
//
//                userRequest.setRetryPolicy(new RetryPolicy() {
//                    @Override
//                    public int getCurrentTimeout() {
//                        return 50000;
//                    }
//
//                    @Override
//                    public int getCurrentRetryCount() {
//                        return 50000;
//                    }
//
//                    @Override
//                    public void retry(VolleyError error) throws VolleyError {
//
//                    }
//                });
//
//                RequestQueue rq = Volley.newRequestQueue(view.getContext().getApplicationContext());
//                rq.add(userRequest);


            }
        });
    }

    @Override
    public int getItemCount() {
        return mknList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView food_image;
        private TextView food_name;
        private TextView food_price;
        private TextView order;
        private TextView add;
        private TextView cancel;
        private EditText etNote;

        private ElegantNumberButton numberButton;
        Button btnDetailsFood;


        public ViewHolder(View itemView) {
            super(itemView);

            food_image = (ImageView) itemView.findViewById(R.id.food_image);
            food_name = (TextView) itemView.findViewById(R.id.food_name);
            food_price = (TextView) itemView.findViewById(R.id.food_price);
            order = (TextView) itemView.findViewById(R.id.tvPlaceOrder);
            numberButton = (ElegantNumberButton) itemView.findViewById(R.id.number_button_baru);
            add = (TextView) itemView.findViewById(R.id.tvOrderFix);
            cancel = (TextView) itemView.findViewById(R.id.tvCancelOrder);
            etNote = (EditText) itemView.findViewById(R.id.etNoteOrder);
            //btnDetailsFood = (Button) itemView.findViewById(R.id.btnFoodDetails);

        }
    }
}
