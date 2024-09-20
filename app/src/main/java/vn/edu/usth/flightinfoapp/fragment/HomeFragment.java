package vn.edu.usth.flightinfoapp.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;

import vn.edu.usth.flightinfoapp.R;
import vn.edu.usth.flightinfoapp.findFlight.FlightNumberFragment;
import vn.edu.usth.flightinfoapp.findFlight.FlightViewPagerAdapter;
import vn.edu.usth.flightinfoapp.widget.CustomViewPager;


public class HomeFragment extends Fragment {

    public HomeFragment() {
    }

    private TabLayout tabLayout;
    private CustomViewPager viewPager;
    private View mView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        tabLayout = mView.findViewById(R.id.tab_layout);
        viewPager = mView.findViewById(R.id.cities_viewpager);

        FlightViewPagerAdapter adapter = new FlightViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);

        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                View customTabView = LayoutInflater.from(getContext()).inflate(R.layout.tab_custom, null);
                TextView tabText = customTabView.findViewById(R.id.tab_text);
                switch (i) {
                    case 0:
                        tabText.setText("Cities");
                        break;
                    case 1:
                        tabText.setText("Flight Number");
                        break;
                }
                tab.setCustomView(customTabView);
            }
        }
        return mView;
    }

}