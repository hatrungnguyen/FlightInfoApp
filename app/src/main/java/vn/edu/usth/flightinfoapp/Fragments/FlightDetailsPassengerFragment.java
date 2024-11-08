package vn.edu.usth.flightinfoapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Locale;

import vn.edu.usth.flightinfoapp.R;
import vn.edu.usth.flightinfoapp.model.Flight;

public class FlightDetailsPassengerFragment extends Fragment {
    private static final String ARG_FLIGHT = "flight";

    private TextView textViewFlightNumber, textViewAircraftModel, textViewDepartureCity, textViewDepartureDate,
            textViewDepartureTime, textViewArrivalCity, textViewArrivalDate, textViewArrivalTime,
            textViewBookingOpenDate, textViewDuration, textViewMaxNumOfSeats, textViewFrequency,
            textViewExtraBaggagePrice, textViewEconomyPrice, textViewBusinessPrice;
    private Button reserveButton, cancelButton;
    private LinearLayout summaryContainer;
    private Flight flight;

    public FlightDetailsPassengerFragment() {}

    public static FlightDetailsPassengerFragment newInstance(Flight flight) {
        FlightDetailsPassengerFragment fragment = new FlightDetailsPassengerFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_FLIGHT, flight);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            flight = (Flight) getArguments().getSerializable(ARG_FLIGHT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flight_details_passenger, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        displayFlightDetails();
    }

    private void findViews(View view) {
        textViewFlightNumber = view.findViewById(R.id.textViewFlightNumber);
        textViewAircraftModel = view.findViewById(R.id.textViewAircraftModel);
        textViewDepartureCity = view.findViewById(R.id.textViewDepartureCity);
        textViewDepartureDate = view.findViewById(R.id.textViewDepartureDate);
        textViewDepartureTime = view.findViewById(R.id.textViewDepartureTime);
        textViewArrivalCity = view.findViewById(R.id.textViewArrivalCity);
        textViewArrivalDate = view.findViewById(R.id.textViewArrivalDate);
        textViewArrivalTime = view.findViewById(R.id.textViewArrivalTime);
        textViewBookingOpenDate = view.findViewById(R.id.textViewBookingOpenDate);
        textViewDuration = view.findViewById(R.id.textViewDuration);
        textViewMaxNumOfSeats = view.findViewById(R.id.textViewMaxNumOfSeats);
        textViewExtraBaggagePrice = view.findViewById(R.id.textViewExtraBaggage);
        textViewEconomyPrice = view.findViewById(R.id.textViewEconomy);
        textViewBusinessPrice = view.findViewById(R.id.textViewBusiness);
        textViewFrequency = view.findViewById(R.id.textViewFrequency);
        cancelButton = view.findViewById(R.id.buttonCancel);
        summaryContainer = view.findViewById(R.id.summaryContainer);

        cancelButton.setOnClickListener(v -> {
            AllFlightsFragment fragment = new AllFlightsFragment();

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

    private void displayFlightDetails() {
        if (flight == null) {
            Toast.makeText(getContext(), "Flight details not available", Toast.LENGTH_SHORT).show();
            return;
        }

        textViewFlightNumber.setText(flight.getFlight() != null ? flight.getFlight().getNumber() : "N/A");
        textViewAircraftModel.setText(flight.getAirline() != null ? flight.getAirline().getName() : "N/A");

        if (flight.getDeparture() != null) {
            textViewDepartureCity.setText(flight.getDeparture().getAirport() != null ? flight.getDeparture().getAirport() : "N/A");
            textViewDepartureDate.setText(parseDate(flight.getDeparture().getScheduled(), "yyyy-MM-dd"));
            textViewDepartureTime.setText(parseDate(flight.getDeparture().getScheduled(), "HH:mm"));
        }

        if (flight.getArrival() != null) {
            textViewArrivalCity.setText(flight.getArrival().getAirport() != null ? flight.getArrival().getAirport() : "N/A");
            textViewArrivalDate.setText(parseDate(flight.getArrival().getScheduled(), "yyyy-MM-dd"));
            textViewArrivalTime.setText(parseDate(flight.getArrival().getScheduled(), "HH:mm"));
        }

        textViewBookingOpenDate.setText(parseDate(flight.getFlightDate(), "yyyy-MM-dd"));

        textViewExtraBaggagePrice.setText("N/A");
        textViewEconomyPrice.setText("N/A");
        textViewBusinessPrice.setText("N/A");

        textViewFrequency.setText("N/A");

    }

    private String parseDate(String dateTime, String pattern) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            SimpleDateFormat outputFormat = new SimpleDateFormat(pattern, Locale.getDefault());
            return outputFormat.format(sdf.parse(dateTime));
        } catch (Exception e) {
            e.printStackTrace();
            return dateTime;
        }
    }
}