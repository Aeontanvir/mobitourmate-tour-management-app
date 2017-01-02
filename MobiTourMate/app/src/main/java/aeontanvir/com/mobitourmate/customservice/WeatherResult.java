package aeontanvir.com.mobitourmate.customservice;


import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
/**
 * Created by aeon on 21 Nov, 2016.
 */

public class WeatherResult extends ResultReceiver {
    private ReceiverWeather mReceiver;

    public WeatherResult(Handler handler) {
        super(handler);
    }

    public void setReceiver(ReceiverWeather receiver) {
        mReceiver = receiver;
    }

    public interface ReceiverWeather {
        public void onWeatherReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onWeatherReceiveResult(resultCode, resultData);
        }
    }
}
