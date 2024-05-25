package itis.solopov.util;


final public class Constants {
    public static final String API_KEY = "2e7a7775femshb136a173b335463p1571a5jsn63e23d4d1928";
    public static final String API_HOST = "mail-sender-api1.p.rapidapi.com";

    public static final String NEW_PASSWORD_LETTER_TEMPLATE = "{\"sendto\":\"%s\",\"name\":\"Dear %s\",\"replyTo\":\"mironsolopov24@gmail.com\",\"ishtml\":\"false\",\"title\":\"Your new password is here!\",\"body\":\"Password: %s \n You can change it later in your profile.\"}";
    public static final String USER_NOT_FOUND_EXCEPTION_EMAIL_TEMPLATE = "The user with email %s was not found";

    public static final String USER_NOT_FOUND_EXCEPTION_ID_TEMPLATE = "The user with id %s was not found";

    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    public static final String PASSWORD_CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@#$%^&+=";
}
