package com.stsoft.demotaxi.aggregators;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stsoft.demotaxi.aggregators.yandex.service.AuthTokenService;
import com.stsoft.demotaxi.json.CancelRequesJSON;
import com.stsoft.demotaxi.json.ServiceJSON;
import com.stsoft.demotaxi.requests.AggregatorRequest;
import com.stsoft.demotaxi.requests.GetUrlRequest;
import com.stsoft.demotaxi.requests.GoodRequest;
import com.stsoft.demotaxi.requests.YandexCancelOrderRequest;


public class SimpleAggregatorService implements AggregatorServise {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleAggregatorService.class);

    @Override
    public AggregatorRequest getPrices(Map<String, String> params, AuthTokenService authTokenService) {
        AggregatorRequest result = new GoodRequest(params, authTokenService);
        if (result.getResult() == null) {
            LOG.error(result.getError().getClass().getName() + " " + ServiceJSON.getJSONString(result.getError()));
        }
        return result;
    }

    
    @Override
    public GetUrlRequest postOrdersApprove(Map<String, String> params, AuthTokenService authTokenService) {
        return new GetUrlRequest(params, authTokenService);
    }

    @Override
    public AggregatorRequest postCancel(Map<String, String> params, AuthTokenService authTokenService, CancelRequesJSON input) {
        return new YandexCancelOrderRequest(params, authTokenService, input);
    }    

}
