package itis.solopov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Chat")
@RestController
@RequestMapping("api/chat")
public class ChatController {

    private final ChatService chatService;

    private final AuthManager authManager;

    public ChatController(ChatService chatService, AuthManager authManager) {
        this.chatService = chatService;
        this.authManager = authManager;
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(description = "Get chat by id")
    @GetMapping("{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable String id) {
        return ResponseEntity.ok(chatService.getChatById(id));
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(description = "Create a new chat")
    @PostMapping("create")
    public ResponseEntity<Boolean> createChat(@RequestBody CreateChatRequestDto requestDto) {
        return ResponseEntity.ok(chatService.createChat(requestDto));
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(description = "Create a new message")
    @PostMapping("add_message")
    public ResponseEntity<Message> createMessage(@RequestBody @Validated CreateMessageRequestDto requestDto) {
        return ResponseEntity.ok(chatService.createMessage(requestDto));
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(description = "Get all chats by user id")
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

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(description = "Get all the messages of the chat by providing its's id")
    @GetMapping("{chatId}/messages")
    public ResponseEntity<List<MessageDto>> getAllMessagesByChatId(@PathVariable String chatId) {
        return ResponseEntity.ok(chatService.getAllMessagesByChatId(chatId));
    }

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @Operation(description = "Get last message of the chat by providing its' id")
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
