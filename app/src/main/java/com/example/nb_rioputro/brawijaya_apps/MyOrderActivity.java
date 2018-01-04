package com.example.nb_rioputro.brawijaya_apps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MyOrderActivity extends AppCompatActivity {
    ImageButton imbBack;
    RecyclerView recyclerView;
    String mId;
    TextView tvRealTotal;
    private CartAdapter cartAdapter;
    private List<Cart> cartList = new ArrayList<>();
    NumberFormat rupiahFormat;
    int realTotal = 0;
    CoordinatorLayout orderLayout;
    String rupiah;
    Button btnCheckout;
    String id, id_user;
    ProgressDialog progressDialog;
    CardView cvTotal;
    TextView tvEmptyOrder;
    ImageView ivEmptyCart;
    int total;
    List<Cart> cartDetailsList = new ArrayList<>();
    String id_purchase_details;
    Cart cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        Log.d("sizecart", String.valueOf(NewCart.contents().size()));
//        for (Item item : NewCart.contents()) {
//            String foodName = item.getProducts().getProductsName();
//            id = item.getProducts().getProductsId();
//            int jumlahOrder = item.getQuantity();
//            sendDetails(id, jumlahOrder);
//
//            Log.d("iddidetail", id);
//        }

        tvRealTotal = (TextView) findViewById(R.id.tvRealTotal);
        cvTotal = (CardView) findViewById(R.id.cvTotal);
        tvEmptyOrder = (TextView) findViewById(R.id.tvEmptyCart);
        ivEmptyCart = (ImageView) findViewById(R.id.ivEmptyCart);

        ivEmptyCart.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.empty_cart, 300, 300));
//
        orderLayout = (CoordinatorLayout) findViewById(R.id.orderLayout);


        imbBack = (ImageButton) findViewById(R.id.imbBack);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
