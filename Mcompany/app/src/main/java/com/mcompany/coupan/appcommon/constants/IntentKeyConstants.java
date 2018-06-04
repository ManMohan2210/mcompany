package com.mcompany.coupan.appcommon.constants;

/**
 * Created by sujeetsingh on 31-03-2017.
 */

public interface IntentKeyConstants {
    String EXTRA_PRODUCT = "extra_product";
    String EXTRA_SIZE_CHART_URL = "extra_size_chart_url";
    String EXTRA_SIZE_CHART_LIST = "extra_size_chart_list";
    String PRODUCT_INFO = "product_info";
    String PRODUCT_IMAGE = "prouct_images";
    String STOCK_LEVEL_FLAG = "stock_level_flag";
    String SELECTED_SIZE_CODE = "selected_size_code";

    String PLP_STATE = "plp_state";
    String PLP_QUERY = "plp_query";
    String PRODUCT_CODE = "product_code";

    String EMAIL_ID = "email";
    String PASSWORD_SCREEN_FLAG = "password_screen_flag";
    int PASSWORD_RESET_CODE = 2;

    String EXTRA_SIGNUP_MODEL = "extra_signup_model";
    String EXTRA_OTP_ACTIVITY_STATE = "extra_otp_activity_state";

    String IS_GUEST = "is_guest";

    String IS_EDIT_ADDRESS = "is_edit_address";
    String ADDRESS = "address";
    String ADDRESS_LIST = "address_list";

    String TAG = "TAG";
    String EXTRA_BAG_ACTION = "EXTRA_BAG_ACTION";

    String EXTRA_CATEGORY_CODE = "category_code";
    String EXTRA_BRAND_CODE = "brand_code";
    String EXTRA_BRAND_NAME = "brand_name";
    String EXTRA_QUERY = "query";
    String EXTRA_SORT = "sort";
    String EXTRA_SEARCH_TEXT = "searchtext";
    String EXTRA_SEARCH_TYPE = "searchType";
    String EXTRA_CATEGORY_LIST = "category_list";
    String EXTRA_ISFROMCATEGORIES = "is_from_category";
    String EXTRA_ISFROM_CIRCULER_WIDGET = "is_from_circuler_widget";

    String EXTRA_FILTER = "filter";
    String EXTRA_REFINE_TYPE = "refine_type";


    String EXTRA_SORT_LIST = "sort_list";

    String SPLASH_INTENT_FILTER = "splash_intent_filter";
    String INTENT_FILTER = "intent_filter";
    String RESULT = "result";
    String SERVICE_ID = "service_id";
    String ERROR_MODEL = "error_model";

    String EXTRA_IMAGE = "image";
    String EXTRA_LINK_COMPONENT = "link";
    String EXTRA_SIGNUP_USER_DATA = "extra_signup_user_data";

    String EXTRA_FACETS = "facets";
    String EXTRA_CLEAR_FILTER = "clear_filter";
    String PAGE_ID = "pageId";
    String PAGE_TYPE = "pageType";
    String CONFIGURABLEPAGE = "configurablepagefromdb";
    String CIRCULAR_WIDGET_TITLE_INTENT_KEY = "circular_widget_title";

    String HOMEPAGE_PAGE_ID = "home_page";
    String CLASS_NAME = "className";
    //TODO change after implement my account
    String CLASS_TAG = "MainActivity";
    String MYACCOUNTCLASS_TAG = "MyAccountActivity";
    String CLASS_TAG_NAME = "DiscoverActivity";
    String ALL_BRAND_TAG = "AllBrandsActivity";
    String BARCODE_VALUE = "bar_code_value";

    String LOGIN_INTENT_FILTER = "login_intent_filter";


    String EXTRA_SOURCE_SCREEN = "extra_source_screen";
    String SHOPPING_BAG_INTENT_KEY = "position";
    String MORE_TAB_URL_INTENT_KEY = "url";
    String MORE_TAB_TITLE_INTENT_KEY = "title";

    String EXTRA_ORDERID = "orderId";
    String EXTRA_GUID = "guid";
    String EXTRA_ORDER = "order";
    String EXTRA_CART_ENTRY = "cartEntry";
    String EXTRA_IS_RETURN = "isReturn";
    String EXTRA_IS_NEED_SIZE_SEECTION = "isNeedSizeSelection";
    String EXTRA_IS_FROM_COMPLETED_ORDER = "completedOrder";

