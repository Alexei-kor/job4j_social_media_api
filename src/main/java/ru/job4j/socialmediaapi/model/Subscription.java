package ru.job4j.socialmediaapi.model;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner", unique = false)
    private User owner;

    @OneToOne
    @JoinColumn(name = "subscriber")
    private User subscriber;

    public Subscription() {
    }

    public Subscription(User owner, User subscriber) {
        this.owner = owner;
        this.subscriber = subscriber;
    }

    public Long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Subscription that = (Subscription) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
