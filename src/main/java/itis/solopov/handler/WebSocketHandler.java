package itis.solopov.handler;

import itis.solopov.converter.MessageDtoListToJsonStringConverter;
import itis.solopov.service.ChatService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;

@Component
public class WebSocketHandler extends AbstractWebSocketHandler {

    private final ChatService chatService;
    private final MessageDtoListToJsonStringConverter converter;

    public WebSocketHandler(ChatService chatService, MessageDtoListToJsonStringConverter converter) {
        this.chatService = chatService;
        this.converter = converter;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        String chatId = message.getPayload();
        String jsonMessages = converter.convert(chatService.getAllMessagesByChatId(chatId));
        session.sendMessage(new TextMessage(jsonMessages));
    }
}