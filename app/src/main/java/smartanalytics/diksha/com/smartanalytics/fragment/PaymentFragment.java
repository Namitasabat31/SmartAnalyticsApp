package smartanalytics.diksha.com.smartanalytics.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.squareup.otto.Subscribe;

import smartanalytics.diksha.com.smartanalytics.MainActivity;
import smartanalytics.diksha.com.smartanalytics.R;
import smartanalytics.diksha.com.smartanalytics.commonutils.LogUtils;
import smartanalytics.diksha.com.smartanalytics.network.OttoEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends BaseFragment {
    private Spinner spinnerExpiryMnth, spinnerExpiryYear,spinnerReminder;
    public String TAG = PaymentFragment.class.getSimpleName();
    public PaymentFragment() {
        // Required empty public constructor
    }
    ArrayAdapter<String> adapterLoc;
    public static PaymentFragment newInstance() {
        return new PaymentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_payment, container, false);
        spinnerExpiryMnth = v.findViewById(R.id.spinner_expirymonth);
        spinnerExpiryYear = v.findViewById(R.id.spinner_expiryyear);
        spinnerReminder = v.findViewById(R.id.spinnerReminder);
        String [] expirymnth = new String[]{"00","01","02","03","04","05","06","07","08","09","10","11","12"};
        String [] expiryyear = new String[]{"2018","2019","2020","2021","2022","2023"};
        String [] reminder = new String[]{"2 days before","1 hr before","2 hr before"};
        adapterLoc = new ArrayAdapter<String>(
                getActivity(), R.layout.spinner_text, expirymnth);
        spinnerExpiryMnth.setAdapter(adapterLoc);

        adapterLoc = new ArrayAdapter<String>(
                getActivity(), R.layout.spinner_text, expiryyear);
        spinnerExpiryYear.setAdapter(adapterLoc);


        adapterLoc = new ArrayAdapter<String>(
                getActivity(), R.layout.spinner_text, reminder);
        spinnerReminder.setAdapter(adapterLoc);
        return v;
    }

    @Override
    protected String getTitle() {
        return getResources().getString(R.string.payment);
    }
    @Override
    protected <T> void onResponse(T t, int id) throws Exception {
        LogUtils.logD(TAG, t.toString());

    }

    @Subscribe
    public void onOttoEventReceived(OttoEvent event) {
        LogUtils.logD(TAG, "DashboardAccountsFragment");

        try {
            super.onOttoEventReceived(event);

        } catch (Exception e) {
            LogUtils.logD(TAG, "Exception --> " + e.getMessage());
        }

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.clear();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

}
