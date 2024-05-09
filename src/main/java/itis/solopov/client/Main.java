package itis.solopov.client;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HttpClient client = new HttpClient();
        String url = "http://localhost:8080/login";
        Map<String, String> map = new HashMap<>();
        map.put("email", "ice@gmail.com");
        map.put("password", "stilinski");
//        map.put("name", "Ice");
//        map.put("gender", "M");
//        map.put("age", "19");
        client.post(url, map);
    }
}
