package itis.solopov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "chat")
@Getter
@Setter
@ToString
public class Chat {

    @Id
    @Column(name = "id")
    private String id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "chat_user",
            joinColumns = @JoinColumn(name = "chat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private User user;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "chat", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Message> messages;
}
