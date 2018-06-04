package com.mcompany.coupan.appcommon.global;

public class AppConfig {
    private static volatile AppConfig sAppConfigInstance;

    private AppConfig(){
        if (sAppConfigInstance != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }

    public static AppConfig getInstance() {
        if (sAppConfigInstance == null) { //if there is no instance available... create new one
            synchronized (AppConfig.class) {
                if (sAppConfigInstance == null) sAppConfigInstance = new AppConfig();
            }
        }
        return sAppConfigInstance;
    }

    //Make singleton from serialize and deserialize operation.
    protected AppConfig readResolve() {
        return getInstance();
    }

}
