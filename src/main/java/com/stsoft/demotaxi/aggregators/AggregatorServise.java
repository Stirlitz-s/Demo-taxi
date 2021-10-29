package com.stsoft.demotaxi.aggregators;

import java.util.Map;

import com.stsoft.demotaxi.aggregators.yandex.service.AuthTokenService;
import com.stsoft.demotaxi.json.CancelRequesJSON;
import com.stsoft.demotaxi.requests.AggregatorRequest;
import com.stsoft.demotaxi.requests.GetUrlRequest;

public interface AggregatorServise {
    public AggregatorRequest getPrices(Map<String, String> params, AuthTokenService authTokenService);
    public GetUrlRequest postOrdersApprove(Map<String, String> params, AuthTokenService authTokenService);
    public AggregatorRequest postCancel(Map<String, String> params, AuthTokenService authTokenService, CancelRequesJSON input);
}