    String T_N_C = "T & Cs";
    String PRIVACY = "Privacy";
    String SHIPPING = "Shipping";
    String EXCHANGE_RETURNS = "Exchange & Returns";


    /*Change Password Screen*/

    String FROM_CHANGE_PASSWORD = "from_change_password";
    String EXTRA_BUNDLE = "bundle";
    String EXTRA_WALLET_REDMEED_AMOUNT = "redmeedAmount";
    String EXTRA_TABID = "tabId";
    String EXTRA_CART_PARENT = "extra_cart_parent";
    String ALL_BRAND_URL = "allBrandUrl";

 /* ss Wallet*/

    String ADD_CARD_BALANCE = "addedCardBalance";
    String CARD_DETAILS = "cardDetails";
    String CARD_NUMBER = "cardNumber";
    String CARD_PIN = "cardPin";
    String CARD_TYPE = "cardType";
    String IS_CHECK_BALANCE = "isCheckBalance";
    String IS_LAUNCHED_FROM_ACCOUNT_SCREEN = "isLaunchedFromAccountScreen";
    String MOBLIE_NUMBER = "walet_mobile_number";
    String EXTRA_TNC_POCILIES_TAB = "extra_tnc_pocilies_tab";
    String SHOW_REMOVEBUTTON = "showview";

    String EXTRA_IS_TOKEN_EXPIRED = "extra_is_token_expired";


    String VERIFICATION_TYPE = "verificationType";
    String DEMOGRAPHIC_DETAILS = "demographicDetails";
    String SECOND_VERIFICATION_DETAIL = "email_or_mobile";
    String LOYALTY_CARD_NUMBER = "loyalty_card_number";
    String VERIFICATION_DETAIL = "verification_detail";
    String LOYALTY_DETAI_IS_UPDATE = "verification_detail_is_update";
    String EXTRA_NEED_TO_HANDLE_TOOL_BAR = "handleToolBar";
    String HYBRIS_MOB = "hybrisMob";
    String HYBRIS_EMAIL = "hybrisEmail";
    String MY_LOYALTY_CARD_NUMBER = "loyaltyCardNumber";
    String DOB = "dob";
    String ORDER_TYPE = "orderType";
    String EXTRA_ORDER_ID = "extra_order_id";
    String IS_ORDER_PICKUP_FROM_STORE="is_order_pick_up_from_store";
/*Upgrade Info*/
    String UPDATE_APP_INFO = "update_app_info";
    String FCC_FROM_FORTH_STEP = "fromForthStep";
    String EXTRA_NEED_TO_FETCH_CART = "extra_need_to_fetch_cart";
    String EXTRA_NEED_TO_SHOW_POP_UP = "extra_need_to_show_pop_up";

    String PRODUCT_NAME = "productName";
    String GTM_SCRREN_NAME = "gtm_scrren_name";
    String SOURCE_SCREEN_NAME_INTENT_KEY = "source_screen_name_intent_key";
    String FROM_BACK_PRESS = "from_back_press";
    String STORE_LIST_KEY = "storelistkey";
    /* Cart Advert Coupon*/
    String CART_ADVERT_COUPON = "cartMobilePage";
    String SAVED_ADDRESS = "saved_address";

    String EXTRA_PINCODE = "pincode";
    String EXTRA_SELECTED_WAREHOUSE = "selectedWareHouseCode";
    String EXTRA_WAREHOUSE_LIST = "wareHouseList";

    String SELECTED_STORE="selectedStore";

    String SAVED_LATER_PRODUCTS = "savedLaterProducts";


    String LIST_OF_SAVED_ADDRESS="savedAddress";
    String CHECKOUT_ACTIVITY="CheckoutActivity";

//    String STORE_PICKUP_CLICKED = "storePickupClicked";

    String STORE_PICKUP_PAYMENT = "storepickup_payment";

    String ADDRESS_OPERATION_PERFORMED="address_operatiom_performed";
    String STORE_PICKUP_DISABLED= "store_pickup_disabled";
    String FROM_STORE_PICKUP= "from_store_pickup";
    String VOICEBUTTONCLICK="voiceCLick";

    String EXTRA_FROM_SEARCHPAGE = "fromSearch";
}
