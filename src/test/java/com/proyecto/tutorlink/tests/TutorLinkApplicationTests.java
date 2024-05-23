package com.proyecto.tutorlink.tests;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value="test")
@SpringBootTest
class TutorLinkApplicationTests {
    @Test
    void contextLoads() {
    }
}