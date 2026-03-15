package ru.job4j.socialmediaapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table (name = "subscribers")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    private User owner;

    //@OneToOne
    //@JoinColumn(name = "subscriber_id")
    //private User subscriber;
    private String nameSubscriber;

    public Subscriber(User owner, String nameSubscriber) {
        this.owner = owner;
        this.nameSubscriber = nameSubscriber;
    }
}
