package itis.solopov.controller;

import itis.solopov.AuthManager;
import itis.solopov.dto.CreateChatRequestDto;
import itis.solopov.dto.CreateMessageRequestDto;
import itis.solopov.dto.MessageDto;
import itis.solopov.filter.JwtAuthentication;
import itis.solopov.model.Chat;
import itis.solopov.model.Message;
import itis.solopov.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
@RequestMapping("api/chat")
public class ChatController {

    private final ChatService chatService;

    private final AuthManager authManager;

    public ChatController(ChatService chatService, AuthManager authManager) {
        this.chatService = chatService;
        this.authManager = authManager;
    }

    @GetMapping("{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable String id) {
        return ResponseEntity.ok(chatService.getChatById(id));
    }

    @PostMapping("create")
    public ResponseEntity<Boolean> createChat(@RequestBody CreateChatRequestDto requestDto) {
        return ResponseEntity.ok(chatService.createChat(requestDto));
    }

    @PostMapping("add_message")
    public ResponseEntity<Message> createMessage(@RequestBody CreateMessageRequestDto requestDto) {
        return ResponseEntity.ok(chatService.createMessage(requestDto));
    }

    @GetMapping("all")
    public ResponseEntity<List<Chat>> getAllChatsByUserId() {
        Authentication authentication = authManager.extractAuthentication();
        JwtAuthentication auth;
        if (authentication != null && authentication instanceof JwtAuthentication) {
            auth = (JwtAuthentication) authentication;
            return ResponseEntity.ok(chatService.getAllChatsByUserId(auth.getId()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("{chatId}/messages")
    public ResponseEntity<List<MessageDto>> getAllMessagesByChatId(@PathVariable String chatId) {
        return ResponseEntity.ok(chatService.getAllMessagesByChatId(chatId));
    }

    @GetMapping("{chatId}/last_message")
    public ResponseEntity<Message> getLastMessageByChatId(@PathVariable String chatId) {
        return ResponseEntity.ok(chatService.getLastMessageByChatId(chatId));
    }

    @MessageMapping("/chat/{chatId}/add_message")
    @SendTo("/topic/chat/{chatId}/messages")
    public MessageDto message(MessageDto message) {
        return message;
    }
}
