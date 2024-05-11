package itis.solopov.controller;

import itis.solopov.dto.CreateChatRequestDto;
import itis.solopov.dto.CreateMessageRequestDto;
import itis.solopov.model.Chat;
import itis.solopov.model.Message;
import itis.solopov.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
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

    @GetMapping("all/{userId}")
    public ResponseEntity<List<Chat>> getAllChatsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(chatService.getAllChatsByUserId(userId));
    }

    @GetMapping("{chatId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByChatId(@PathVariable String chatId) {
        return ResponseEntity.ok(chatService.getAllMessagesByChatId(chatId));
    }

    @GetMapping("{chatId}/last_message")
    public ResponseEntity<Message> getLastMessageByChatId(@PathVariable String chatId) {
        return ResponseEntity.ok(chatService.getLastMessageByChatId(chatId));
    }
}
