package itis.solopov.dto;


import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "Пользователь")
public class MessageDto {

    private Long id;

    @NotBlank(message = "Chat id must not be blank")
    private String chatId;

    @NotBlank(message = "Sender id must not be blank")
    private String senderId;

    private String text;

    @NotBlank(message = "Date must not be blank")
    private String date;
    public Long getId() {
        return id;
    }

    public String getChatId() {
        return chatId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(String date) {
        this.date = date;
    }
}