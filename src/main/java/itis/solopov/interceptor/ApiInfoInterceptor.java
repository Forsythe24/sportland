package itis.solopov.interceptor;

import itis.solopov.util.Constants;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ApiInfoInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key" , Constants.API_KEY)
                .addHeader("X-RapidAPI-Host", Constants.API_HOST)
                .build();
        return chain.proceed(request);
    }
}
