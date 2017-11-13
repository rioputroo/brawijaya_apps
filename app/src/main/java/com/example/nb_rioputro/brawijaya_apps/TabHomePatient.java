package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 11/6/2017.
 */

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class TabHomePatient extends Fragment {
    Button btnFoodOrder, btnMyOrder, btnCall, btnLogout, btnReview;
    String mId;
    ImageView cardFood, cardPharmacy, cardToys, cardRoomServices, cardGuidanceBook, cardLogout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_home_patient, container, false);

        cardFood = (ImageView) rootView.findViewById(R.id.imageView5);
        cardPharmacy = (ImageView) rootView.findViewById(R.id.imageView6);
        cardToys = (ImageView) rootView.findViewById(R.id.imageView7);
        cardRoomServices = (ImageView) rootView.findViewById(R.id.imageView8);
        cardGuidanceBook = (ImageView) rootView.findViewById(R.id.imageView9);
        cardLogout = (ImageView) rootView.findViewById(R.id.imageView10);

        cardFood.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.indonesian_2, 100, 100));
        cardPharmacy.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.home_pharmacy, 100, 100));
        cardToys.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.home_toys2, 100, 100));
        cardRoomServices.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.home_housekeeping, 100, 100));
        cardGuidanceBook.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.guidance_book, 100, 100));
        cardLogout.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.logout_baru, 100, 100));

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogout();
            }
        });


//        Picasso.with(getActivity().getApplicationContext()).load(R.drawable.indonesian_2).into(cardFood);
//        Picasso.with(getActivity().getApplicationContext()).load(R.drawable.home_pharmacy).into(cardPharmacy);
//        Picasso.with(getActivity().getApplicationContext()).load(R.drawable.home_toys2).into(cardToys);


//
//        btnFoodOrder = (Button) rootView.findViewById(R.id.btnFoodOrder);
//        btnFoodOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                orderFood();
//            }
//        });
//
//        btnMyOrder = (Button) rootView.findViewById(R.id.btnMyOrder);
//        btnMyOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myOrder();
//            }
//        });
//
//        btnCall = (Button) rootView.findViewById(R.id.btnCall);
//        btnCall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                contactnurse();
//            }
//        });
//
//        btnReview = (Button) rootView.findViewById(R.id.btnReview);
//        btnReview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                review();
//            }
//        });
//
//        btnLogout = (Button) rootView.findViewById(R.id.btnLogout);
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                userLogout();
//            }
//        });

        return rootView;
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

    private void contactnurse() {
        Intent contactIntent = new Intent(getActivity(), ContactActivity.class);
        startActivity(contactIntent);
    }

    private void review() {
        Intent reviewIntent = new Intent(getActivity(), ReviewActivity.class);
        startActivity(reviewIntent);
    }

    private void myOrder() {
        Intent myOrderIntent = new Intent(getActivity(), MyOrderActivity.class);
        startActivity(myOrderIntent);
    }

    private void orderFood() {
        Intent orderIntent = new Intent(getActivity(), FoodOrderActivity.class);
        startActivity(orderIntent);

    }

    private void userLogout() {
        AlertDialog.Builder adLogout = new AlertDialog.Builder(getActivity());
        adLogout.setMessage("Are you sure you want to logout? ");
        adLogout.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences spLogout = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = spLogout.edit();

                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);
                editor.putString(Config.ID_SHARED_PREF, "");
                editor.putString(Config.USERNAME_SHARED_PREF, "");
                editor.putString(Config.NAME_SHARED_PREF, "");
                editor.putString(Config.ROLE_SHARED_PREF, "");

                editor.commit();

                Intent logoutIntent = new Intent(getActivity(), LoginActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logoutIntent);
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


}
