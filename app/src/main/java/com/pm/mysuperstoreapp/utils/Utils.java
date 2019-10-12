package com.pm.mysuperstoreapp.utils;





import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;


import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.pm.mysuperstoreapp.R;
import com.pm.mysuperstoreapp.activity.LoginActivity;
import com.pm.mysuperstoreapp.activity.MainActivity;

import java.util.regex.Pattern;

import static android.app.PendingIntent.getActivity;
import static com.facebook.FacebookSdk.getApplicationContext;


public final class Utils {

    public static void makeToast(View view, String string, String color){

        int x = view.getLeft();
        int y = view.getBottom() + 2*view.getHeight();

        Toast toast = Toast.makeText(view.getContext(), string, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, x, y);
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


    public static boolean isValidPassword(@org.jetbrains.annotations.NotNull EditText password, View view) {

        if (password.getText().toString().isEmpty()) {
            Utils.makeToast(view, view.getResources().getString(R.string.not_all_fields_filled), "#FF0000");
        } else {
            return true;
        }
        return false;
    }

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
}
