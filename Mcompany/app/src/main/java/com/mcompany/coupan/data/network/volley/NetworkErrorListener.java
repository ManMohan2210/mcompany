package com.mcompany.coupan.data.network.volley;


import com.mcompany.coupan.dtos.ErrorModel;

/**
 * Interface which is used to update UI after Network operation has falied
 */
public interface NetworkErrorListener<T> {

    void onError(ErrorModel response);


}

