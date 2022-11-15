package pl.waw.great.shop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.waw.great.shop.model.Message;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;

    private static final String TITLE = "title";
    private static final String TEXT = "txt";
    private static final String CITY = "city";
    private static final String EMAIL = "email";
    private static final String COUNTRY = "country";

    @AfterEach
    void clear() {
        this.messageRepository.deleteAll();
    }

    @Test
    void create() {
        Message message = new Message();
        Message createdMessage = messageRepository.create(message);

        assertEquals(TITLE, createdMessage.getTitle());
        assertEquals(TEXT, createdMessage.getBody());
        assertEquals(CITY, createdMessage.getCity());
        assertEquals(EMAIL, createdMessage.getEmail());
        assertEquals(COUNTRY, createdMessage.getCountry());
    }
}