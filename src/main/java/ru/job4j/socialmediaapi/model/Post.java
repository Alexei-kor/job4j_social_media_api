package ru.job4j.socialmediaapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;
    //private LocalDate date;
    //private LocalDate time;
    private String head;
    private String text;

    public Post(User owner, String head, String text) {
        this.owner = owner;
        //this.date = date;
        //this.time = time;
        this.head = head;
        this.text = text;
    }
}
