package vn.edu.usth.flightinfoapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import vn.edu.usth.flightinfoapp.model.Flight;
import vn.edu.usth.flightinfoapp.model.Passenger;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GreenHill.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USER(" +
                "EMAIL TEXT PRIMARY KEY, " +
                "PHONE TEXT, " +
                "FIRST_NAME TEXT, " +
                "LAST_NAME TEXT, " +
                "HASHED_PASSWORD TEXT, " +
                "ROLE TEXT, " +
                "PASSPORT_NUMBER TEXT, " +
                "PASSPORT_ISSUE_DATE TEXT, " +
                "PASSPORT_ISSUE_PLACE TEXT, " +
                "PASSPORT_EXPIRATION_DATE TEXT, " +
                "FOOD_PREFERENCE TEXT, " +
                "DATE_OF_BIRTH TEXT, " +
                "NATIONALITY TEXT" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE FLIGHT(" +
                "FLIGHT_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FLIGHT_NUMBER TEXT," +
                "DEPARTURE_CITY TEXT," +
                "DESTINATION_CITY TEXT," +
                "DEPARTURE_DATETIME TEXT," +
                "ARRIVAL_DATETIME TEXT," +
                "DURATION TEXT," +
                "AIRCRAFT_MODEL TEXT," +
                "CURRENT_RESERVATIONS INTEGER," +
                "MAX_SEATS INTEGER," +
                "MISSED_FLIGHTS INTEGER," +
                "BOOKING_OPEN_DATE TEXT," +
                "PRICE_ECONOMY REAL," +
                "PRICE_BUSINESS REAL," +
                "PRICE_EXTRA_BAGGAGE REAL," +
                "IS_RECURRENT TEXT" +
                ");");

        sqLiteDatabase.execSQL("CREATE TABLE RESERVATION(" +
                "RESERVATION_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FLIGHT_ID TEXT, " +
                "PASSENGER_EMAIL TEXT, " +
                "FLIGHT_CLASS TEXT, " +
                "NUM_EXTRA_BAGS INTEGER, " +
                "FOOD_PREFERENCE TEXT, " +
                "PRICE REAL," +
                "FOREIGN KEY(FLIGHT_ID) REFERENCES FLIGHT(FLIGHT_ID), " +
                "FOREIGN KEY(PASSENGER_EMAIL) REFERENCES USER(EMAIL)" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }


    public void insertPassenger(Passenger passenger) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("EMAIL", passenger.getEmail());
        contentValues.put("PHONE", passenger.getPhoneNumber());
        contentValues.put("FIRST_NAME", passenger.getFirstName());
        contentValues.put("LAST_NAME", passenger.getLastName());
        contentValues.put("HASHED_PASSWORD", passenger.getHashedPassword());
        contentValues.put("ROLE", "Passenger");
        contentValues.put("PASSPORT_NUMBER", passenger.getPassportNumber());
        contentValues.put("PASSPORT_ISSUE_DATE", passenger.getPassportIssueDate());
        contentValues.put("PASSPORT_ISSUE_PLACE", passenger.getPassportIssuePlace());
        contentValues.put("PASSPORT_EXPIRATION_DATE", passenger.getPassportExpiryDate());
        contentValues.put("FOOD_PREFERENCE", passenger.getFoodPreference());
        contentValues.put("DATE_OF_BIRTH", passenger.getDateOfBirth());
        contentValues.put("NATIONALITY", passenger.getNationality());

        sqLiteDatabase.insert("USER", null, contentValues);
    }

//    public int insertFlight(Flight flight) {
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//
//
//        long id = sqLiteDatabase.insert("FLIGHT", null, contentValues);
//        sqLiteDatabase.close();
//
//        return (int) id;
//    }

    public int updatePassenger(Passenger passenger, String oldEmail) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("EMAIL", passenger.getEmail());
        contentValues.put("PHONE", passenger.getPhoneNumber());
        contentValues.put("FIRST_NAME", passenger.getFirstName());
        contentValues.put("LAST_NAME", passenger.getLastName());
        contentValues.put("HASHED_PASSWORD", passenger.getHashedPassword());
        contentValues.put("ROLE", "Passenger");


        return sqLiteDatabase.update("USER", contentValues, "EMAIL = ?", new String[]{oldEmail});
    }

    public Cursor getAllFlights() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM FLIGHT " +
                "ORDER BY DEPARTURE_DATETIME ASC ";

        return sqLiteDatabase.rawQuery(query, null);
    }

    public Cursor getFlightsByCityAndDate(String departureCity, String destinationCity, String departureDate, String arrivalDate) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM FLIGHT WHERE 1=1");
        List<String> args = new ArrayList<>();

        if (departureCity != null && !departureCity.isEmpty()) {
            queryBuilder.append(" AND DEPARTURE_CITY = ?");
            args.add(departureCity);
        }

        if (destinationCity != null && !destinationCity.isEmpty()) {
            queryBuilder.append(" AND DESTINATION_CITY = ?");
            args.add(destinationCity);
        }

        if (departureDate != null && !departureDate.isEmpty()) {
            queryBuilder.append(" AND DATE(DEPARTURE_DATETIME) = ?");
            args.add(departureDate);
        }

        if (arrivalDate != null && !arrivalDate.isEmpty()) {
            queryBuilder.append(" AND DATE(ARRIVAL_DATETIME) = ?");
            args.add(arrivalDate);
        }

        return sqLiteDatabase.rawQuery(queryBuilder.toString(), args.toArray(new String[0]));
    }

    public Cursor getFlightsByCityAndDepartureDate(String departureCity, String destinationCity, String departureDate) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM FLIGHT WHERE 1=1");
        List<String> args = new ArrayList<>();

        if (departureCity != null && !departureCity.isEmpty()) {
            queryBuilder.append(" AND DEPARTURE_CITY = ?");
            args.add(departureCity);
        }

        if (destinationCity != null && !destinationCity.isEmpty()) {
            queryBuilder.append(" AND DESTINATION_CITY = ?");
            args.add(destinationCity);
        }

        if (departureDate != null && !departureDate.isEmpty()) {
            queryBuilder.append(" AND DATE(DEPARTURE_DATETIME) = ?");
            args.add(departureDate);
        }

        return sqLiteDatabase.rawQuery(queryBuilder.toString(), args.toArray(new String[0]));
    }

    public Cursor getUsersByEmail(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM USER WHERE  EMAIL =?", new String[]{email});
    }

    public boolean checkUserCredentials(String email, String hashedPassword) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM USER WHERE EMAIL = ? AND HASHED_PASSWORD = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{email, hashedPassword});

        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    @SuppressLint("Range")
    public String getUserName(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT FIRST_NAME, LAST_NAME FROM USER WHERE EMAIL = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{email});

        String userName = null;
        if (cursor.moveToFirst()) {
            String firstName = cursor.getString(cursor.getColumnIndex("FIRST_NAME"));
            String lastName = cursor.getString(cursor.getColumnIndex("LAST_NAME"));
            userName = firstName + " " + lastName;
        }

        cursor.close();
        return userName;
    }

    @SuppressLint("Range")
    public String getUserRole(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT ROLE FROM USER WHERE EMAIL = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{email});
        String role = null;
        if (cursor.moveToFirst()) {
            role = cursor.getString(cursor.getColumnIndex("ROLE"));
        }
        cursor.close();
        return role;
    }

}

