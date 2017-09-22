package com.webstar.util;

public class Constants
{
    public static final Integer ACTIVE = 1;
    public static final Integer PASSIVE = 2;
    public static final Integer DELETED = 3;
    public static final Integer BLOCKED = 4;

    public static final String REGISTRATION_SUCCESS_MSG = "Thank you for signing up. You may now login. Email has been sent for confirmation.";
    public static final String LOGIN_FAIL_MSG = "Either username or password is wrong.";
    public static final String WEBSTAR_COOKIE_AUTH = "Webstar_cookie";
    public static final String EMAIL_EXISTS = "Email already exists.";
    public static final String HIDDEN_FORM_ATTRIBUTE_SESSION = "nameEmail";
    
    public static final String IMG_PATH = "//pics//";
    public static final String IMG_PATH_FOLDER = "pics";
    
    public static final String COOKIE_SEPARATOR = "###";
   
    //email
    public static final String FROM_EMAIL = "support@webstar.com";
    
    public static final int BLOCKSIZE = 100;
    public static final String REGISTRATION_MESSAGE ="\nWelcome! Thank you for registering with us. This email is just a reminder that you have successfully "
        + "registered and you account is activated.\n"
        + "Should you have any feedback, please feel free to reach us at any time.\n\n"
        + "Thanking you,\n"
        + "Webstar Team\n";
    
}
