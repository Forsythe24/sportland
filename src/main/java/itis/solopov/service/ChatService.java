package itis.solopov.service;

import itis.solopov.dto.CreateChatRequestDto;
import itis.solopov.dto.CreateMessageRequestDto;
import itis.solopov.model.Chat;
import itis.solopov.model.Message;
import itis.solopov.repository.ChatRepository;
import itis.solopov.repository.MessageRepository;
import itis.solopov.service.exception.ChatNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    private final MessageRepository messageRepository;

    public ChatService(ChatRepository chatRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    public Boolean createChat(CreateChatRequestDto dto) {
        Chat chat = new Chat();

        chat.setId(dto.getId());
        chat.setUserId(dto.getUserId());

        chatRepository.save(chat);
        return true;
    }

    public Chat getChatById(String id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new ChatNotFoundException("Couldn't find chat with such id"));
    }

    public List<Chat> getAllChatsByUserId(String userId) {
        return chatRepository.findAllByUserId(userId);
    }

    public Message createMessage(CreateMessageRequestDto dto) {
        Message message = new Message();
        message.setText(dto.getText());
        message.setDate(dto.getDate());
        message.setChatId(dto.getChatId());
        message.setSenderId(dto.getSenderId());

        return messageRepository.save(message);
    }

    public List<Message> getAllMessagesByChatId(String chatId) {
        return messageRepository.getAllByChatId(chatId);
    }

    public Message getLastMessageByChatId(String chatId) {
        return messageRepository.findLastMessageByChatId(chatId);
    }
}
