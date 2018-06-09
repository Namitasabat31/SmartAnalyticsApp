package smartanalytics.diksha.com.smartanalytics.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import smartanalytics.diksha.com.smartanalytics.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustHomeFragment extends BaseFragment {


    public CustHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cust_home, container, false);
    }

    @Override
    protected String getTitle() {
        return "Home";
    }

    @Override
    protected <T> void onResponse(T t, int id) throws Exception {

    }

    public static CustHomeFragment newInstance() {
        return new CustHomeFragment();
    }
}
