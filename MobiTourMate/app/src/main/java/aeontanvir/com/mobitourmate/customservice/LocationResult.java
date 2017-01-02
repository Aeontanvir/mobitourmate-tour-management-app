package aeontanvir.com.mobitourmate.customservice;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by aeon on 21 Nov, 2016.
 */

public class LocationResult extends ResultReceiver {
    private ReceiverLocation mReceiver;

    public LocationResult(Handler handler) {
        super(handler);
    }

    public void setReceiver(ReceiverLocation receiver) {
        mReceiver = receiver;
    }

    public interface ReceiverLocation {
        public void onLocationReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onLocationReceiveResult(resultCode, resultData);
        }
    }
}
