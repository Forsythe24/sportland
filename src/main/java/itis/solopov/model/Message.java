package itis.solopov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Getter
@Setter
@ToString
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sender_id")
    private String senderId;

    private String text;

    private String date;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "message_chat",
            joinColumns = @JoinColumn(name = "message_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id")
    )
    private Chat chat;
}
