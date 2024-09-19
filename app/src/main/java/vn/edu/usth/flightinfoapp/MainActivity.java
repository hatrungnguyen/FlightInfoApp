package vn.edu.usth.flightinfoapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.flightinfoapp.findFlight.CitiesFragment;
import vn.edu.usth.flightinfoapp.findFlight.FlightNumberFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
        }

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, new CitiesFragment())
//                    .commit();
//        }
//
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_container, new FlightNumberFragment())
//                    .commit();
//        }
    }



}
