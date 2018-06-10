package com.mcompany.coupan.data.network.volley;


import com.mcompany.coupan.dtos.ErrorModel;

/**
 * Interface which is used to update UI after Network operation has finished
 */
public interface NetworkListener<T> {
    /**
     * Callback method called after Network Operation finish
     */
    void onSuccess(T response, int reqType);

    void onError(ErrorModel response, int reqType);


}
