/*
 * Copyright (c) 2019
 * Pavel Mayzenberg aka x-auth-token
 * Timur Hertz
 *
 * All rights reserved.
 */

package com.pm.mysuperstoreapp.utils;





import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;


import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.activities.LoginActivity;
import com.pm.mysuperstoreapp.activities.MainActivity;

import java.util.regex.Pattern;

import static android.app.PendingIntent.getActivity;
import static androidx.core.content.ContextCompat.getSystemService;

// Helper class for various shared methods
public final class Utils {

    public static void makeToast(View view, String string, String color){

        int x = view.getLeft();
        int y = view.getTop() + 3*view.getHeight();

        Toast toast = Toast.makeText(view.getContext(), string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.RELATIVE_LAYOUT_DIRECTION, x, y);
        TextView toastMessage = toast.getView().findViewById(android.R.id.message);
        toastMessage.setTextColor(Color.parseColor(color));
        toast.show();
    }

    public static boolean isValidEmail(EditText email, View view) {

        if (email.getText().toString().isEmpty()) {

            Utils.makeToast(view, view.getResources().getString(R.string.not_all_fields_filled), "#FF0000");

        } else if (!isValidEmailFormat(email.getText().toString())) {

            Utils.makeToast(view,view.getResources().getString(R.string.email_required), "#FF0000");

        } else {
            return true;
        }

        return false;
    }

    // Checks email format correctness
    private static boolean isValidEmailFormat(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    /*public static boolean isValidPassword(@org.jetbrains.annotations.NotNull EditText password, View view) {

        if (password.getText().toString().isEmpty()) {
            Utils.makeToast(view, view.getResources().getString(R.string.not_all_fields_filled), "#FF0000");
        } else {
            return true;
        }
        return false;
    }*/

    public static boolean isValidFirstLastName(EditText firstOrLastName, View view) {
        final int MAX_NAME_LENGTH = 15;



        if (firstOrLastName.toString().isEmpty()) {

            Utils.makeToast(view, view.getResources().getString(R.string.not_all_fields_filled), "#FF0000");

        } else if (firstOrLastName.length() > MAX_NAME_LENGTH) {
            Utils.makeToast(view, view.getResources().getString(R.string.name_too_long), "#FF0000");

        }  else if (!Pattern.matches("^[a-zA-Z]+$", firstOrLastName.getText().toString())) {

            Utils.makeToast(view, view.getResources().getString(R.string.numeric_or_special_chars_provided), "#FF0000");

        } else {
            return true;
        }


        return false;
    }

    public static void goToLoginActivity(View view) {

        Activity activity = (Activity) view.getContext();
        final Intent intent = new Intent(activity.getApplicationContext(),
                LoginActivity.class);


        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
        activity.finish();
    }

    public static void goToMainActivity(View view) {
        Activity activity = (Activity) view.getContext();
        final Intent mActivityIntent = new Intent(activity,
                MainActivity.class);
        activity.startActivity(mActivityIntent);
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
        activity.finish();
    }

    public static boolean isNetworkConnected(View view) {
        ConnectivityManager cm = (ConnectivityManager) view.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);


        // Disabled as it requires API 24+
        /*ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                super.onAvailable(network);
                // this ternary operation is not quite true, because non-metered doesn't yet mean, that it's wifi
                // nevertheless, for simplicity let's assume that's true
                //Log.i("vvv", "connected to " + (manager.isActiveNetworkMetered() ? "LTE" : "WIFI"));
            }

            @Override
            public void onLost(Network network) {
                super.onLost(network);
               // Log.i("vvv", "losing active connection");
            }
        };*/


        //cm.registerDefaultNetworkCallback(networkCallback);


        Network activeNetwork = cm.getActiveNetwork();

        if (cm != null) {

            NetworkCapabilities networkCapabilities  = cm.getNetworkCapabilities(activeNetwork);

            if (networkCapabilities != null) {
                return (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
            }
        }
        return false;
     }
}
