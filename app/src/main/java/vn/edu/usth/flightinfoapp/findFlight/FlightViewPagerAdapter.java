package vn.edu.usth.flightinfoapp.findFlight;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FlightViewPagerAdapter extends FragmentStatePagerAdapter {

    public FlightViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CitiesFragment();
            case 1:
                return new FlightNumberFragment();
            default:
                return new CitiesFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Cities";
            case 1:
                return "Flight Number";
            default:
                return "Cities";
        }
    }
}

