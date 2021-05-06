package com.app.system;

import com.app.client.domain.MatchDto;
import com.app.client.domain.Winner;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class TicketSystem implements TicketManager {

    private static final BigDecimal QUOTE_MULTIPLIER = BigDecimal.valueOf(0.05);
    private static final BigDecimal STANDARD_WIN = BigDecimal.valueOf(2);

    @Override
    public BigDecimal countMultiplierByWinnerType(MatchDto matchDto, Winner winner) {
        double hostPower = matchDto.getHostTeam().getPower();
        double opponentPower = matchDto.getOppositeTeam().getPower();
        BigDecimal different = BigDecimal.valueOf(hostPower - opponentPower);
        switch (winner) {
            case HOST_TEAM:
                return STANDARD_WIN.subtract(different.multiply(QUOTE_MULTIPLIER));
            case OPPOSITE_TEAM:
                return STANDARD_WIN.add(different.multiply(QUOTE_MULTIPLIER));
            case DRAW:
                return STANDARD_WIN;
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
