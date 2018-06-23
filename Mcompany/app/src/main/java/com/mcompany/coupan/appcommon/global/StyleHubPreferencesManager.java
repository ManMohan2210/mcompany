package com.mcompany.coupan.appcommon.global;

import android.content.Context;
import android.content.SharedPreferences;

import com.mcompany.coupan.ui.application.McompApplication;
import com.mcompany.coupan.appcommon.constants.Constants;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

public class StyleHubPreferencesManager {
    private final static String PREF_FILE = "SSLStyleHubPreference";

    private SharedPreferences mSharedPreferences;
    private static volatile StyleHubPreferencesManager sPreferencesManagerInstance;

    private StyleHubPreferencesManager() {
        mSharedPreferences = McompApplication.getInstance().getContext().getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        if (sPreferencesManagerInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static synchronized StyleHubPreferencesManager getInstance() {
        if (sPreferencesManagerInstance == null) { //if there is no instance available... create new one
            synchronized (StyleHubPreferencesManager.class) {
                if (sPreferencesManagerInstance == null) sPreferencesManagerInstance = new StyleHubPreferencesManager();
            }
        }
        return sPreferencesManagerInstance;
    }
    //Make singleton from serialize and deserialize operation.
    protected StyleHubPreferencesManager readResolve() {
        return getInstance();
    }
    /**
     * Set a string shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Set a integer shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * Set a Boolean shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * Get a string shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference
     *                 isn't found.
     * @return value - String containing value of the shared preference if
     * found.
     */
    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    public String getString(String key) {
        return getString(key, Constants.EMPTY_STRING);
    }

    /**
     * Get a integer shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference
     *                 isn't found.
     * @return value - String containing value of the shared preference if
     * found.
     */
    public int getInt(Context context, String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    /**
     * Validate Key Exist or not in shared preference file.
     *
     * @param key      - Key to look up in shared preferences.
     */
    public Boolean isKeyExist(String key){
        return mSharedPreferences.contains(key);
    }

    /**
     * Remove record from shared preference
     *
     * @param key      - Key to look up in shared preferences.
     */
    public void removeKey(String key){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }
    /**
     * Get a boolean shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference
     *                 isn't found.
     * @return value - String containing value of the shared preference if
     * found.
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }


    /**
     * Set a long shared preference
     *
     * @param key   - Key to set shared preference
     * @param value - Value for the key
     */
    public void setLong(String key, long value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * Get a long shared preference
     *
     * @param key      - Key to look up in shared preferences.
     * @param defValue - Default value to be returned if shared preference
     *                 isn't found.
     * @return value - long containing value of the shared preference if
     * found.
     */
    public long getLong(final String key, final long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    public boolean saveDataMap(final WeakHashMap<String, String> dataMap) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Iterator it = dataMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (pair.getKey() != null) {
                String value = (pair.getValue() != null) ? pair.getValue().toString() : Constants.EMPTY_STRING;
                editor.putString(pair.getKey().toString(), value);
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        return editor.commit();
    }

    public void clearAll() {
        mSharedPreferences.edit().clear().commit();
    }

}