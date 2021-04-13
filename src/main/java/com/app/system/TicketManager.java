package com.app.system;

import com.app.client.domain.MatchDto;
import com.app.client.domain.Winner;

import java.util.Map;

public interface TicketManager {

    double countMultiplierByWinnerType(MatchDto matchDto, Winner winner);

    Map<Winner, Double> getPricesForMatch(MatchDto matchDto);

}