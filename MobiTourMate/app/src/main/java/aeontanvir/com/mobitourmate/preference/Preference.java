package aeontanvir.com.mobitourmate.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by aeon on 24 Nov, 2016.
 */

public class Preference {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String DATA_KEY = "loged";


    public Preference(Context context) {
        sharedPreferences = context.getSharedPreferences("mobitourmate", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUserData(String userData) {

        editor.putString(DATA_KEY, userData);
        editor.commit();
    }

    public String getUserData() {
        String userData = sharedPreferences.getString(DATA_KEY, "No Data Found");
        return userData;
    }
}
