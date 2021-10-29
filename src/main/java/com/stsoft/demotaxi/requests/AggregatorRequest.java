package com.stsoft.demotaxi.requests;

import com.stsoft.demotaxi.resperrors.TaxiExchangeError;
import com.stsoft.demotaxi.response.StandartResponse;

public interface AggregatorRequest {
    public StandartResponse getResult();
    public TaxiExchangeError getError();

}
