package edu.gxu.impression.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 *
 * @User longkai
 * @Date 13-8-30
 * @Mail im.longkai@gmail.com
 */
public class App extends Application implements SharedPreferences.OnSharedPreferenceChangeListener {

	private static final String TAG = "App";

	private static App sApp;

	public static App getApp() {
		return sApp;
	}

	private SharedPreferences mPrefs;

	@Override
	public void onCreate() {
		super.onCreate();
		sApp = this;
		mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		Log.d(TAG, "prefs " + key + " changed!");
	}

	public SharedPreferences getmPrefs() {
		return mPrefs;
	}
}
