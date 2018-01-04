package com.example.nb_rioputro.brawijaya_apps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.w3c.dom.Text;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    ImageView cardFood, cardPharmacy, cardContacts, cardRoomServices, cardGuidanceBook, cardLogout, cardSurvey;
    private SliderLayout mDemoSlider;
    int cartCount;

    CardView cvHousekeeping, cvSurvey;


    ImageButton cartBtn, historyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        TextView count = (TextView) findViewById(R.id.textCount);

        cvHousekeeping = (CardView) findViewById(R.id.cardView14);
        cvSurvey = (CardView) findViewById(R.id.cardView18);

        SharedPreferences spUser = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String nurse = spUser.getString(Config.ROLE_SHARED_PREF, "cant getting role");

        if (nurse.equalsIgnoreCase("Nurse")) {
            cvHousekeeping.setVisibility(View.GONE);
            cvSurvey.setVisibility(View.GONE);
        }

        for (Item item : NewCart.contents()) {
            int jumlahOrder = item.getQuantity();
            cartCount += jumlahOrder;
            count.setVisibility(View.VISIBLE);
            count.setText(String.valueOf(cartCount));
        }

        Log.d("cartCount ", String.valueOf(cartCount));

        cartBtn = (ImageButton) findViewById(R.id.cartBtnImage);
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fetchCart = new Intent(MainActivity.this, MyOrderActivity.class);
                startActivity(fetchCart);
            }
        });

        historyBtn = (ImageButton) findViewById(R.id.historyBtnImage);
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fetchHistory = new Intent(MainActivity.this, HistoryOrderActivity.class);
                startActivity(fetchHistory);
            }
        });

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Banner1", R.drawable.banner_1);
        file_maps.put("Banner2", R.drawable.banner_2);
        file_maps.put("Banner3", R.drawable.banner_3);
        file_maps.put("Banner4", R.drawable.banner_4);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Fade);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

        cardFood = (ImageView) findViewById(R.id.imageView5);
        cardPharmacy = (ImageView) findViewById(R.id.imageView6);
        cardContacts = (ImageView) findViewById(R.id.imageView11);
        cardRoomServices = (ImageView) findViewById(R.id.imageView8);
        cardGuidanceBook = (ImageView) findViewById(R.id.imageView9);
        cardLogout = (ImageView) findViewById(R.id.imageView10);
        cardSurvey = (ImageView) findViewById(R.id.imageView12);

        cardFood.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.indonesian_2, 100, 100));
        cardPharmacy.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.home_pharmacy, 100, 100));
        cardContacts.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.contacts, 100, 100));
        cardRoomServices.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.home_housekeeping, 100, 100));
        cardGuidanceBook.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.guidance_book, 100, 100));
        cardLogout.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.logout_baru, 100, 100));
        cardSurvey.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.survey2, 100, 100));


        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogout();
            }
        });

        cardContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectContacts();
            }
        });

        cardSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchSurveyForm();
            }
        });

        cardFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getFood = new Intent(MainActivity.this, FoodOrderActivity.class);
                startActivity(getFood);
            }
        });

        cardGuidanceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getGuidance = new Intent(MainActivity.this, GuidanceBookActivity.class);
                startActivity(getGuidance);
            }
        });

        cardPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getPharmacy = new Intent(MainActivity.this, PharmacyOrderActivity.class);
                startActivity(getPharmacy);
            }
        });

    }

    private void fetchSurveyForm() {
        Intent fetchSurveyForm = new Intent(this, SurveyFormActivity.class);
        startActivity(fetchSurveyForm);
    }

    private void redirectContacts() {
        Intent redirectContacts = new Intent(this, ContactsActivity.class);
        startActivity(redirectContacts);
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

    private void userLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sign Out");
        builder.setMessage("Are you sure you want to sign out? Make sure you have completed any transactions before.");

        // add a button
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (getSinchServiceInterface() != null) {
                    getSinchServiceInterface().stopClient();
                }

                SharedPreferences spLogout = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spLogout.edit();

                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
                editor.putString(Config.ID_SHARED_PREF, "");
                editor.putString(Config.USERNAME_SHARED_PREF, "");
                editor.putString(Config.NAME_SHARED_PREF, "");
                editor.putString(Config.ROLE_SHARED_PREF, "");
                editor.putString(Config.ROOM_SHARED_PREF, "");
                editor.putString(Config.REGID_SHARED_PREF, "");

                editor.commit();

                Intent logoutIntent = new Intent(getApplicationContext(), LoginActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
            }
        });
        builder.setNegativeButton("Cancel", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
