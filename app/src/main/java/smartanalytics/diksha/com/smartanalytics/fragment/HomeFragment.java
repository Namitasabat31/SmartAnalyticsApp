package smartanalytics.diksha.com.smartanalytics.fragment;

/**
 * Created by A1GLJZJ1 on 4/26/2018.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import smartanalytics.diksha.com.smartanalytics.MainActivity;
import smartanalytics.diksha.com.smartanalytics.R;
import smartanalytics.diksha.com.smartanalytics.commonutils.LogUtils;
import smartanalytics.diksha.com.smartanalytics.core.SmartAppication;
import smartanalytics.diksha.com.smartanalytics.data.MyBillDetails;
import smartanalytics.diksha.com.smartanalytics.database.DatabaseHelper;
import smartanalytics.diksha.com.smartanalytics.network.OttoEvent;

public class HomeFragment extends BaseFragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    private Spinner spinnerAccountno, spinnerInvoiceno;
    private TextView tvThisMonthCharges, tvAmountPayable, tvBilldate, tvBillDueDate, tvViewBill, tvEmailBill;
    private Button btnPaynow;
    private String userEmail;
    public String TAG = HomeFragment.class.getSimpleName();
    private SendMessage SM;
    public String selectedAccNo,selectedBillNo;
    private DatabaseHelper mDatabaseHelper;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //this inflates out tab layout file.
        View x = inflater.inflate(R.layout.home_fragment_layout, null);
        initTab(x);
        mDatabaseHelper = SmartAppication.getInstance().getmDatabaseHelper();
        tvThisMonthCharges = x.findViewById(R.id.thismonthchargesvalue);
        tvAmountPayable = x.findViewById(R.id.amountpayablevalue);
        tvBilldate = x.findViewById(R.id.billdatevalue);
        tvBillDueDate = x.findViewById(R.id.billduedatevalue);
        spinnerAccountno = x.findViewById(R.id.spinner_accountno);
        spinnerInvoiceno = x.findViewById(R.id.spinner_invoiceno);
        tvViewBill = x.findViewById(R.id.viewbill);
        tvEmailBill = x.findViewById(R.id.emailbill);
        btnPaynow = x.findViewById(R.id.paynow);
        btnPaynow = x.findViewById(R.id.paynow);
        tvViewBill = x.findViewById(R.id.viewbill);
        tvEmailBill = x.findViewById(R.id.emailbill);
        userEmail = SmartAppication.getInstance().getPreferenceData().getUserEmail();
        if (userEmail != null) {
            ArrayList<String> acccountNolist = mDatabaseHelper.getAccountNumberList(userEmail);
            bindAccountNoSpinner(acccountNolist);
        }

        tvViewBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ((MainActivity)getActivity()).replaceBillsFragment();
            }
        });

        btnPaynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((MainActivity)getActivity()).replacePaymentFragment();
            }
        });

        spinnerAccountno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAccNo = parent.getItemAtPosition(position).toString();
                SmartAppication.getInstance().setSelectedAccNo(selectedAccNo);
                // bind bill no spinner
                ArrayList<String> invoiceNolist = mDatabaseHelper.getBillNumbersByAccountNumber(selectedAccNo);
                bindInvoiceNoSpinner(invoiceNolist);
                // bind bill history
               SM.sendBillHistory(selectedAccNo,billHistoryFragment);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinnerInvoiceno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBillNo = parent.getItemAtPosition(position).toString();
                SmartAppication.getInstance().setSelectedInvoiceNo(selectedBillNo);
                //populate my details
                populateMyDetails(selectedBillNo);
                SM.sendAccSummary(selectedBillNo,accountSummeryFragment);
                String useremail = SmartAppication.getInstance().getPreferenceData().getUserEmail();
                if(selectedAccNo != null && selectedBillNo != null && useremail != null){
                    SM.sendServiceSummary(userEmail,selectedAccNo,selectedBillNo,serviceSummeryFragment);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return x;
    }

    private void populateMyDetails(String selectedBillNo) {
        MyBillDetails billDetails = mDatabaseHelper.getMyBillDetails(selectedBillNo);
        tvThisMonthCharges.setText("₹ " +billDetails.getThisMonCharges());
                tvAmountPayable.setText("₹ " +billDetails.getAmountPyable());
                tvBilldate.setText(billDetails.getBillDate());
        tvBillDueDate.setText(billDetails.getBillDueDate());

    }

    private void bindInvoiceNoSpinner(ArrayList<String> invoiceNolist) {
        adapterLoc = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_dropdown_item, invoiceNolist);
        spinnerInvoiceno.setAdapter(adapterLoc);
    }

    ArrayAdapter<String> adapterLoc;

    private void bindAccountNoSpinner(ArrayList<String> acccountNolist) {
        adapterLoc = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_dropdown_item, acccountNolist);
        spinnerAccountno.setAdapter(adapterLoc);
    }

    private void initTab(View x) {
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.acc_Summary)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.service_summary)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getString(R.string.bill_history)));
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // create a new adapter for our pageViewer. This adapters returns child fragments as per the positon of the page Viewer.
        final MyAdapter adapter = new MyAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //to preload the adjacent tabs. This makes transition smooth.
        viewPager.setOffscreenPageLimit(2);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected <T> void onResponse(T t, int id) throws Exception {
        LogUtils.logD(TAG, t.toString());

    }

    @Subscribe
    public void onOttoEventReceived(OttoEvent event) {


        try {
            super.onOttoEventReceived(event);

        } catch (Exception e) {
            LogUtils.logD(TAG, "Exception --> " + e.getMessage());
        }

    }
    AccountSummeryFragment accountSummeryFragment;
    ServiceSummeryFragment serviceSummeryFragment;
    BillHistoryFragment billHistoryFragment;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    class MyAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public MyAdapter(FragmentManager fm, int NumOfTabs) {

            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        //return the fragment with respect to page position.
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    accountSummeryFragment = new AccountSummeryFragment();
                    return accountSummeryFragment;
                case 1:
                    serviceSummeryFragment = new ServiceSummeryFragment();
                    return serviceSummeryFragment;
                case 2:
                    billHistoryFragment = new BillHistoryFragment();
                    return billHistoryFragment;

            }
            return null;
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }



        //This method returns the title of the tab according to the position.
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return getResources().getString(R.string.acc_Summary);
                case 1:
                    return getResources().getString(R.string.service_summary);
                case 2:
                    return getResources().getString(R.string.bill_history);

            }
            return null;
        }
    }

    public interface SendMessage {
        void sendBillHistory(String accNo,Fragment fragment);
        void sendAccSummary(String billNo,Fragment fragment);
        void sendServiceSummary(String email, String accNo,String billNo,Fragment fragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
           e.printStackTrace();
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
    protected String getTitle() {
        return "Home";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
