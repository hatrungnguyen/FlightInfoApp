package vn.edu.usth.flightinfoapp.tasks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.flightinfoapp.model.Flight;

public class FlightJsonParser {
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static List<Flight> getObjectFromJson(String json) {
        List<Flight> flights = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                Flight flight = new Flight();

                flights.add(flight);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return flights;
    }
}
