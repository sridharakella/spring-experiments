package me.loki2302.greetingsapp;

import me.loki2302.greetings.EnableGreetings;
import me.loki2302.greetings.GreetingProvider;
import me.loki2302.greetings.GreetingService;
import me.loki2302.greetings.GreetingsConfigurer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnableGreetingsWithConfigurerTest {
    @Autowired
    private GreetingService greetingService;

    @Test
    public void greetingServiceShouldSayHola() {
        assertEquals("Hola, loki2302!", greetingService.greet("loki2302"));
    }

    @Configuration
    @EnableGreetings
    public static class Config extends GreetingsConfigurer {
        @Override
        protected void configureGreetingProvider(GreetingProvider greetingProvider) {
            greetingProvider.setGreetingTemplate("Hola, %s!");
        }
    }
}
