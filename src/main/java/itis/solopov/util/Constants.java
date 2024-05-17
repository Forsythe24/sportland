package itis.solopov.util;


final public class Constants {
    public static final String API_KEY = "2e7a7775femshb136a173b335463p1571a5jsn63e23d4d1928";
    public static final String API_HOST = "mail-sender-api1.p.rapidapi.com";

    public static final String NEW_PASSWORD_LETTER_TEMPLATE = "{\"sendto\":\"%s\",\"name\":\"Dear %s\",\"replyTo\":\"mironsolopov24@gmail.com\",\"ishtml\":\"false\",\"title\":\"Your new password!\",\"body\":\"Password: %s\"}";

    public static final String PASSWORD_CHAR_SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz@#$%^&+=";
}
