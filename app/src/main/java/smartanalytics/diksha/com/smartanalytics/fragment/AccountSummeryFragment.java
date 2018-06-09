package smartanalytics.diksha.com.smartanalytics.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import smartanalytics.diksha.com.smartanalytics.R;
import smartanalytics.diksha.com.smartanalytics.commonutils.LogUtils;
import smartanalytics.diksha.com.smartanalytics.core.SmartAppication;
import smartanalytics.diksha.com.smartanalytics.data.AccountSummaryData;
import smartanalytics.diksha.com.smartanalytics.network.OttoEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountSummeryFragment extends Fragment {
    TextView tvPrevBalance,tvPayments,tvAdjustments,tvMonthlyRental,tvUsage,tvTaxes,tvAmountDue,tvAmountdueAfterDueDate;

    public AccountSummeryFragment() {
        // Required empty public constructor
    }
    public String TAG = AccountSummeryFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account_summery, container, false);
        tvPrevBalance = v.findViewById(R.id.tvPrevBalvalue);
        tvPayments = v.findViewById(R.id.tvPaymentsValue);
        tvAdjustments = v.findViewById(R.id.tvAdjustmentsValue);
        tvMonthlyRental = v.findViewById(R.id.tvMonthlyrentalvalue);
        tvUsage = v.findViewById(R.id.tvUsageValue);
        tvTaxes = v.findViewById(R.id.tvTaxesValue);
        tvAmountDue = v.findViewById(R.id.tvAmountDueValue);
        tvAmountdueAfterDueDate = v.findViewById(R.id.tvTotalAccountvalue);
        return v;
    }


    public void setAccSummary(String billNo) {
        AccountSummaryData accountSummaryData = SmartAppication.getInstance().getmDatabaseHelper().getUserAnalyticsSummaryByBillnum(billNo);
        tvPrevBalance.setText("₹ " +accountSummaryData.getPrevBalance());
        tvPayments.setText("₹ " +accountSummaryData.getPayments());
        tvAdjustments.setText("₹ " +accountSummaryData.getAdjustments());
        tvMonthlyRental.setText("₹ " +accountSummaryData.getMonthlyRental());
        tvUsage.setText("₹ " +accountSummaryData.getUsage());
        tvTaxes.setText("₹ " +accountSummaryData.getTaxes());
        tvAmountDue.setText("₹ " +accountSummaryData.getAmountDue());
        tvAmountdueAfterDueDate.setText("₹ " +accountSummaryData.getAmountdueAfterDueDate());


    }

}
