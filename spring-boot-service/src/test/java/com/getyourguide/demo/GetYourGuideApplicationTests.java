package com.getyourguide.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GetYourGuideApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext, "Application context should not be null");
    }

    @Test
    void mainMethodStartsApplication() {
        GetYourGuideApplication.main(new String[]{});
        // If the main method runs without throwing an exception, the test passes
    }
}
