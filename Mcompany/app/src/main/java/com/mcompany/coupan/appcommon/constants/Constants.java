package com.mcompany.coupan.appcommon.constants;


public interface Constants {
    String EMPTY_STRING = "";
    String NA_STRING = "NA";
    String SPACE_STRING = " ";

    //Error Handling Keys
    String ERR_INVALID_TOKEN = "InvalidTokenError";
    String ERR_INVALID_GRANT = "InvalidGrantError";
    String ERR_USER_NOT_REG = "UsernameNotFoundError";
    String ERR_DUPLICATE_UID = "DuplicateUidError";
    String ERR_PASS_MISS_MATCH = "PasswordMismatchError";
    String ERR_NO_CHECK_CART = "NoCheckoutCartError";

    long MILLISEC = 1000;
    long SEC = 60 * MILLISEC;
    long MIN = 60 * SEC;

    String SESSION_TIME_OUT_RECEIVER = "com.mcompany.coupan.CUSTOM_INTENT";

}


