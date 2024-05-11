package itis.solopov.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
    public static void main(String[] args) {
//        HttpClient client = new HttpClient();
//        String url = "http://localhost:8080/login";
//        Map<String, String> map = new HashMap<>();
//        map.put("email", "ice@gmail.com");
//        map.put("password", "stilinski");
//        map.put("name", "Ice");
//        map.put("gender", "M");
//        map.put("age", "19");
//        client.post(url, map);

        String source = "{\"sport_id\":1,\"flag\":\"england\",\"rating\":null,\"nationality_code\":\"ENG\",\"has_photo\":true,\"name_translations\":{\"en\":\"Sam McQueen\"},\"preferred_foot\":\"Left\",\"id\":13,\"ability\":null,\"name_short\":\"S. McQueen\",\"slug\":\"sam-mcqueen\",\"date_birth_at\":\"1995-02-06\",\"height\":1.76,\"market_value\":1100000,\"characteristics\":null,\"contract_until\":\"\",\"position_name\":\"Midfielder\",\"photo\":\"https://xscore.cc/resb/player/sam-mcqueen.png\",\"weight\":70,\"positions\":{\"main\":[\"ML\",\"LW\"]},\"main_team\":null,\"shirt_number\":38,\"market_currency\":\"EUR\",\"name\":\"Sam McQueen\",\"position\":\"M\",\"age\":28}\n";

        Gson gson = new Gson();
//        List<Instructor> instructors = gson.fromJson(source, List<*>.c);

        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(source).getAsJsonObject();
//        JsonElement mainTeam = obj.get("main_team").isJsonObject();
        String gender = "";
        System.out.println(obj.get("main_team").isJsonObject());

    }
}
