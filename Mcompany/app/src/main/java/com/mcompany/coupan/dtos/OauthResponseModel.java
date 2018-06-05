package com.mcompany.coupan.dtos;

import android.text.TextUtils;

/**
 * Created by manmohan.singh on 03/06/2018.
 */

public class OauthResponseModel {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private String scope;
    private int expires_in;
    private String accessTokenFetchTime;


    public static boolean checkAccessToken(OauthResponseModel oauthResponse) {
        boolean isValidAccessToken = false;
        if (oauthResponse != null) {
            isValidAccessToken = !TextUtils.isEmpty(oauthResponse.getAccess_token());
        }
        return isValidAccessToken;
    }

    public String getAccessTokenFetchTime() {
        return accessTokenFetchTime;
    }

    public void setAccessTokenFetchTime(String accessTokenFetchTime) {
        this.accessTokenFetchTime = accessTokenFetchTime;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
