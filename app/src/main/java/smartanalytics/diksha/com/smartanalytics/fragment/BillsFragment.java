package smartanalytics.diksha.com.smartanalytics.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import smartanalytics.diksha.com.smartanalytics.MainActivity;
import smartanalytics.diksha.com.smartanalytics.R;
import smartanalytics.diksha.com.smartanalytics.adapter.RecyclerViewAdapter;
import smartanalytics.diksha.com.smartanalytics.commonutils.BusProvider;
import smartanalytics.diksha.com.smartanalytics.commonutils.LogUtils;
import smartanalytics.diksha.com.smartanalytics.commonutils.ThreadUtils;
import smartanalytics.diksha.com.smartanalytics.core.SmartAppication;
import smartanalytics.diksha.com.smartanalytics.data.Constants;
import smartanalytics.diksha.com.smartanalytics.network.OttoEvent;
import smartanalytics.diksha.com.smartanalytics.network.StringVollyRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class BillsFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter accuOfferadapter;
    private ArrayList list;
    private String TAG = BillsFragment.class.getSimpleName();

    public BillsFragment() {
        // Required empty public constructor
    }

    public static BillsFragment newInstance() {
        return new BillsFragment();
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

    @Override
    protected String getTitle() {
        return getResources().getString(R.string.bills);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bills, container, false);

        recyclerView = v.findViewById(R.id.recycler_view_bills);
        BusProvider.getInstance().register(BillsFragment.this);
        list = SmartAppication.getInstance().getmDatabaseHelper().getBillDetails(SmartAppication.getInstance().getSelectedAccNo());
        Collections.reverse(list);
        LinearLayoutManager mLayout = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayout);
        accuOfferadapter = new RecyclerViewAdapter(list, RecyclerViewAdapter.POPULATE_VIEW_BILLS, getActivity());
        recyclerView.setAdapter(accuOfferadapter);



        return v;
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

    public void getBillDetails() {
        showDotsProgress(getActivity());
        ThreadUtils.getDefaultExecutorService().submit(new StringVollyRequest(getActivity(), Constants.URL,
                StringVollyRequest.ID_GET_BILL_DETAILS));

    }
}
