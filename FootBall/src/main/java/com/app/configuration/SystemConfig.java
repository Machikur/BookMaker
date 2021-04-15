package com.app.configuration;

import com.app.service.system.MatchSystemManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class SystemConfig {

    private final MatchSystemManager matchSystemManager;

    public SystemConfig(MatchSystemManager matchSystemManager) {
        this.matchSystemManager = matchSystemManager;
    }

    @Scheduled(cron = "* */30 * * * *")
    public void manage() {
        matchSystemManager.manage();
    }

}
