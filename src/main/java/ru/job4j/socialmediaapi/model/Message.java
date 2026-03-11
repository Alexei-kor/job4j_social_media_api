package ru.job4j.socialmediaapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "from_id")
    private User from;

    @OneToOne
    @JoinColumn(name = "to_id")
    private User to;

    private String text;

    public Message(User from, User to, String text) {
        this.from = from;
        this.to = to;
        this.text = text;
    }
}
