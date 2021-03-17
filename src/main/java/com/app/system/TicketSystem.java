package com.app.system;

import com.app.client.domain.MatchDto;
import com.app.client.domain.Winner;
import com.app.domain.Ticket;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class TicketSystem implements TicketManager {

    @Override
    public double countMultiplierByWinnerType(MatchDto matchDto, Winner winner) {
        double hostPower = matchDto.getHostTeam().getPower();
        double opponentPower = matchDto.getOppositeTeam().getPower();
        double baseCounter = 1.3;
        double different =( hostPower - opponentPower) / 100;
        switch (winner) {
            case HOST_TEAM:
                return baseCounter - different;
            case OPPOSITE_TEAM:
                return baseCounter + different;
            case DRAW:
                return different < 0 ? baseCounter + different/2 : baseCounter - different/2;
        }
        throw new RuntimeException();
    }

    @Override
    public Map<Winner, Double> getPricesForMatch(MatchDto matchDto) {
        Map<Winner, Double> counters = new HashMap<>();
        for (Winner w : Winner.values()) {
            counters.put(w, countMultiplierByWinnerType(matchDto, w));
        }
        return counters;
    }



}
