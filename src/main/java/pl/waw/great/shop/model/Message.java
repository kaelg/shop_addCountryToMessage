package pl.waw.great.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private String city;
    private String email;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Message() {
    }

    public Message(String title, String text, String city, String email) {
        this.title = title;
        this.text = text;
        this.city = city;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated() {
        this.created = LocalDateTime.now();
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated() {
        this.updated = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Message message = (Message) o;
        return Objects.equals(title, message.title) && Objects.equals(text, message.text) && Objects.equals(city, message.city) && Objects.equals(
                email, message.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, city, email);
    }
}