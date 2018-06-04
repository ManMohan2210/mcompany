package com.mcompany.coupan.appcommon.constants;


public interface AuthConstants {
    String USER_AGENT_KEY = "http.useragent";
    String USER_AGENT_VALUE = "RestDroid for Android";

    /**
     * User's Oauth constants
     */
    String KEY_ACCESS_TOKEN = "access_token";
    String KEY_REFRESH_TOKEN = "refresh_token";
    String KEY_TOKEN_TYPE = "token_type";
    String KEY_TOKEN_SCOPE = "scope";
    String KEY_TOKEN_EXPIRES_IN = "expires_in";


    /**
     * Client's Oauth constants
     */
    String KEY_CLIENT_ACCESS_TOKEN = "client_access_token";
    String KEY_CLIENT_REFRESH_TOKEN = "client_refresh_token";
    String KEY_CLIENT_TOKEN_TYPE = "client_token_type";
    String KEY_CLIENT_TOKEN_SCOPE = "client_scope";
    String KEY_CLIENT_TOKEN_EXPIRES_IN = "client_expires_in";


    String AUTHORIZATION = "Authorization";
    String BEARER = "Bearer ";
}
