//package itis.solopov.model.converter;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.google.gson.reflect.TypeToken;
//import itis.solopov.model.Instructor;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.core.convert.converter.Converter;
//
//import java.lang.reflect.Type;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class StringToInstructorsList implements Converter<String, List<Instructor>> {
//
//    @Override
//    public List<Instructor> convert(@NotNull String responseBody) {
//        Gson gson = new Gson();
//
//        JsonParser parser = new JsonParser();
//        JsonObject obj = parser.parse(responseBody).getAsJsonObject();
//
//        Type instructorType = new TypeToken<Instructor>(){}.getType();
//
//
//        JsonArray data = obj.get("data").getAsJsonArray();
//
//        List<Instructor> instructors = data.asList().stream().map(dataElement -> {
//                    JsonObject jsonObject = dataElement.getAsJsonObject();
//                    Instructor instructor = gson.fromJson(dataElement, instructorType);
//                    if (jsonObject.get("sport_id") == null) {
//                        instructor.setSportId(1);
//                    } else {
//                        instructor.setSportId(jsonObject.get("sport_id").getAsInt());
//                    }
//                    if (!jsonObject.get("main_team").isJsonObject()) {
//                        instructor.setGender("M");
//                    } else {
//                        instructor.setGender(jsonObject.get("main_team").getAsJsonObject().get("gender").getAsString());
//                    }
//                    return instructor;
//                }
//        ).collect(Collectors.toList());
//
//
////        for (int i = 0; i)
////
////        String instructorsListString = (new JSONObject(responseBody)).getJSONArray("data").toString();//        JsonElement mainTeam = obj.get("main_team").isJsonObject();
////
////        List<Instructor> instructorList = new ArrayList<>();
////        if (obj.get("main_team").isJsonObject()) {
////            gson.fromJson()
////        }
////        instructorList = gson.fromJson(instructorsListString, userListType);
//        System.out.println(instructors);
//
//        return instructors;
//    }
//}
