package ru.job4j.socialmediaapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "from_id")
    private User from;

    @OneToOne
    @JoinColumn(name = "to_id")
    private User friend;

    private Status status;

    public Request(User from, User friend, Status status) {
        this.from = from;
        this.friend = friend;
        this.status = status;
    }
}
