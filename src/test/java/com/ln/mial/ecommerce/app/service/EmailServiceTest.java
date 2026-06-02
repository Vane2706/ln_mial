package com.ln.mial.ecommerce.app.service;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Test
    void testSendEmail() throws Exception {
        MimeMessage message = mock(MimeMessage.class);

        when(mailSender.createMimeMessage()).thenReturn(message);

        emailService.sendEmail("test@mail.com", "subject", "<h1>body</h1>");

        verify(mailSender, times(1)).send(message);
    }
}