//
        SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        id_user = sp.getString(Config.ID_SHARED_PREF, "error getting id");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_cart);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        cartAdapter = new CartAdapter(cartList);
        recyclerView.setAdapter(cartAdapter);


        getListOrder();

        //Toast.makeText(getApplicationContext(),String.valueOf(NewCart.total()),Toast.LENGTH_SHORT).show();
        total = (int) NewCart.total();
        if (total == 0.0) {
            cvTotal.setVisibility(View.GONE);
            tvEmptyOrder.setVisibility(View.VISIBLE);
            ivEmptyCart.setVisibility(View.VISIBLE);
        }

        btnCheckout = (Button) findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkout();
            }
        });
    }


    private void checkout() {
        StringRequest checkoutRequest = new StringRequest(Request.Method.POST, Config.CHECKOUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                id_purchase_details = response;
                insertDetailsCart();
                //Log.d("responseCheckout", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", id_user);
                params.put("total", String.valueOf(total));


                return params;
            }
        };

        checkoutRequest.setRetryPolicy(new RetryPolicy() {
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

        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        rq.add(checkoutRequest);

    }

    private void insertDetailsCart() {
        id_purchase_details = id_purchase_details.replace("\"", "");

        List<Products> toRemove = new ArrayList<>();

        for (Item item : NewCart.contents()) {
            String foodName = item.getProducts().getProductsName();
            id = item.getProducts().getProductsId();
            int jumlahOrder = item.getQuantity();

            Products current = item.getProducts();

            sendDetails(id, jumlahOrder, current);
            Log.d("cartcurrent: ", String.valueOf(item.getProducts()));
            //NewCart.remove(current);
            toRemove.add(current);
        }
        Log.d("isitoremove", String.valueOf(toRemove));
        NewCart.contents().removeAll(toRemove);
        Log.d("isitoremove2", String.valueOf(toRemove));

//        Iterator<Item> iter = NewCart.contents().iterator();
//        while (iter.hasNext()){
//            id = iter.next().getProducts().getProductsId();
//            int jumlahOrder = iter.next().getQuantity();
//            Products current = iter.next().getProducts();
//
//            sendDetails(id, jumlahOrder);
//            Log.d("cartcurrent: ", String.valueOf(current));
//
//        }

        //cartList.clear();
        Toast.makeText(getApplicationContext(), "Your Order has been processed", Toast.LENGTH_LONG).show();
        Intent doneShopping = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(doneShopping);


    }

    private void sendDetails(final String id, final int jumlahOrder, final Products current) {
        Log.d("idsenddetails", id);

        StringRequest insertDetail = new StringRequest(Request.Method.POST, Config.ADD_CARTDETAIL_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                NewCart.remove(current);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyOrderActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("id_purchase_details", id_purchase_details);
                params.put("id_products", id);
                params.put("quantity", String.valueOf(jumlahOrder));

                return params;
            }
        };

        insertDetail.setRetryPolicy(new RetryPolicy() {
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

        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        rq.add(insertDetail);
    }

    private void getListOrder() {
        for (Item item : NewCart.contents()) {
            Log.d("ISI CART: ", item.getProducts().getProductsId() + "--" + item.getProducts().getProductsName() + "--" + item.getQuantity() + "--" + item.getProducts().getProductsPrice());
            String foodName = item.getProducts().getProductsName();
            id = item.getProducts().getProductsId();
            int jumlahOrder = item.getQuantity();
            int price_food = (int) item.getProducts().getProductsPrice();
            int totalOrder = (int) (price_food * jumlahOrder);
            String foodPict = item.getProducts().getProductsPict();
            String note = item.getProducts().getProductsNote();
            Products currentProducts = item.getProducts();

            //Toast.makeText(this, note, Toast.LENGTH_SHORT).show();

            cart = new Cart(foodName, jumlahOrder, totalOrder, foodPict, id, price_food, note, currentProducts);
            cartList.add(cart);

            realTotal = realTotal + totalOrder;


        }

        String rupiah = NumberFormat.getNumberInstance(Locale.US).format(realTotal);
        tvRealTotal.setText("IDR " + rupiah);
//        StringRequest onCartRequest = new StringRequest(Request.Method.POST, Config.GET_CART_LIST_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("ListCart: ", response);
//
//                try {
//                    JSONArray cartArr = new JSONArray(response);
//
//                    for (int i = 0; i < cartArr.length(); i++) {
//                        JSONObject object = cartArr.getJSONObject(i);
//                        id = object.getInt("id");
//                        String foodName = object.getString("name_food");
//                        int jumlahOrder = Integer.parseInt(object.getString("jumlah_order"));
//                        int totalOrder = Integer.parseInt(object.getString("total_order"));
//                        String foodPict = object.getString("pict_food");
//                        int price_food = object.getInt("price_food");
//
////                        Cart cart = new Cart(foodName, jumlahOrder, totalOrder, foodPict, id, price_food);
////                        cartList.add(cart);
//
//                        realTotal = realTotal + totalOrder;
//
//
//                    }
//                    rupiahFormat = NumberFormat.getInstance(Locale.GERMANY);
//                    rupiah = rupiahFormat.format(realTotal);
//                    tvRealTotal.setText("IDR " + rupiah);
//                    Log.d("RUPIAH MY ORDER: ", rupiah);
//
//
//                    Log.d("Total: ", String.valueOf(realTotal));
//                    cartAdapter.notifyDataSetChanged();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("ListCartErr: ", error.toString());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//
//                params.put("id", mId);
//
//                return params;
//            }
//        };
//
//        onCartRequest.setRetryPolicy(new RetryPolicy() {
//            @Override
//            public int getCurrentTimeout() {
//                return 50000;
//            }
//
//            @Override
//            public int getCurrentRetryCount() {
//                return 50000;
//            }
//
//            @Override
//            public void retry(VolleyError error) throws VolleyError {
//
//            }
//        });
//
//        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
//        rq.add(onCartRequest);

    }

    public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {

        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        //Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        //Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {

        //Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            //Calculate the largest inSampleSize value that is a power of 2 and keeps both
            //height and width larger than the requested height and width.

            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }


}
