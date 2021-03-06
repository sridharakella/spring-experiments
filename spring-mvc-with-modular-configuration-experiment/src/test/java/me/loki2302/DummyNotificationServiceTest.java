package me.loki2302;

import static org.junit.Assert.*;
import me.loki2302.notifications.DummyNotificationService;
import me.loki2302.notifications.NotificationService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DummyNotificationServiceTestConfiguration.class)
public class DummyNotificationServiceTest {
    @Autowired
    private NotificationService notificationService;
    
    @Test
    public void test() {
        notificationService.notifyUser();
        assertEquals(DummyNotificationService.class, notificationService.getClass());
    }
}
