package com.mcompany.coupan.data.network;


public interface NetworkRequestCode {
    /**
     * JSON_ERROE_REQUEST_CODE is taken for handling a special case for JSON request's error.
     * Do not modify it or do not give duplicate value to another variable
     */
    int JSON_ERROE_REQUEST_CODE = -1;
    int PRODUCT_DETAILS = 1;

    int CATALOG_LIST = 2;
    int AUTH_CODE = 3;
    int SIGN_UP = 4;
    int SOCIAL_LOGIN = 5;
    int REQ_CODE_SEND_OTP = 6;
    int REQ_CODE_SUBMIT_OTP = 7;
    int REQ_CODE_SIGNUP = 8;
    int FORGET_PASSWORD = 9;
    int RESET_PASSWORD = 10;
    int ADD_ADDRESS = 11;
    int PIN_CODE_CHECK = 12;
    int GET_ALL_ADDRESS = 13;
    int EDIT_ADDRESS = 14;
    int REMOVE_ADDRESS = 15;
    int SET_DEFAULT_ADDRESS = 16;


    int GIFT_WRAP = 17;
    int REMOVE_GIFT_WRAP = 18;
    int APPLY_COUPON = 19;
    int REMOVE_COUPON = 20;
    int ADD_TO_BAG = 21;

    int UPDATE_CART_ENTRY = 22;
    int REMOVE_CART_ITEM = 23;
    int GET_USER = 24;
    int GET_CART = 25;
    int GET_EMPTY_CART = 26;
    int POST_REFRESH_TOKEN = 27;
    int CHECK_PINCODE_COD_CART = 28;
    int CHECK_PINCODE_COD_PDP = 29;

    int SIGN_OUT = 30;
    int IS_UPGRADE_AVAILABLE = 31;
    int CONFIGURABLE_WHOLE_PAGE = 32;
    int CONFIGURABLE_PAGE_INDEX = 33;
    int CONFIGURABLE_PAGE_WITH_SELECTED_WIDGETS = 34;
    int LINK_UID_WITH_EMAIL = 35;

    int CONFIGURABLE_PAGE = 36;
    int ADD_EGV_TO_BAG = 37;
    int UPDATE_FILTER = 38;
    int SAVEMYFIT = 39;
    int ADDTOWISHLIST = 40;
    int REMOVEFROMWISHLIST = 41;
    int MOVETOWISHLST = 42;


    int CATEGORY_LIST = 43;
    int AUTO_SEARCH = 44;


    int PENDING_ORDER = 45;
    int COMPLETED_ORDERS = 46;
    int RETURNED_ORDERS = 47;
    int GET_CONSIGNMENT = 48;
    int RETURN_SUBMIT = 49;
    int ADD_FCCTO_BAG = 50;
    int FCCTITLE = 51;
    int GET_GENDER_INFO = 52;


    int NEWSLETTER_SUBSCRIBTION = 53;
    int GETALL_BRANDS = 54;
    int GC_EGV_CHECK_BALANCE = 55;
    int UPDATE_PROFILE_CODE = 56;
    int GC_EGV_REMOVE_CARD = 57;
    int CHANGE_PASSWORD_CODE = 58;
    int GC_EGV_APPLY = 59;
    int REQ_CODE_MOBILE_CHANGE = 60;
    int FCC_CHECK_BALANCE = 61;
    int UPDATE_PROFILE_PCIURE_CODE = 62;
    int FCC_REDEEM_BALANCE = 63;
    int REMOVE_PROFILE_PCIURE_CODE = 64;
    /*int FCC_REMOVE = 60;
    int WALLET_STATEMENT = 61;
    int COD_ORDER = 62;
    int OTP_WALLET_PAYMNET = 63;*/

   /* int ADD_CARD_TO_WALLET = 64;
    int ADD_USER_ADDRESS_TO_CART = 65;
    int ADD_CLIENT_ADDRESS_TO_CART = 66;
    int GET_CONFIGURABLE_TAB =67;*/

   /* int GET_CITY_NAME = 68;
    int GC_EGV_CHECK_BALANCE = 60;
    int GET_STORE_INFO = 69;
    int GC_EGV_REMOVE_CARD = 61;
    int SET_HOME_STORE = 70;
    int GC_EGV_APPLY = 62;
    int GET_USER_HOMESTORE = 71;
    int FCC_CHECK_BALANCE = 63;
    int CREATE_WALLET = 72;
    int FCC_REDEEM_BALANCE = 64;
    int NOTIFIED_CUSTOMER = 73;*/
    int FCC_REMOVE = 65;
    int BENEFIT_OF_LINKING = 66;
    int WALLET_STATEMENT = 67;
    int DEMOGRAPHIC_DETAILS = 68;
    int COD_ORDER = 69;
    int VERIFY_OR_UPDATE_FCC_DETAILS = 70;
    int LINK_MY_CARD = 71;
    int OTP_WALLET_PAYMNET = 72;

    int ADD_CARD_TO_WALLET = 73;
    int ADD_USER_ADDRESS_TO_CART = 74;
    int ADD_CLIENT_ADDRESS_TO_CART = 75;
    int GET_CONFIGURABLE_TAB = 76;

    int GET_CITY_NAME = 77;
    int GET_STORE_INFO = 78;
    int SET_HOME_STORE = 79;
    int GET_USER_HOMESTORE = 80;
    int CREATE_WALLET = 81;
    int NOTIFIED_CUSTOMER = 82;
    int GET_LOYALTY_VALUE_POINTS = 83;
    int GETSIZEVERIENTS = 84;
    int EDIT_GUEST_ADDRESS = 85;
    int UNLINK_FCC = 86;
    int MOVETOBAG = 87;

    int LINK_REDIRECTION_CONVERT = 88;
    int ALL_ORDER = 89;
    int PAYMENT_CANCEL = 90;
    int TRACK_ORDER = 91;
    int ALL_PRODUCT_REVIEWS = 92;
    int SUBMIT_PRODUCT_REVIEW = 93;
    int EDIT_PRODUCT_REVIEW = 94;
    int EXISTING_PRODUCT_REVIEW = 95;
    int REGISTER_USER = 96;
    int PICK_FROM_STORE_ORDER_LIST = 97;
    int CHECK_PICK_FROM_STORE_AVAILABILITY = 98;

    int GET_PICK_STORE_LIST = 99;

    int ADDRESS_STORE_PICKUP=100;

}
