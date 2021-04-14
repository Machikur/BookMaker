package com.app.system;

import com.app.client.domain.MatchDto;
import com.app.client.domain.Winner;

import java.math.BigDecimal;
import java.util.Map;

public interface TicketManager {

    BigDecimal countMultiplierByWinnerType(MatchDto matchDto, Winner winner);

    Map<Winner, BigDecimal> getPricesForMatch(MatchDto matchDto);

}
