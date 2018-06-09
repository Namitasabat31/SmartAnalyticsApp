package smartanalytics.diksha.com.smartanalytics.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;

import org.json.JSONException;
import org.json.JSONObject;

import smartanalytics.diksha.com.smartanalytics.commonutils.CommonUtility;

/**
 * Created by b0089798 on 2/8/2016.
 */
public class NetworkExceptionHandler {


    private static final String DEFAULT_ERROR_MESSAGE = "Your request could not serverd. Please try again later.";
    private static final String LOG_TAG = "AM_RESPONSE";
    private static final String NETWORK_ERROR_MESSAGE = "No network connection. Please try again later.";
    private static final String TIMEOUT_ERROR_MESSAGE = "Request timed out. Please try again later.";
    protected int mCode = 1;
    protected String mErrCode = "0";
    protected String mMessage = "";
    protected JSONObject mResponse = new JSONObject();
    protected Exception mSysErr = null;

    public NetworkExceptionHandler(Exception exception) {
        this.mCode = -1;
        this.mErrCode = "0";
        this.mMessage = this.getErrorMessage(exception);
    }

    public NetworkExceptionHandler(String str) {

            this.mCode = -1;
            this.mErrCode = "0";
            this.mMessage = "";
            return;

    }

    public NetworkExceptionHandler(JSONObject jSONObject) {
        try {
            this.mResponse = jSONObject;
            this.mCode = this.mResponse.getInt("code");
            this.mErrCode = this.mResponse.optString("errorCode", "0");
            this.mMessage = this.mResponse.optString("messageText");
            return;
        } catch (JSONException e) {
            this.mCode = -1;
            this.mErrCode = "0";
            this.mMessage = "Your request could not serverd. Please try again later.";
            return;
        }
    }

    public int getCode() {
        return this.mCode;
    }

    public String getErrorCode() {
        return this.mErrCode;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String getErrorMessage(Exception exception) {
        String string2 = "Your request could not serverd. Please try again later.";
        if (exception instanceof NetworkError) {
            return "No network connection. Please try again later.";
        }
        String string3 = string2;
        if (exception instanceof ServerError) return string3;
        string3 = string2;
        if (exception instanceof AuthFailureError) return string3;
        string3 = string2;
        if (exception instanceof ParseError) return string3;
        if (exception instanceof NoConnectionError) {
            return "No network connection. Please try again later.";
        }
        string3 = string2;
        if (!(exception instanceof TimeoutError)) return string3;
        return "Request timed out. Please try again later.";
    }

    public String getMessageText() {
        return this.mMessage;
    }

    public Exception getSysErr() {
        return this.mSysErr;
    }

    public boolean isSuccessful() {
        if (this.mCode == 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        if (this.mSysErr != null) {
            return this.mSysErr.getClass().getName() + "\n" + this.mSysErr.getMessage();
        }
        return String.format("{code:%d,\nerrCode:%s,\nmessage:%s}", this.mCode, this.mErrCode, this.mMessage);
    }
}