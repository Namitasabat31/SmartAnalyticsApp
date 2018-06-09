package smartanalytics.diksha.com.smartanalytics.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

@SuppressLint("CommitPrefEdits")
public class PreferenceData {
	private SharedPreferences pref;
	private Context mContext;
    private Editor editor;

	public boolean isDatabaseExists() {
		return pref.getBoolean("IS_DB_EXISTS",false);
	}

	public void setDatabaseExists(boolean databaseExists) {
		editor.putBoolean("IS_DB_EXISTS", databaseExists);
		editor.commit();
	}

	public boolean isDatabaseExists;
	public static final String KEY_LOGIN_CHECK = "IS_LOGGED_IN";

	public String getUserEmail() {
		return pref.getString("USR_EMAIL", null);
	}

	public void setUserEmail(String userEmail) {
		editor.putString("USR_EMAIL", userEmail);
		editor.commit();
	}


	public String getNewVersion() {
		return pref.getString("VERSION", "0");
	}

	public void setNewVersion(String newVersion) {
		editor.putString("VERSION", newVersion);
		editor.commit();
	}

	public boolean newVersion;

	public boolean isLoggedIn() {

		return pref.getBoolean(KEY_LOGIN_CHECK, false);
	}

	public void setIsLoggedIn(boolean isLoggedIn) {
		editor.putBoolean(KEY_LOGIN_CHECK, isLoggedIn);
		editor.commit();
	}

	public String getUserName() {
		return pref.getString("USER_NAME", null);	}

	public void setUserName(String userName) {
		editor.putString("USER_NAME", userName);
		editor.commit();
	}

	public String userName;

	public String getUserRole() {
		return pref.getString("USER_ROLE", null);	}


	public void setUserRole(String userRole) {
		editor.putString("USER_ROLE", userRole);
		editor.commit();
	}

	public String userRole;


	public PreferenceData(Context context) {
		mContext = context;
		pref = PreferenceManager.getDefaultSharedPreferences(mContext);
		editor = pref.edit();
	}





}
