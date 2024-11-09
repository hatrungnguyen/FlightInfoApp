package vn.edu.usth.flightinfoapp.Fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import vn.edu.usth.flightinfoapp.R;
import vn.edu.usth.flightinfoapp.database.SharedPrefManager;
import vn.edu.usth.flightinfoapp.model.Flight;
import vn.edu.usth.flightinfoapp.utils.FlightAdapter;

import okhttp3.Request;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AllFlightsFragment extends Fragment {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private SharedPrefManager sharedPrefManager;
    private RecyclerView recyclerView;
    private FlightAdapter flightAdapter;
    private ProgressBar progressBar;
    private TextInputEditText editDepartureCity, editArrivalCity, editDepartureDate;
    private Button buttonFilter;
    private List<Flight> allFlights = new ArrayList<>();
    private static final String URLAPI = "https://api.aviationstack.com/v1/flights?access_key=8676e9085b95b51763f64bc0950f68b7";

    public AllFlightsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_all_flights, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPrefManager = SharedPrefManager.getInstance(getContext());
        sharedPrefManager.writeToolbarTitle("All Flights");

        findAndSetupViews();

        fetchFlightsFromApi();
    }

    private void findAndSetupViews() {
        editDepartureCity = getView().findViewById(R.id.editDepartureCity);
        editArrivalCity = getView().findViewById(R.id.editArrivalCity);
        editDepartureDate = getView().findViewById(R.id.editDepartureDate);
        recyclerView = getView().findViewById(R.id.recyclerView);
        buttonFilter = getView().findViewById(R.id.buttonFilter);

        progressBar = new ProgressBar(getContext());
        progressBar.setVisibility(View.GONE);
        ((ViewGroup) getView()).addView(progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        flightAdapter = new FlightAdapter(new ArrayList<>(), flight -> openFlightDetailsFragment(flight));
        recyclerView.setAdapter(flightAdapter);

        setupDatePickers(editDepartureDate);

        buttonFilter.setOnClickListener(v -> filterFlights());
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupDatePickers(TextInputEditText dateInput) {
        dateInput.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(getContext(), (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    dateInput.setText(dateFormat.format(calendar.getTime()));
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
                v.performClick();
            }
            return false;
        });
    }

    private void openFlightDetailsFragment(Flight flightId) {
        FlightDetailsPassengerFragment detailsFragment = FlightDetailsPassengerFragment.newInstance(flightId);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void fetchFlightsFromApi() {
        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URLAPI).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("API", "Flight data analysis error", e);
                getActivity().runOnUiThread(() -> progressBar.setVisibility(View.GONE));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();
                    allFlights = parseFlights(responseBody);
                    if (allFlights != null) {
                        getActivity().runOnUiThread(() -> {
                            flightAdapter.updateList(allFlights);
                            progressBar.setVisibility(View.GONE);
                        });
                    }
                }
            }
        });
    }

    private List<Flight> parseFlights(String json) {
        try {
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            Type listType = new TypeToken<List<Flight>>() {}.getType();
            return new Gson().fromJson(jsonObject.get("data"), listType);
        } catch (Exception e) {
            Log.e("API", "Flight data analysis error", e);
            return null;
        }
    }

    private void filterFlights() {
        String filterDepartureCity = editDepartureCity.getText().toString().trim().toLowerCase();
        String filterArrivalCity = editArrivalCity.getText().toString().trim().toLowerCase();
        String filterDepartureDate = editDepartureDate.getText().toString().trim();

        List<Flight> filteredFlights = new ArrayList<>();

        for (Flight flight : allFlights) {
            boolean matchesDepartureCity = filterDepartureCity.isEmpty() ||
                    (flight.getDeparture() != null && flight.getDeparture().getAirport() != null && flight.getDeparture().getAirport().toLowerCase().contains(filterDepartureCity)) ||
                    (flight.getDeparture() != null && flight.getDeparture().getIata() != null && flight.getDeparture().getIata().toLowerCase().equals(filterDepartureCity));

            boolean matchesArrivalCity = filterArrivalCity.isEmpty() ||
                    (flight.getArrival() != null && flight.getArrival().getAirport() != null && flight.getArrival().getAirport().toLowerCase().contains(filterArrivalCity)) ||
                    (flight.getArrival() != null && flight.getArrival().getIata() != null && flight.getArrival().getIata().toLowerCase().equals(filterArrivalCity));

            boolean matchesDepartureDate = filterDepartureDate.isEmpty() ||
                    (flight.getDeparture() != null && flight.getDeparture().getScheduled() != null && flight.getDeparture().getScheduled().startsWith(filterDepartureDate));

            if (matchesDepartureCity && matchesArrivalCity && matchesDepartureDate) {
                filteredFlights.add(flight);
            }
        }

        flightAdapter.updateList(filteredFlights);
    }
}