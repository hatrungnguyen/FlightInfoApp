package vn.edu.usth.flightinfoapp.findFlight;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;

import vn.edu.usth.flightinfoapp.MainActivity;
import vn.edu.usth.flightinfoapp.R;

public class FlightNumberFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flight_number, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton searchFlightButton = view.findViewById(R.id.search_flight_button);
        searchFlightButton.setOnClickListener(v -> {
            ((MainActivity) getActivity()).showFlightDetailFragment();
        });
    }
}