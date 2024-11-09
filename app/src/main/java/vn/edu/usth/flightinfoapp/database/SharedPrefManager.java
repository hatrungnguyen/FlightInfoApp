package vn.edu.usth.flightinfoapp.database;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "JetSetPrefs";
    private static final String FLIGHT_LIST_KEY = "FlightList";
    private static final String USER_NAME_KEY = "UserName";
    private static final int SHARED_PREF_PRIVATE = Context.MODE_PRIVATE;
    private static final String TOOLBAR_TITLE_KEY = "ToolbarTitle";
    private static SharedPreferences sharedPreferences = null;
    private static SharedPrefManager ourInstance = null;
    private final SharedPreferences.Editor editor;

    private SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, SHARED_PREF_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SharedPrefManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new SharedPrefManager(context);
        }

        return ourInstance;
    }

    public void writeString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String readString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void writeBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public Boolean readBoolean(String key, Boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public void apply() {
        editor.apply();
    }

    public String getUserName() {
        return sharedPreferences.getString(USER_NAME_KEY, "");
    }

    public void writeToolbarTitle(String title) {
        editor.putString(TOOLBAR_TITLE_KEY, title);
        editor.commit();
    }

    public String readToolbarTitle() {
        return sharedPreferences.getString(TOOLBAR_TITLE_KEY, "Home");
    }
}

