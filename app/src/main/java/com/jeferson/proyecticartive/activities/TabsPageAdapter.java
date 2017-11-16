package com.jeferson.proyecticartive.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jeferson.proyecticartive.activities.fragments.CanceladoFragment;
import com.jeferson.proyecticartive.activities.fragments.CompletadoFragment;

/**
 * Created by JARVIS on 13/11/2017.
 */

public class TabsPageAdapter extends FragmentPagerAdapter {

    int tabCount;

    public TabsPageAdapter(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount= tabCount;

    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return  tabCount;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CompletadoFragment();
            case 1:
                return new CanceladoFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Completados";
            case 1:
                return "Cancelados";
            case 2:
        }
        return null;
    }

}
