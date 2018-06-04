//package com.mcompany.coupan.appcommon.global;
//
//import android.text.TextUtils;
//
//import com.mcompany.coupan.appcommon.constants.AuthConstants;
//import com.mcompany.coupan.appcommon.constants.Constants;
//import com.mcompany.coupan.appcommon.utility.Utility;
//import com.mcompany.coupan.data.network.OauthManager;
//
//import java.util.List;
//
//
//public class UserManager {
//    private static volatile UserManager sUserManagerInstance;
//
//    private String userIDKey = "user_id";
//    public String guestCartGUIDKey = "guestCartId";
//
//
//    // User identifier
//    private String uid = "anonymous";
//    // User display name, default is Guest
//    private String userDisplayName = "Guest";
//    // User is Current
//    private final static String CURRENT_USER = "current";
//    // User's first name
//    private String firstName;
//    // User's last name
//    private String lastName;
//    /// User's full name
//    private String fullName;
//    //User's gender
//    private String gender;
//    //Display Email Id
//    private String emailId;
//
//    private HomeStore userHomeStore;
//
//    private Cart mCart;
//
//    private int cartCount;
//
//    private ProductListModel mWishList;
//
//    private String profilePicture;
//
//    // User's mobile number
//    private String mobile;
//
//    // User's dob
//    private String dob;
//
//    // user's Title
//    private String tittle;
//
//    // user's Title Code
//    private String tittleCode;
//
//    // user's available Title list
//    private List<FCCNameTitle> titleList;
//
//    // User's loyality details
//    private SslLoyaltyDetailData sslLoyaltyDetailData;
//
//    // User's loyality address details
//    private LoyaltyAddressDetails mloyaltyAddressData;
//
//    // User's wallet Number
//    private String walletnumber;
//
//    // User's want to register when checkout as a guest
//    private Boolean isGuestWantToRegister;
//
//    // To check user is social logged In or not
//    private Boolean isPasswordAvailable;
//
//
//    private UserManager() {
//        if (sUserManagerInstance != null) {
//            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
//        }
//    }
//
//    public static UserManager getInstance() {
//        if (sUserManagerInstance == null) { //if there is no instance available... create new one
//            synchronized (UserManager.class) {
//                if (sUserManagerInstance == null) sUserManagerInstance = new UserManager();
//            }
//        }
//        return sUserManagerInstance;
//    }
//
//    //Make singleton from serialize and deserialize operation.
//    protected UserManager readResolve() {
//        return getInstance();
//    }
//
//    public void updateUser(User userModel) {
//
//        if (null != userModel) {
//            uid = userModel.getUid();
//            firstName = userModel.getFirstName();
//            lastName = userModel.getLastName();
//            emailId = userModel.getUid();//userModel.getDisplayUid();
//            if (null != userModel.getName()) {
//                fullName = userModel.getName();
//            } else {
//                fullName = (firstName + " " + lastName).trim();
//            }
//            userDisplayName = fullName;
//            gender = userModel.getGender();
//            profilePicture = userModel.getProfilePicture();
//            mobile = userModel.getMobile();
//            dob = userModel.getDob();
//            tittle = userModel.getTitle();
//            tittleCode = userModel.getTitleCode();
//            titleList = userModel.getTitleList();
//            sslLoyaltyDetailData = userModel.getSslLoyaltyDetailData();
//            mloyaltyAddressData = userModel.getSslLoyaltyAddressData();
//            if (null != userModel.getWalletnumber()) {
//                walletnumber = userModel.getWalletnumber();
//            } else {
//                walletnumber = null;
//            }
//            isPasswordAvailable = userModel.isPasswordAvailable();
//
//
//        } else {
//            //If null is set, revert to default guest user
//            uid = "anonymous";
//            emailId = Constants.EMPTY_STRING;
//            userDisplayName = "Guest";
//            firstName = null;
//            lastName = null;
//            fullName = null;
//            gender = null;
//            profilePicture = null;
//            mobile = null;
//            dob = null;
//            tittle = null;
//            tittleCode = null;
//            titleList = null;
//            sslLoyaltyDetailData = null;
//            mloyaltyAddressData = null;
//            walletnumber = null;
//            isGuestWantToRegister = false;
//            userHomeStore = null;
//            isPasswordAvailable = false;
//        }
//    }
//
//    /**
//     * Save the cart GUID for next login (restoring the guest cart)
//     *
//     * @param cartGUID cart guID
//     */
//    private void saveGuestCartGUID(String cartGUID) {
//        if (cartGUID != null) {
//            PreferencesManager.getInstance().setString(guestCartGUIDKey, cartGUID);
//        } else {
//            PreferencesManager.getInstance().setString(guestCartGUIDKey, "");
//        }
//    }
//
//    /// Get guest user cart GUID (for restoring the guest cart)
//    private String getGuestCartGUID() {
//        return PreferencesManager.getInstance().getString(guestCartGUIDKey, "");
//    }
//
//    /**
//     * Get the cart code for the user (GUID for guest user and cart code for loggedin user), blank if does not already have a cart
//     *
//     * @return
//     */
//    public String getCartCode() {
//        String cartCode = "";
//        if (isLoggedIn()) {
//
//            cartCode = (mCart != null && !TextUtils.isEmpty(mCart.getCode())) ? mCart.getCode() : "";
//            return cartCode;
//        } else {
//            if (!TextUtils.isEmpty(getGuestCartGUID())) {
//                cartCode = getGuestCartGUID();
//                return cartCode;
//            }
//        }
//        return cartCode;
//    }
//
//    public void updateCart(Cart userCart) {
//        if (userCart != null) {
//            if (!isLoggedIn()) {
//                //save cart GUID
//                if(TextUtils.isEmpty(PreferencesManager.getInstance().getString(UserManager.getInstance().guestCartGUIDKey))) {
//                    saveGuestCartGUID(userCart.getGuid());
//                }
//            }
//            mCart = userCart;
//        } else {
//            //this is the case if anonymous user have cart GUID and he is unable to find the cart with given GUID. To handle this case first we need to update the cart with null and will fetch the empty cart
//            PreferencesManager.getInstance().setString(guestCartGUIDKey, "");
//
//
//            //Caution:- If anyone set the cart with null then its mandatory to fetch the empty cart just after updating it with null
//            mCart = null;
//        }
//    }
//
//    public void updateWishList(ProductListModel userWishlist) {
//        if (null != userWishlist) {
//            mWishList = userWishlist;
//            if (null != userWishlist.getProducts())
//                mWishList.setTotalProductCount(userWishlist.getProducts().size());
//        }
//    }
//
//    /**
//     * Check if the user is loggedin or not
//     *
//     * @return logged in status flag.
//     */
//    public static boolean isLoggedIn() {
//        CharSequence token = PreferencesManager.getInstance().getString(AuthConstants.KEY_ACCESS_TOKEN);
//        if (!TextUtils.isEmpty(token)) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * Logout the user by clearing any saved session, this method must be called on success of logout API call
//     */
//    public void logoutUser() {
//        AnalyticsTracker.getInstance().trackEventAction(AnalyticsConstants.ONCLICK, AnalyticsConstants.LOGOUT);
//        //Logout FB Session
//        LoginManager.getInstance().logOut();
//
//        //TODO: Signout Google session
//        //GIDSignIn.sharedInstance().signOut()
//
//        //Clear app session
//        OauthManager.getInstance().clearUserToken();
//
//        // Reset user data
//        updateUser(null);
//        if (null != mWishList) {
//            mWishList.setTotalProductCount(0);
//            mWishList.setProducts(null);
//        }
//    }
//
//
//    public boolean isWalletActivated() {
//        if (null != getWalletnumber()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public String getUid() {
//        if(isLoggedIn())
//        {
//            uid = getEmailId();
//            if(Utility.isStringNullOrEmpty(uid)){
//                uid = CURRENT_USER;
//            }
//        }
//        return uid;
//    }
//
//    public String getUserDisplayName() {
//        return userDisplayName;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public Cart getCart() {
//        return mCart;
//    }
//
//    public ProductListModel getWishList() {
//        return mWishList;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getEmailId() {
//        return emailId;
//    }
//
//    public void setEmailId(String emailId) {
//        this.emailId = emailId;
//    }
//
//    public String getProfilePicture() {
//        return profilePicture;
//    }
//
//    public void setProfilePicture(String profilePicture) {
//        this.profilePicture = profilePicture;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public String getDob() {
//        return dob;
//    }
//
//    public void setDob(String dob) {
//        this.dob = dob;
//    }
//
//
//    public int getCartCount() {
//        return cartCount;
//    }
//
//    public void setCartCount(int cartCount) {
//        this.cartCount = cartCount;
//    }
//
//    public void setHomeStore(HomeStore homestore) {
//        this.userHomeStore = homestore;
//    }
//
//    public HomeStore getHomeStore() {
//        return userHomeStore;
//    }
//
//    public List<FCCNameTitle> getTitleList() {
//        return titleList;
//    }
//
//    public String getTittleCode() {
//        return tittleCode;
//    }
//
//    public String getTittle() {
//        return tittle;
//    }
//
//
//    public SslLoyaltyDetailData getSslLoyaltyDetailData() {
//        return sslLoyaltyDetailData;
//    }
//
//    public void setSslLoyaltyDetailData(SslLoyaltyDetailData sslLoyaltyDetailData) {
//        this.sslLoyaltyDetailData = sslLoyaltyDetailData;
//    }
//
//    public LoyaltyAddressDetails getMloyaltyAddressData() {
//        return mloyaltyAddressData;
//    }
//
//    public void setMloyaltyAddressData(LoyaltyAddressDetails mloyaltyAddressData) {
//        this.mloyaltyAddressData = mloyaltyAddressData;
//    }
//
//    public String getWalletnumber() {
//        return walletnumber;
//    }
//
//    public void setWalletnumber(String walletnumber) {
//        this.walletnumber = walletnumber;
//    }
//
//    public Boolean getGuestWantToRegister() {
//        return isGuestWantToRegister;
//    }
//
//    public void setGuestWantToRegister(Boolean guestWantToRegister) {
//        isGuestWantToRegister = guestWantToRegister;
//    }
//
//
//    public Boolean getPasswordAvailable() {
//        return isPasswordAvailable;
//    }
//
//    public void setPasswordAvailable(Boolean passwordAvailable) {
//        isPasswordAvailable = passwordAvailable;
//    }
//}
