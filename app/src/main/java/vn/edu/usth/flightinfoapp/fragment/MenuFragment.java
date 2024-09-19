package vn.edu.usth.flightinfoapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.edu.usth.flightinfoapp.R;
import vn.edu.usth.flightinfoapp.fragment.ViewPagerAdapter;

public class MenuFragment extends Fragment {

    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private BottomNavigationView bottomNavigationView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        viewPager = view.findViewById(R.id.view_pager);
        bottomNavigationView = view.findViewById(R.id.bottombar);

        viewPagerAdapter = new ViewPagerAdapter(getActivity());
        viewPager.setAdapter(viewPagerAdapter);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.home_button) {
                viewPager.setCurrentItem(0);
            } else if (itemId == R.id.noti_button) {
                viewPager.setCurrentItem(1);
            } else if (itemId == R.id.profile_button) {
                viewPager.setCurrentItem(2);
            }
            return false;
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.home_button);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.noti_button);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.profile_button);
                        break;
                }
            }
        });

        return view;
    }
}