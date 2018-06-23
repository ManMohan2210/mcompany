package com.mcompany.coupan.data.network.volley;

/**
 * Created by manmohansingh on 13-06-2018.
 */

public class TokenController {

    private static final String TAG = TokenController.class.getSimpleName();

    private static volatile TokenController tokenControllerInstance;

    private  TokenController (){
        if (tokenControllerInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }
    public static TokenController getInstance(){
        if (tokenControllerInstance == null) { //if there is no instance available... create new one
            synchronized (TokenController.class) {
                if (tokenControllerInstance == null) tokenControllerInstance = new TokenController();
            }
        }
        return tokenControllerInstance;
    }
}
