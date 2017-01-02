package aeontanvir.com.mobitourmate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import aeontanvir.com.mobitourmate.customservice.LocationResult;
import aeontanvir.com.mobitourmate.customservice.LocationService;

public class MainActivity extends AppCompatActivity implements LocationResult.ReceiverLocation {
    public LocationResult locationResult;

    TextView tvWeather;
    TextView tvPlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationResult = new LocationResult(new Handler());
        locationResult.setReceiver(this);

        tvPlace = (TextView) findViewById(R.id.tvPlace);
        tvWeather = (TextView) findViewById(R.id.tvWeather);


    }

    @Override
    protected void onStart() {
        super.onStart();
        startNow();
    }

    public void startNow() {
        Intent intent = new Intent(Intent.ACTION_SYNC, null, this, LocationService.class);
        intent.putExtra("receiver", locationResult);
        startService(intent);

    }


    @Override
    public void onLocationReceiveResult(int resultCode, Bundle resultData) {
        if(resultCode == 1) {
            String longitute = resultData.getString("longitute");
            String latitute = resultData.getString("latitute");
            String locations = resultData.getString("currentLocation");
            String weather = resultData.getString("weather");
            tvPlace.setText(locations);
            tvWeather.setText(weather+ (char) 0x00B0);

            Toast.makeText(MainActivity.this, "Long:"+longitute + ", Lati:" + latitute, Toast.LENGTH_LONG).show();
        }
    }


    public void goTourManageHome(View view) {
    }
}
