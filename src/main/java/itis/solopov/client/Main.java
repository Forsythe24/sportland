package itis.solopov.client;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HttpClient client = new HttpClient();
        String url = "http://localhost:8080/signup";
        Map<String, String> map = new HashMap<>();
        map.put("email", "gimaletdinov@gmail.com");
        map.put("password", "hellohello");
        map.put("name", "Miron");
        map.put("gender", "M");
        map.put("age", "18");
        client.post(url, map);
    }
}
