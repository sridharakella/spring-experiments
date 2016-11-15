package me.loki2302;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.boot.actuate.info.MapInfoContributor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableAdminServer
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Component
    public static class DummyHealthIndicator extends AbstractHealthIndicator {
        private boolean isUp;
        private int upDownCount;

        @Scheduled(fixedRate = 1500)
        public void update() {
            isUp = !isUp;
            ++upDownCount;
        }

        @Override
        protected void doHealthCheck(Health.Builder builder) throws Exception {
            if(isUp) {
                builder.up();
            } else {
                builder.down();
            }

            builder
                    .withDetail("Up/Down count", upDownCount)
                    .withDetail("Current time", new Date().toString());
        }
    }

    @Bean
    public MapInfoContributor staticInfoContributor() {
        Map<String, Object> myInfo = new HashMap<>();
        myInfo.put("Description", "I am a very useful application");
        return new MapInfoContributor(myInfo);
    }

    @Component
    public static class DummyInfoContributor implements InfoContributor {
        @Override
        public void contribute(Info.Builder builder) {
            builder
                    .withDetail("This application", "Rocks")
                    .withDetail("Current time", new Date().toString());
        }
    }
}
