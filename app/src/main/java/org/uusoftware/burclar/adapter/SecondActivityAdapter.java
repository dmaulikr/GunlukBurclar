package org.uusoftware.burclar.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.uusoftware.burclar.FragmentEighth;
import org.uusoftware.burclar.FragmentFifth;
import org.uusoftware.burclar.FragmentNineth;
import org.uusoftware.burclar.FragmentSeventh;
import org.uusoftware.burclar.FragmentSixth;

public class SecondActivityAdapter extends FragmentPagerAdapter {

    private Fragment fragment = null;

    public SecondActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragment = new FragmentFifth();
                break;
            case 1:
                fragment = new FragmentSixth();
                break;
            case 2:
                fragment = new FragmentSeventh();
                break;
            case 3:
                fragment = new FragmentEighth();
                break;
            case 4:
                fragment = new FragmentNineth();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Genel";
            case 1:
                return "Günlük";
            case 2:
                return "Haftalık";
            case 3:
                return "Aylık";
            case 4:
                return "Yıllık";
        }
        return null;
    }
}