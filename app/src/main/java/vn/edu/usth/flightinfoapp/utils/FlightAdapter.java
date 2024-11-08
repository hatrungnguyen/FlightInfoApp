package vn.edu.usth.flightinfoapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.usth.flightinfoapp.R;
import vn.edu.usth.flightinfoapp.model.Flight;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {
    private List<Flight> flightList;
    private OnItemClickListener onItemClickListener;

    public FlightAdapter(List<Flight> flightList, OnItemClickListener onItemClickListener) {
        this.flightList = flightList;
        this.onItemClickListener = onItemClickListener;
    }

    public void updateList(List<Flight> updatedList) {
        flightList = updatedList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_card, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flight flight = flightList.get(position);

        holder.textViewFlightNumber.setText(flight.getFlight().getNumber());
        holder.textViewFrom.setText(flight.getDeparture().getAirport());
        holder.textViewDeparture.setText("Departure on " + flight.getDeparture().getScheduled());
        holder.textViewTo.setText(flight.getArrival().getAirport());
        holder.textViewArrival.setText("Arrival on " + flight.getArrival().getScheduled());

        // Set click listener for the item
        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(flight));
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    // Define a custom OnItemClickListener interface
    public interface OnItemClickListener {
        void onItemClick(Flight flight);
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFlightNumber, textViewFrom, textViewDeparture, textViewTo, textViewArrival;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFlightNumber = itemView.findViewById(R.id.textViewFlightNumber);
            textViewFrom = itemView.findViewById(R.id.textViewFrom);
            textViewDeparture = itemView.findViewById(R.id.textViewDeparture);
            textViewTo = itemView.findViewById(R.id.textViewTo);
            textViewArrival = itemView.findViewById(R.id.textViewArrival);
        }
    }
}