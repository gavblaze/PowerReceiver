package com.gavblaze.android.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static com.gavblaze.android.powerreceiver.MainActivity.KEY;

public class CustomReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = CustomReceiver.class.getSimpleName();

    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(LOG_TAG, "TEST............onReceive() called");


        if (intent.getAction() != null) {
            switch (intent.getAction()) {
                case Intent.ACTION_POWER_CONNECTED:
                    displayToast(context, "Power connected");
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    displayToast(context, "Power disconnected");
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    int numReceived = intent.getIntExtra(KEY, 0);
                    int numSqrd = (int) Math.pow(numReceived, 2);
                    displayToast(context, "Custom broadcast received \n Square of the random number: " + numSqrd);
                    break;
                case Intent.ACTION_HEADSET_PLUG:
                    if (intent.getIntExtra("state", -1) == 1) {
                        displayToast(context, "Headset plugged in");
                    } else if (intent.getIntExtra("state", -1) == 0) {
                        displayToast(context, "Headset unplugged");
                    }
                    break;
                default:
                    displayToast(context, "No intent recieved");
            }
        }
    }

    private void displayToast(Context context, String toastMessage) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }
}
