package smartanalytics.diksha.com.smartanalytics.commonutils;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import smartanalytics.diksha.com.smartanalytics.R;

/**
 * Created by B0089798 on 8/17/2015.
 */
public class CommonUtility {

    private final static String TAG = CommonUtility.class.getSimpleName();


    // /////////////////////////////////////////////////////////////////////////////////////////////////
    // COMMON VARS
    // ////////////////////////////////////////////////////////////////////////////////////////////////
    // field to enable and disable print stack trace

    // field to enable and disable toast msg
    private static boolean sEnableToast = true;
    private static int mHashCode;


    public static boolean nullChecker(Object obj) {

        boolean success = Boolean.FALSE;

        if (null != obj) {
            success = Boolean.TRUE;
        }
        return success;
    }


    static String REGEX_PHONE_NUMBER = "^[6-9][0-9]{9}$";


    /**
     * return true if phone number is valid
     *
     * @param number
     * @return
     */
    public static boolean isValidPhoneNumber(String number) {
        boolean isValid = false;
        if (number != null && number.matches(REGEX_PHONE_NUMBER)) {
            return true;
        }
        return isValid;

    }

    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDate() {
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy h:mm a");
        return sdf.format(date);
    }

    public static String formatDate(String date_s) {
        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            return date_s;
        }

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("dd-mm-yyyy");
        return dt1.format(date);
    }

    public static String formatGraphDate(String date_s) {
        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
        SimpleDateFormat dt = new SimpleDateFormat("dd-MMM-yyyy");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            return date_s;
        }

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("MMM yyyy");
        return dt1.format(date);
    }

    public static String formatMonth(String date_s, String format) {
        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
        SimpleDateFormat dt = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            return date_s;
        }

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("MMMM");
        return dt1.format(date);
    }

    public static String formatYear(String date_s, String format) {
        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"

        //yyyy-MM-dd
        SimpleDateFormat dt = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            return date_s;
        }

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy");
        return dt1.format(date);
    }

   /* "E yyyy.MM.dd 'at' hh:mm:ss a zzz"
    Sun 2004.07.18 at 04:14:09 PM PDT
            Tue Jan 10 17:36:55 IST 2017
E MMM dd HH:mm:ss zzz yyyy*/

    //dd/mm/yyyy h:mm a

    public static String formatNewDate(String date_s) {
        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  EEE MMM dd HH:mm:ss z yyyy
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yy hh:mm:ss a");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            return date_s;
        }

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("MMMM yyyy");
        return dt1.format(date);
    }

    public static String getShowDate(String date_s) {

        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yy hh:mm:ss a");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            return date_s;
        }

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("dd MMM yyyy");
        return dt1.format(date);
    }

    public static String getShowShortDate(String date_s) {

        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yy hh:mm:ss a");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            return date_s;
        }

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("dd MMM");
        return dt1.format(date);
    }


    public static void createAlert1(final Context context, String title, String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        dialog.cancel();
                      /*  if (finish) {
                            ((Activity) context).finish();
                        }*/


                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        if (!((Activity) context).isFinishing()) {
            // show it
            alertDialog.show();
        }

    }

    public static void exitFromApp(final Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("");

        // set dialog message
        alertDialogBuilder
                .setMessage("Are you sure you want to Exit from app?")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity

                        //PreferenceManager.setLogin(context, Boolean.FALSE);

                        dialog.cancel();

                        ((Activity) context).finish();


                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();


            }
        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
       // alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        if (!((Activity) context).isFinishing()) {
            // show it
            alertDialog.show();
        }

    }

    public static void checkInternetConnectivity(Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set title
        alertDialogBuilder.setTitle("Error");

        // set dialog message
        alertDialogBuilder
                .setMessage("Please check internet connectivity")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        dialog.cancel();
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        //alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        if (!((Activity) context).isFinishing()) {
            // show it
            alertDialog.show();
        }

    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static void hideKeyboard(Context context) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {

        }
    }

    public static void displayToast(Context context, String msg) {
        if (sEnableToast) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }


}