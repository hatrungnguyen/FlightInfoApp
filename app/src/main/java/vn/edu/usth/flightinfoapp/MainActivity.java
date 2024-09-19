package vn.edu.usth.flightinfoapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import vn.edu.usth.flightinfoapp.findFlight.FlightByCityFragment;
import vn.edu.usth.flightinfoapp.findFlight.FlightDetailFragment;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new LoginFragment());
            transaction.commit();
        }
    }

    public void showFlightByCityFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new FlightByCityFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void showFlightDetailFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new FlightDetailFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}


