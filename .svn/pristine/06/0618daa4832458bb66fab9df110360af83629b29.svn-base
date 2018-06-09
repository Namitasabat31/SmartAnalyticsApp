package smartanalytics.diksha.com.smartanalytics.network;

/**
 * Created by B0089737 on 2/22/2016.
 */


import java.util.HashMap;
import java.util.Map;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class VolleyErrorHelper {

    private static final String NO_NETWORK_CONNECTION = "No network connection found";
    private static final String GENERIC_SERVER_DOWN = "Server down";
    private static final String GENERIC_SERVER_TIMEOUT = "Server Timeout";
    private static final String GENERIC_ERROR = "Your request could not served. Please try again later.";//"Some Issue";
    private static final String NO_INTERNET = "No internet";
    private static final String PARSING_FAILED = "Parsing Failure";
    private static final String AUTH_FAILED = "Authentication Failure";

    /**
     * @param error
     * @return Returns appropriate message which is to be displayed to the user against
     * the specified error object.
     */
    public static String getMessage(Object error) {
        if (error instanceof TimeoutError) {
            return GENERIC_SERVER_TIMEOUT;
        } else if (error instanceof NoConnectionError) {
            return "Backend System Down.";
        } else if (isServerProblem(error)) {
            return handleServerError(error);
        } else if (isNetworkProblem(error)) {
            return NO_INTERNET;
        } else if (isConnectionProblem(error)) {
            return "ConnectionError";
        }
        return getErrorType(error);
    }

    /**
     * @param error
     * @param
     * @return Return generic message for errors
     */
    public static String getErrorType(Object error) {
        if (error instanceof TimeoutError) {
            return GENERIC_SERVER_TIMEOUT;
        } else if (error instanceof ServerError) {
            return GENERIC_SERVER_DOWN;
        } else if (error instanceof AuthFailureError) {
            return AUTH_FAILED;
        } else if (error instanceof NetworkError) {
            return NO_INTERNET;
        } else if (error instanceof NoConnectionError) {
            return NO_NETWORK_CONNECTION;
        } else if (error instanceof ParseError) {
            return PARSING_FAILED;
        }
        return GENERIC_ERROR;
    }

    /**
     * Determines whether the error is related to network
     *
     * @param error
     * @return
     */
    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError);
    }

    /**
     * Determines whether the error is related to network
     *
     * @param error
     * @return
     */
    private static boolean isConnectionProblem(Object error) {
        return (error instanceof NoConnectionError);
    }

    /**
     * Determines whether the error is related to server
     *
     * @param error
     * @return
     */
    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError) || (error instanceof ParseError);
    }

    /**
     * Handles the server error, tries to determine whether to show a stock
     * message or to show a message retrieved from the server.
     *
     * @param err
     * @return
     */
    private static String handleServerError(Object err) {
        VolleyError error = (VolleyError) err;

        NetworkResponse response = error.networkResponse;
        HashMap<String, String> result = null;

        if (response != null) {
            switch (response.statusCode) {
                case 500:
                case 503:
                case 302:
                case 502:
                case 404:
                case 422:
                case 401:
                    try {
                        // server might return error like this { "error":
                        // "Some error occured" }
                        // Use "Gson" to parse the result
                        result = new Gson().fromJson(new String(response.data),
                                new TypeToken<Map<String, String>>() {
                                }.getType());

                    } catch (Exception e) {
                        ;
                        //return getErrorType(error);
                    } finally {
                        if (result != null && result.containsKey("error")) {
                            return response.statusCode + ":" + result.get("error");
                        } else
                            return response.statusCode + ":" + getErrorType(error);
                    }
                    // invalid request
                    //return error.getMessage();

                default:
                    return response.statusCode + ":" + GENERIC_SERVER_DOWN;
            }
        }
        return GENERIC_ERROR;//can we make it - error.getMessage.concat(GENERIC_ERROR);
    }
}
