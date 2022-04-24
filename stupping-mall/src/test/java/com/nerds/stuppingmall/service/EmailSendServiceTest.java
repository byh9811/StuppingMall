package com.nerds.stuppingmall.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nerds.stuppingmall.service.email.EmailSendService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmailSendServiceTest {
	@InjectMocks
    EmailSendService emailSendService;

	@Test
	public void read() {
		// given
        String email = "byh9811@naver.com";

		// when
        String key = null;
        try {
            key = emailSendService.sendMessage(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then
        System.out.println(key);
	}
}
