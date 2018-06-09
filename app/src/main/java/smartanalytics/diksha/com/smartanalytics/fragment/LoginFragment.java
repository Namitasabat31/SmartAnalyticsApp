package smartanalytics.diksha.com.smartanalytics.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import smartanalytics.diksha.com.smartanalytics.LoginActivity;
import smartanalytics.diksha.com.smartanalytics.MainActivity;
import smartanalytics.diksha.com.smartanalytics.R;
import smartanalytics.diksha.com.smartanalytics.commonutils.CommonUtility;
import smartanalytics.diksha.com.smartanalytics.commonutils.ConnectionManager;
import smartanalytics.diksha.com.smartanalytics.commonutils.LogUtils;
import smartanalytics.diksha.com.smartanalytics.commonutils.ThreadUtils;
import smartanalytics.diksha.com.smartanalytics.core.SmartAppication;
import smartanalytics.diksha.com.smartanalytics.data.Constants;
import smartanalytics.diksha.com.smartanalytics.network.OttoEvent;
import smartanalytics.diksha.com.smartanalytics.network.StringVollyRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment {

    private Button btnSignin;
    private EditText edtUserID,edtPassword;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        btnSignin = v.findViewById(R.id.btnSignin);
        edtUserID = v.findViewById(R.id.idUserName);
        edtPassword = v.findViewById(R.id.idPassword);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userid = edtUserID.getText().toString();
                String pasword = edtPassword.getText().toString();
                if(TextUtils.isEmpty(userid) || !CommonUtility.isEmailValid(userid)){
                    edtPassword.clearFocus();
                    edtUserID.requestFocus();
                    edtUserID.setError(getResources().getString(R.string.userid_error));
                }else if(TextUtils.isEmpty(pasword)){
                    edtUserID.clearFocus();
                    edtPassword.requestFocus();
                    edtPassword.setError(getResources().getString(R.string.password_Error));
                }else {
                    checkLogin(userid,pasword);
                }



            }
        });
        return  v;
    }

    @Override
    protected String getTitle() {
        return null;
    }

    private void checkLogin(String userid, String pasword) {

      /*  boolean isUserExists = SmartAppication.getInstance().getmDatabaseHelper().checkLoginUserExists(userid,pasword);

        if(isUserExists){
            SmartAppication.getInstance().getPreferenceData().setUserEmail(userid);
            SmartAppication.getInstance().getPreferenceData().setIsLoggedIn(true);
            getActivity().finish();
            Intent i = new Intent(getActivity(), MainActivity.class);
              startActivity(i);
        }else{
            CommonUtility.createAlert1(getActivity(),getResources().getString(R.string.loginerror),getResources().getString(R.string.invalid_cred));
        }*/

        if (!ConnectionManager.getSingletonInstance().isConnectingToInternet(getActivity())) {
            LogUtils.logD(TAG, "No internet");
            //display no internet
              CommonUtility.displayToast(getActivity(), Constants.INTERNET_CHECK_MESSAGE);
            return;
        }else {
            showDotsProgress(getActivity());

            Map<String, String> params = new HashMap<String, String>();
            params.put("userid", userid);
            params.put("password", pasword);

            ThreadUtils.getDefaultExecutorService().submit(new StringVollyRequest(Constants.LOGIN_URL, StringVollyRequest.ID_LOGIN,
                    params));
        }
    }

    @Override
    protected <T> void onResponse(T t, int id) throws Exception {

    }

    @Override
    public void onOttoEventReceived(OttoEvent event) throws Exception {
        super.onOttoEventReceived(event);
    }
}
