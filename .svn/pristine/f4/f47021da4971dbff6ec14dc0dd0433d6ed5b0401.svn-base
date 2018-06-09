package smartanalytics.diksha.com.smartanalytics.commonutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Connectivity check (if device is connected with wi-fi or mobile data network)
 * 
 * @author b0089798
 */
public class ConnectionManager {

	// static member holds only one instance of the ConnectionManager
	private static ConnectionManager singletonInstance;

	// providing global point to access
	public static ConnectionManager getSingletonInstance() {

		if (null == singletonInstance) {
			singletonInstance = new ConnectionManager();
		}
		return singletonInstance;
	}

	// private constructor to prevent any other class from instantiating
	private ConnectionManager() {

	}

	/**
	 * returns true if device is connected with working wi-fi
	 * 
	 * @param context
	 * @return
	 */
	public boolean isWifiConnected(Context context) {

		boolean isConnected = false;

		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		if (networkInfo != null) {
			isConnected = networkInfo.isConnected();
		}

		return isConnected;
	}

	/**
	 * returns true if device has enabled mobile data
	 * 
	 * @param context
	 * @return
	 */
	public boolean isMobileDataEnabled(Context context) {

		boolean isConnected = false;

		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo networkInfo = connectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

		if (networkInfo != null) {
			isConnected = networkInfo.isConnected();
		}

		return isConnected;
	}

	/*
	 * returns true if connected to Internet
	 */
	public boolean isConnectingToInternet(Context context) {

		boolean isConnected = false;
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null)
				for (int i = 0; i < info.length; i++)
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						isConnected = true;
					}

		}

		if (!isConnected) {
			CommonUtility.displayToast(context,
                    "Please check internet connection.");
		}
		return isConnected;
	}

}
