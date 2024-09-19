package vn.edu.usth.flightinfoapp.findFlight;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import vn.edu.usth.flightinfoapp.R;

public class FlightDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_detail, container, false);

        // Initialize the back arrow
        ImageView backArrow = view.findViewById(R.id.btn_back);

        // Set click listener for the back arrow
        backArrow.setOnClickListener(v -> {
            // Pop the current fragment from the back stack, navigating back to the previous fragment
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }
}