package com.mcompany.coupan.data.network.volley;

import android.text.TextUtils;
import com.mcompany.coupan.appcommon.constants.AuthConstants;
import com.mcompany.coupan.appcommon.constants.Constants;
import com.mcompany.coupan.appcommon.global.PreferencesManager;
import com.mcompany.coupan.dtos.OauthResponseModel;
import java.util.WeakHashMap;

/**
 * Created by manmohan.singh on 03/06/2018.
 */

public class OauthManager {
    private static volatile OauthManager mOauthManagerInstance;

    private String mUserToken;
    private String mUserRefreshToken;
    private String mClientToken;

    private OauthManager() {
        initTokensFromPref();
        if (mOauthManagerInstance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }

    }

    public static synchronized OauthManager getInstance() {
        if (mOauthManagerInstance == null) { //if there is no instance available... create new one
            synchronized (OauthManager.class) {
                if (mOauthManagerInstance == null) mOauthManagerInstance = new OauthManager();
            }
        }
        return mOauthManagerInstance;
    }

    private void initTokensFromPref() {
        mUserToken = PreferencesManager.getInstance().getString(AuthConstants.KEY_ACCESS_TOKEN);
        mUserRefreshToken = PreferencesManager.getInstance().getString(AuthConstants.KEY_REFRESH_TOKEN);
        if (null == mUserRefreshToken) {
            mUserRefreshToken = Constants.ERR_INVALID_TOKEN;
        }
        mClientToken = PreferencesManager.getInstance().getString(AuthConstants.KEY_CLIENT_ACCESS_TOKEN);
    }

    /**
     * clear user token detail when user logged out.
     *
     * @return flag that notify the success result of the current operation.
     */
    public boolean clearUserToken() {
        WeakHashMap<String, String> userMap = new WeakHashMap<String, String>();
        userMap.put(AuthConstants.KEY_ACCESS_TOKEN, "");
        userMap.put(AuthConstants.KEY_REFRESH_TOKEN, "");
        userMap.put(AuthConstants.KEY_TOKEN_TYPE, "");
        userMap.put(AuthConstants.KEY_TOKEN_SCOPE, "");
        userMap.put(AuthConstants.KEY_TOKEN_EXPIRES_IN, "");

        mUserToken = null;
        mUserRefreshToken = null;
        return PreferencesManager.getInstance().saveDataMap(userMap);
    }

    /**
     * Update/Save user's token on user login and user's token refresh.
     *
     * @param userOauth user's oauth detail.
     * @return flag that notify the success result of the current operation.
     */
    public boolean updateUserToken(OauthResponseModel userOauth) {
        if (userOauth == null) {
            return false;
        }
        WeakHashMap<String, String> userMap = new WeakHashMap<String, String>(5);
        userMap.put(AuthConstants.KEY_ACCESS_TOKEN, userOauth.getAccess_token());
        userMap.put(AuthConstants.KEY_REFRESH_TOKEN, userOauth.getRefresh_token());
        userMap.put(AuthConstants.KEY_TOKEN_TYPE, userOauth.getToken_type());
        userMap.put(AuthConstants.KEY_TOKEN_SCOPE, userOauth.getScope());
        userMap.put(AuthConstants.KEY_TOKEN_EXPIRES_IN, String.valueOf(userOauth.getExpires_in()));

        mUserToken = userOauth.getAccess_token();
        mUserRefreshToken = userOauth.getRefresh_token();

        return PreferencesManager.getInstance().saveDataMap(userMap);
    }

    /**
     * Update/Save client's token detail in to preferences.
     *
     * @param clientOauth OauthResponseModel of client's token
     * @return flag that notify the success result of the current operation.
     */
    public boolean updateClientToken(OauthResponseModel clientOauth) {
        if (clientOauth == null) {
            return false;
        }
        WeakHashMap<String, String> userMap = new WeakHashMap<String, String>(5);
        userMap.put(AuthConstants.KEY_CLIENT_ACCESS_TOKEN, clientOauth.getAccess_token());
        userMap.put(AuthConstants.KEY_CLIENT_REFRESH_TOKEN, clientOauth.getRefresh_token());
        userMap.put(AuthConstants.KEY_CLIENT_TOKEN_TYPE, clientOauth.getToken_type());
        userMap.put(AuthConstants.KEY_CLIENT_TOKEN_SCOPE, clientOauth.getScope());
        userMap.put(AuthConstants.KEY_CLIENT_TOKEN_EXPIRES_IN, String.valueOf(clientOauth.getExpires_in()));

        mClientToken = clientOauth.getAccess_token();

        return PreferencesManager.getInstance().saveDataMap(userMap);
    }

    public String getToken() {
        if (!TextUtils.isEmpty(mUserToken)) {
            return mUserToken;
        } else {
            return mClientToken;
        }
    }

    public String getRefreshToken() {
        return mUserRefreshToken;
    }

}
