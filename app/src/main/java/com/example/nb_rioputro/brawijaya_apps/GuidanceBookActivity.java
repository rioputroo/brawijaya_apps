package com.example.nb_rioputro.brawijaya_apps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;

public class GuidanceBookActivity extends AppCompatActivity {
    ImageButton imbBack;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance_book);

        imbBack = (ImageButton) findViewById(R.id.imbBack);
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guidance_book, menu);
        return true;
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //returning current tabs
            switch (position){
                case 0:
                    Guidance1 guidance1 = new Guidance1();
                    return guidance1;
                case 1:
                    Guidance2 guidance2 = new Guidance2();
                    return guidance2;
                case 2:
                    Guidance3 guidance3 = new Guidance3();
                    return guidance3;
                case 3:
                    Guidance4 guidance4 = new Guidance4();
                    return guidance4;
                case 4:
                    Guidance5 guidance5 = new Guidance5();
                    return guidance5;
                case 5:
                    Guidance6 guidance6 = new Guidance6();
                    return guidance6;
                case 6:
                    Guidance7 guidance7 = new Guidance7();
                    return guidance7;
                case 7:
                    Guidance8 guidance8 = new Guidance8();
                    return guidance8;
                case 8:
                    Guidance9 guidance9 = new Guidance9();
                    return guidance9;
                case 9:
                    Guidance10 guidance10 = new Guidance10();
                    return guidance10;
                case 10:
                    Guidance11 guidance11 = new Guidance11();
                    return guidance11;
                case 11:
                    Guidance12 guidance12 = new Guidance12();
                    return guidance12;
                case 12:
                    Guidance13 guidance13 = new Guidance13();
                    return guidance13;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 13 total pages.
            return 13;
        }
    }
}
