package com.mcompany.coupan.data.network;


import com.mcompany.coupan.models.ErrorModel;

/**
 * Interface which is used to update UI after Network operation has falied
 */
public interface NetworkErrorListener<T> {

    void onError(ErrorModel response);


}

