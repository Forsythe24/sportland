//package itis.solopov.repository;
//
//import itis.solopov.interceptor.ApiInfoInterceptor;
//import itis.solopov.model.Instructor;
//import itis.solopov.model.converter.StringToInstructorsList;
//import okhttp3.Call;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.ResponseBody;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.io.IOException;
//import java.util.List;
//
//public interface InstructorRepository extends JpaRepository<Instructor, Long> {
//    default List<Instructor> getInstructorsBySportId(int id) throws IOException {
//        OkHttpClient client = new OkHttpClient()
//                .newBuilder()
//                .retryOnConnectionFailure(true)
//                .addInterceptor(new ApiInfoInterceptor())
//                .build();
//
//        Request request = new Request.Builder()
//                .url("https://sportscore1.p.rapidapi.com/sports/" + id + "/players")
//                .build();
//
//        Call call = client.newCall(request);
//        ResponseBody responseBody = call.execute().body();
//
//        assert responseBody != null;
//
//        Converter<String, List<Instructor>> converter = new StringToInstructorsList();
//
//        return converter.convert(responseBody.string());
//    }
//}
