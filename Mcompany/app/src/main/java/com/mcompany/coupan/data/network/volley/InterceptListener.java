package com.mcompany.coupan.data.network.volley;


import com.mcompany.coupan.dtos.ErrorModel;

/**
 * Created by manmohansingh on 13-06-2017.
 */

public interface InterceptListener<T> {

    /**
     * Callback method called after Network Operation finish
     */
    void onSuccess(T response, int reqType);

    void onError(ErrorModel response, int reqType);
}
