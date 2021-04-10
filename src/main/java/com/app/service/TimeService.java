package com.app.service;

import com.app.client.domain.MatchDto;
import com.app.domain.Timer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class TimeService {

    public Timer getTimeToFinish(MatchDto math) {
        Timer timer=new Timer();
        LocalDateTime startTime = LocalDateTime.of(math.getDateOfMatch(), math.getFinishTime());
        long seconds = getSecondsToLocalDateTime(startTime);
        if (seconds>3600){
            timer.setHours(seconds/3600);
            seconds%=3600;
        }
        if (seconds>60){
            timer.setMinutes(seconds/60);
            seconds%=60;
        }
        timer.setSeconds(seconds);
        return timer;
    }

    private long getSecondsToLocalDateTime(LocalDateTime localDateTime) {
        return ChronoUnit.SECONDS.between(LocalDateTime.now(),localDateTime );
    }

}
