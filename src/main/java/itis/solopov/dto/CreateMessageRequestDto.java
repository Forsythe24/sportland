package itis.solopov.dto;

import javax.validation.constraints.NotBlank;

public class CreateMessageRequestDto {
    @NotBlank(message = "Chat id must not be blank")
    private String chatId;

    @NotBlank(message = "Sender id must not be blank")
    private String senderId;

    private String text;

    private String date;

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
