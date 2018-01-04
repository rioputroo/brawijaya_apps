package com.example.nb_rioputro.brawijaya_apps;

/**
 * Created by nb-rioputro on 12/17/2017.
 */

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Guidance6 extends Fragment {
    ImageView miniJuniorSuiteRoom, deluxePlusRoom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.guidance6, container, false);

        miniJuniorSuiteRoom = (ImageView) rootView.findViewById(R.id.ivMiniJuniorSuiteRoom);
        deluxePlusRoom = (ImageView) rootView.findViewById(R.id.ivDeluxePlusRoom);

        miniJuniorSuiteRoom.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.mini_junior_suite_room, 300, 300));
        deluxePlusRoom.setImageBitmap(decodeSampleBitmapFromResource(getResources(), R.drawable.deluxe_plus_room, 300, 300));

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
}
