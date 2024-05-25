package itis.solopov.converter;

import com.google.gson.Gson;
import itis.solopov.dto.MessageDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class MessageDtoListToJsonStringConverter implements Converter<List<MessageDto>, String> {
    private final Gson gson = new Gson();
    @Override
    public String convert(@NotNull List<MessageDto> messages) {
        return gson.toJson(messages);
    }
}
