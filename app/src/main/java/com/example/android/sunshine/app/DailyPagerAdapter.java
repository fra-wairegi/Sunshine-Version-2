package com.example.android.sunshine.app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.android.sunshine.app.data.WeatherContract;

import org.threeten.bp.DayOfWeek;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;


/**
 * Created by franciswairegi on 10/24/17.
 */

public class DailyPagerAdapter extends FragmentPagerAdapter {

    // private String tabTitles[] = new String [] {"TODAY","TOMORROW","THURSDAY","FRIDAY"};

    private ArrayList<String> mTabTitlesList = new ArrayList<String>();

    private String[] mTabTitles = new String[mTabTitlesList.size()];

    private Context mContext;


    private void populateTabTitles() {
        mTabTitlesList.add("TODAY");
        mTabTitlesList.add("TOMORROW");
        for (int i = 2; i < 7; i++) {
            LocalDate localDate = LocalDate.now().plusDays(i);
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            mTabTitlesList.add(dayOfWeek.toString());
        }
    }


    public DailyPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        String locationSetting = Utility.getPreferredLocation(mContext);
       // if (position == 0) {
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(position);

//            Uri detailUri = WeatherContract.WeatherEntry.buildWeatherLocationWithDate(
//                    locationSetting, cursor.getLong(COL_WEATHER_DATE);

        Log.v("DailyPagerAdapter","LocationSetting -> "+ locationSetting+" || Date -> "+
                localDateTime.toString()+ " || Position -> " +position);

            Uri detailUri = WeatherContract.WeatherEntry.buildWeatherLocationWithDate(
                    locationSetting, Utility.convertLocalDateToLong(localDateTime));

            Bundle arguments = new Bundle();
            arguments.putParcelable(DetailFragment.DETAIL_URI, detailUri);

            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);

            //return new DetailFragment();
            return fragment;
//        } else if (position == 1) {
//            return new DetailFragment();
//        } else if (position == 2) {
//            return new DetailFragment();
//        } else if (position == 3) {
//            return new DetailFragment();
//        } else if (position == 4) {
//            return new DetailFragment();
//        } else if (position == 5) {
//            return new DetailFragment();
        //} else {
          //  return new DetailFragment();
        //}
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        populateTabTitles();
        mTabTitles = mTabTitlesList.toArray(mTabTitles);
        return mTabTitles[position];
    }
}
