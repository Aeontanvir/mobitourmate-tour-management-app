package aeontanvir.com.mobitourmate.customservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;


public class LocationService extends IntentService {
    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    private static final String TAG = "DownloadService";

    public LocationService(){
        super(LocationService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Service Started!");


        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String url = intent.getStringExtra("url");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        bundle.putString("longitute", "90.3932762");
        bundle.putString("latitute", "23.7518651");
        bundle.putString("currentLocation", "Kawran Bazar Masjid Road, Dhaka");
        bundle.putString("weather", "24");
        receiver.send(STATUS_FINISHED, bundle);


    }
}
