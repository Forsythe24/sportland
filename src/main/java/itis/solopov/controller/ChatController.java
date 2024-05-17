package itis.solopov.controller;

import itis.solopov.AuthManager;
import itis.solopov.dto.CreateChatRequestDto;
import itis.solopov.dto.CreateMessageRequestDto;
import itis.solopov.filter.JwtAuthentication;
import itis.solopov.model.Chat;
import itis.solopov.model.Message;
import itis.solopov.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
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
        if (authManager.isAuthorized()) {
            return ResponseEntity.ok(chatService.getChatById(id));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("create")
    public ResponseEntity<Boolean> createChat(@RequestBody CreateChatRequestDto requestDto) {
        if (authManager.isAuthorized()) {
            return ResponseEntity.ok(chatService.createChat(requestDto));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("add_message")
    public ResponseEntity<Message> createMessage(@RequestBody CreateMessageRequestDto requestDto) {
        if (authManager.isAuthorized()) {
            return ResponseEntity.ok(chatService.createMessage(requestDto));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("all")
    public ResponseEntity<List<Chat>> getAllChatsByUserId() {
        Authentication authentication = authManager.extractAuthentication();
        JwtAuthentication auth;
        if (authentication instanceof JwtAuthentication && authManager.isAuthorized()) {
            auth = (JwtAuthentication) authentication;
            return ResponseEntity.ok(chatService.getAllChatsByUserId(auth.getId()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("{chatId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByChatId(@PathVariable String chatId) {
        if (authManager.isAuthorized()) {
            return ResponseEntity.ok(chatService.getAllMessagesByChatId(chatId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("{chatId}/last_message")
    public ResponseEntity<Message> getLastMessageByChatId(@PathVariable String chatId) {
        if (authManager.isAuthorized()) {
            return ResponseEntity.ok(chatService.getLastMessageByChatId(chatId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
