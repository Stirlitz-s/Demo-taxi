package com.stsoft.demotaxi.aggregators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stsoft.demotaxi.aggregators.yandex.service.AuthTokenService;
import com.stsoft.demotaxi.json.CancelRequesJSON;
import com.stsoft.demotaxi.json.ServiceJSON;
import com.stsoft.demotaxi.requests.AggregatorRequest;
import com.stsoft.demotaxi.requests.BadRequest;
import com.stsoft.demotaxi.requests.GetUrlRequest;
import com.stsoft.demotaxi.requests.GoodRequest;
import com.stsoft.demotaxi.requests.YandexCancelOrderRequest;
import com.stsoft.demotaxi.resperrors.NoTaxiFoundError;

public class MainAggregatorsService {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleAggregatorService.class);
    private List<AggregatorServise> services = new ArrayList<>();
    
    public MainAggregatorsService() {
        services.add(new SimpleAggregatorService());
        services.add(new SimpleAggregatorService());
    }

    public String getPrices(Map<String, String> params, AuthTokenService authTokenService) {
        List<AggregatorRequest> aggRequests = new ArrayList<>();
        for (AggregatorServise agServ : services) {
            aggRequests.add(agServ.getPrices(params, authTokenService));
        }
        List<AggregatorRequest> result = new ArrayList<>();
        for (AggregatorRequest agReq : aggRequests) {
            if (agReq.getResult() == null) {
                LOG.error(agReq.getError().getClass().getName() + " " + ServiceJSON.getJSONString(agReq.getError()));
            } else {
                result.add(agReq);
            }
        }
        if (result.isEmpty())
            return ServiceJSON.getJSONString(new NoTaxiFoundError());
        return ServiceJSON.getJSONString(result);
    }

    
    public String postOrdersApprove(Map<String, String> params, AuthTokenService authTokenService) {
        if (params.get("service_name").equals("yandex.taxi")) {
            ServiceJSON.getJSONString(services.get(0).postOrdersApprove(params, authTokenService));
        }
        return ServiceJSON.getJSONString(services.get(1).postOrdersApprove(params, authTokenService));
    }

    public String postCancel(Map<String, String> params, AuthTokenService authTokenService, CancelRequesJSON input) {
        if (params.get("service_name").equals("yandex.taxi")) {
            ServiceJSON.getJSONString(services.get(0).postCancel(params, authTokenService, input));
        }
        return ServiceJSON.getJSONString(services.get(1).postCancel(params, authTokenService, input));
    }    

}
