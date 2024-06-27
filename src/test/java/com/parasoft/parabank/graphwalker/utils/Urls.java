package com.parasoft.parabank.graphwalker.utils;

public class Urls {
    public static final String BASE_URL = "http://localhost:8080/parabank";
    public static final String INDEX_URL = BASE_URL + "/index.htm";
    public static final String LOGIN_URL = BASE_URL + "/login.htm"; // Only really used to show login result if incorrect
    public static final String FORGOT_LOGIN_URL = BASE_URL + "/lookup.htm";
    public static final String REGISTER_URL = BASE_URL + "/register.htm";
    public static final String ACCOUNTS_OVERVIEW_URL = BASE_URL + "/overview.htm";  // Used as "index" when logged in
    public static final String ACCOUNTS_ACTIVITY_URL = BASE_URL + "/activity.htm";  // Not valid as a direct URL, needs ?id= parameter
    public static final String TRANSACTION_DETAILS_URL = BASE_URL + "/transaction.htm";  // Not valid as a direct URL, needs ?id= parameter
    public static final String OPEN_NEW_ACCOUNT_URL = BASE_URL + "/openaccount.htm";
    public static final String TRANSFER_FUNDS_URL = BASE_URL + "/transfer.htm";
    public static final String BILL_PAY_URL = BASE_URL + "/billpay.htm";
    public static final String FIND_TRANSACTIONS_URL = BASE_URL + "/findtrans.htm";
    public static final String UPDATE_INFO_URL = BASE_URL + "/updateprofile.htm";
    public static final String REQUEST_LOAN_URL = BASE_URL + "/requestloan.htm";
    public static final String LOGOUT_URL = BASE_URL + "/logout.htm";
    public static final String ADMIN_URL = BASE_URL + "/admin.htm";
}
