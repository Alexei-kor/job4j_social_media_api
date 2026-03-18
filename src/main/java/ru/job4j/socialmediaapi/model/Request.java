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

    //private Status status;
    private int status;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private User from;

    @OneToOne
    @JoinColumn(name = "friend")
    private User friend;

    public Request(User from, User friend, int status) {
        this.status = status;
        this.from = from;
        this.friend = friend;
    }
}
