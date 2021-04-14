package com.app.system;

import com.app.client.domain.MatchDto;
import com.app.client.domain.Winner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
public class TicketSystem implements TicketManager {

    @Override
    public BigDecimal countMultiplierByWinnerType(MatchDto matchDto, Winner winner) {
        double hostPower = matchDto.getHostTeam().getPower();
        double opponentPower = matchDto.getOppositeTeam().getPower();
        BigDecimal baseCounter = BigDecimal.valueOf(1.5);
        double different = (hostPower - opponentPower) / 100;
        switch (winner) {
            case HOST_TEAM:
                return baseCounter.subtract(BigDecimal.valueOf(different));
            case OPPOSITE_TEAM:
                return baseCounter.add(BigDecimal.valueOf(different));
            case DRAW:
                return (baseCounter.subtract(BigDecimal.valueOf(different)).add(baseCounter.add(BigDecimal.valueOf(different)))).divide(BigDecimal.valueOf(2));
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public Map<Winner, BigDecimal> getPricesForMatch(MatchDto matchDto) {
        Map<Winner, BigDecimal> counters = new HashMap<>();
        for (Winner w : Winner.values()) {
            counters.put(w, countMultiplierByWinnerType(matchDto, w));
        }
        return counters;
    }

}
