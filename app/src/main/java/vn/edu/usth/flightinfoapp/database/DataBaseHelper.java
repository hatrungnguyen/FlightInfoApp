package vn.edu.usth.flightinfoapp.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import vn.edu.usth.flightinfoapp.model.Passenger;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GreenHill.db";
    private static final int DATABASE_VERSION = 1;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }


    public void insertPassenger(Passenger passenger) {
    }

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

