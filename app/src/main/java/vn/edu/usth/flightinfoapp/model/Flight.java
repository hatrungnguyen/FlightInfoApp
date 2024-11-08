package vn.edu.usth.flightinfoapp.model;

import java.io.Serializable;

public class Flight implements Serializable {
    private String flightDate;
    private String flightStatus;
    private Departure departure;
    private Arrival arrival;
    private Airline airline;
    private FlightDetails flight;

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Arrival getArrival() {
        return arrival;
    }

    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public FlightDetails getFlight() {
        return flight;
    }

    public void setFlight(FlightDetails flight) {
        this.flight = flight;
    }

    // Inner classes to represent nested JSON structures
    public static class Departure {
        private String airport;
        private String timezone;
        private String iata;
        private String icao;
        private String terminal;
        private String gate;
        private String scheduled;
        private String estimated;

        public String getAirport() {
            return airport;
        }

        public void setAirport(String airport) {
            this.airport = airport;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getIata() {
            return iata;
        }

        public void setIata(String iata) {
            this.iata = iata;
        }

        public String getIcao() {
            return icao;
        }

        public void setIcao(String icao) {
            this.icao = icao;
        }

        public String getTerminal() {
            return terminal;
        }

        public void setTerminal(String terminal) {
            this.terminal = terminal;
        }

        public String getGate() {
            return gate;
        }

        public void setGate(String gate) {
            this.gate = gate;
        }

        public String getScheduled() {
            return scheduled;
        }

        public void setScheduled(String scheduled) {
            this.scheduled = scheduled;
        }

        public String getEstimated() {
            return estimated;
        }

        public void setEstimated(String estimated) {
            this.estimated = estimated;
        }
    }

    public static class Arrival {
        private String airport;
        private String timezone;
        private String iata;
        private String icao;
        private String terminal;
        private String gate;
        private String scheduled;
        private String estimated;

        public String getAirport() {
            return airport;
        }

        public void setAirport(String airport) {
            this.airport = airport;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getIata() {
            return iata;
        }

        public void setIata(String iata) {
            this.iata = iata;
        }

        public String getIcao() {
            return icao;
        }

        public void setIcao(String icao) {
            this.icao = icao;
        }

        public String getTerminal() {
            return terminal;
        }

        public void setTerminal(String terminal) {
            this.terminal = terminal;
        }

        public String getGate() {
            return gate;
        }

        public void setGate(String gate) {
            this.gate = gate;
        }

        public String getScheduled() {
            return scheduled;
        }

        public void setScheduled(String scheduled) {
            this.scheduled = scheduled;
        }

        public String getEstimated() {
            return estimated;
        }

        public void setEstimated(String estimated) {
            this.estimated = estimated;
        }
    }

    public static class Airline {
        private String name;
        private String iata;
        private String icao;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIata() {
            return iata;
        }

        public void setIata(String iata) {
            this.iata = iata;
        }

        public String getIcao() {
            return icao;
        }

        public void setIcao(String icao) {
            this.icao = icao;
        }
    }

    public static class FlightDetails {
        private String number;
        private String iata;
        private String icao;
        private Codeshare codeshared;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getIata() {
            return iata;
        }

        public void setIata(String iata) {
            this.iata = iata;
        }

        public String getIcao() {
            return icao;
        }

        public void setIcao(String icao) {
            this.icao = icao;
        }

        public Codeshare getCodeshared() {
            return codeshared;
        }

        public void setCodeshared(Codeshare codeshared) {
            this.codeshared = codeshared;
        }
    }

    public static class Codeshare {
        private String airlineName;
        private String airlineIata;
        private String airlineIcao;
        private String flightNumber;
        private String flightIata;
        private String flightIcao;

        public String getAirlineName() {
            return airlineName;
        }

        public void setAirlineName(String airlineName) {
            this.airlineName = airlineName;
        }

        public String getAirlineIata() {
            return airlineIata;
        }

        public void setAirlineIata(String airlineIata) {
            this.airlineIata = airlineIata;
        }

        public String getAirlineIcao() {
            return airlineIcao;
        }

        public void setAirlineIcao(String airlineIcao) {
            this.airlineIcao = airlineIcao;
        }

        public String getFlightNumber() {
            return flightNumber;
        }

        public void setFlightNumber(String flightNumber) {
            this.flightNumber = flightNumber;
        }

        public String getFlightIata() {
            return flightIata;
        }

        public void setFlightIata(String flightIata) {
            this.flightIata = flightIata;
        }

        public String getFlightIcao() {
            return flightIcao;
        }

        public void setFlightIcao(String flightIcao) {
            this.flightIcao = flightIcao;
        }
    }
}
