package itis.solopov.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chat")
@Getter
@Setter
@ToString
public class Chat {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private String userId;
}
