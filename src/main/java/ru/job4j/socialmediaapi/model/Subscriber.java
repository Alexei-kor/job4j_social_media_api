package ru.job4j.socialmediaapi.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table (name = "subscribers")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "subscriber_id")
    private User subscriber;

    public Subscriber() {
    }

    public Subscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subscriber that = (Subscriber) o;
        return Objects.equals(id, that.id) && Objects.equals(subscriber, that.subscriber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subscriber);
    }
}
