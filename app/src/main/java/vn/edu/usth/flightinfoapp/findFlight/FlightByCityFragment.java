package vn.edu.usth.flightinfoapp.findFlight;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

import vn.edu.usth.flightinfoapp.MainActivity;
import vn.edu.usth.flightinfoapp.R;


public class FlightByCityFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_by_city, container, false);

        ImageView backArrow = view.findViewById(R.id.btn_back);


        backArrow.setOnClickListener(v -> {
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MaterialButton searchFlightButton = view.findViewById(R.id.details1);
        searchFlightButton.setOnClickListener(v -> {
            ((MainActivity) getActivity()).showFlightDetailFragment();
        });
        MaterialButton searchFlightButton1 = view.findViewById(R.id.details2);
        searchFlightButton1.setOnClickListener(v -> {
            ((MainActivity) getActivity()).showFlightDetailFragment();
        });
        MaterialButton searchFlightButton2 = view.findViewById(R.id.details3);
        searchFlightButton2.setOnClickListener(v -> {
            ((MainActivity) getActivity()).showFlightDetailFragment();
        });

    }


}