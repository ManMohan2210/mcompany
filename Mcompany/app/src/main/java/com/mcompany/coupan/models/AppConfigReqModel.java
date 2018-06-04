package com.mcompany.coupan.models;



public class AppConfigReqModel {

    private String appVersion;

    private String osVersion;

    private DeviceInfo deviceData;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public DeviceInfo getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(DeviceInfo deviceData) {
        this.deviceData = deviceData;
    }


}
