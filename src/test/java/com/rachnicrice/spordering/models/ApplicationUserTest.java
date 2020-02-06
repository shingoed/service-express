package com.rachnicrice.spordering.models;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationUserTest {
    ApplicationUser user;

    @BeforeEach
    public void setUp () {
        user = new ApplicationUser("Name", "password", "email@gmail.com", "2533333333","jon", "Doe", "1243", "business");
    }

    @Test
    public void testGetUsername () {

    }

    @Test
    public void testGetPassword () {

    }
}
