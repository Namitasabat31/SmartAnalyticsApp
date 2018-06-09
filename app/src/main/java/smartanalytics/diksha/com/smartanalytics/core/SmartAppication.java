package smartanalytics.diksha.com.smartanalytics.core;

import android.app.Application;
import android.content.Context;

import smartanalytics.diksha.com.smartanalytics.data.PreferenceData;
import smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper;

/**
 * Created by A1GLJZJ1 on 5/1/2018.
 */

public class SmartAppication extends Application {
    public static SmartAppication getInstance() {
        return sInstance;
    }
    private static SmartAppication sInstance;
    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }
    static Context context;

    public String getSelectedAccNo() {
        return selectedAccNo;
    }

    public void setSelectedAccNo(String selectedAccNo) {
        this.selectedAccNo = selectedAccNo;
    }

    public String selectedAccNo;

    public String getSelectedInvoiceNo() {
        return selectedInvoiceNo;
    }

    public void setSelectedInvoiceNo(String selectedInvoiceNo) {
        this.selectedInvoiceNo = selectedInvoiceNo;
    }

    public String selectedInvoiceNo;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        context = getApplicationContext();
    }

    public DatabaseHelper getmDatabaseHelper() {
        if(mDatabaseHelper == null){
            mDatabaseHelper = new DatabaseHelper(context);
        }
        return mDatabaseHelper;
    }

    public DatabaseHelper mDatabaseHelper;

    public PreferenceData getPreferenceData() {
        if(preferenceData == null){
            preferenceData = new PreferenceData(context);
        }
        return preferenceData;
    }

    public PreferenceData preferenceData;
}
