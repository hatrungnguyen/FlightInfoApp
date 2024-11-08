package vn.edu.usth.flightinfoapp.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import vn.edu.usth.flightinfoapp.R;
import vn.edu.usth.flightinfoapp.model.Flight;
import vn.edu.usth.flightinfoapp.utils.FlightAdapter;


public class HomeFragment extends Fragment {
    private static final String URLAPI = "https://api.aviationstack.com/v1/flights?access_key=db1a19dd8f507caa7e8a9e2f127913cc&fbclid=IwY2xjawGaulhleHRuA2FlbQIxMAABHXWwd2RMjLNVmADwGUHgulVxfCOE6pFpLMbFz2rCY55saY6acrdOezYcMw_aem_Y8JZXxhKbECxghcZWrtOLg";
    private RecyclerView recyclerView;
    private FlightAdapter flightAdapter;
    private ProgressBar progressBar;
    private List<Flight> flightList = new ArrayList<>();

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar); // Assuming you have a ProgressBar in the layout

        setupRecyclerView();
        fetchFlightsFromApi();
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        flightAdapter = new FlightAdapter(flightList, flight -> openFlightDetails(flight)); // Define the click action
        recyclerView.setAdapter(flightAdapter);
    }

    private void fetchFlightsFromApi() {
        progressBar.setVisibility(View.VISIBLE);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(URLAPI).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("API", "Error fetching flights", e);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> progressBar.setVisibility(View.GONE));
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();
                    flightList = parseFlights(responseBody);
                    if (flightList != null) {
                        getActivity().runOnUiThread(() -> {
                            flightAdapter.updateList(flightList);
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
            Log.e("API", "Error parsing flights", e);
            return null;
        }
    }

    private void openFlightDetails(Flight flight) {
        FlightDetailsPassengerFragment detailsFragment = FlightDetailsPassengerFragment.newInstance(flight);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, detailsFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}