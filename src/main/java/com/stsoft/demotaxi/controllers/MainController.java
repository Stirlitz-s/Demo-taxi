package com.stsoft.demotaxi.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stsoft.demotaxi.aggregators.MainAggregatorsService;
import com.stsoft.demotaxi.aggregators.yandex.service.AuthTokenService;
import com.stsoft.demotaxi.json.CancelRequesJSON;
import com.stsoft.demotaxi.json.ServiceJSON;
import com.stsoft.demotaxi.requests.BadRequest;
import com.stsoft.demotaxi.requests.StandartRequestParams;
import com.stsoft.demotaxi.resperrors.BadRequestError;

@RestController
public class MainController {
    @Autowired
    private AuthTokenService authTokenService;
    
    private MainAggregatorsService mainAggService = new MainAggregatorsService();

    @RequestMapping(value = "/order/approve", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPayUrl(@RequestParam(required=false) Map<String,String> qparams, Principal principal) {
        String test = checkRequest(qparams, StandartRequestParams.getUrlStandartParams);
        return test != null ? mainAggService.postOrdersApprove(qparams, authTokenService) : test;
    }
    
    @RequestMapping(value = "/order/cancel", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String cancelOrder(@RequestParam(required=false) Map<String,String> qparams, @RequestBody CancelRequesJSON input) {
        String test = checkRequest(qparams, StandartRequestParams.cancelOrdeStandartParams);
        return test != null ? mainAggService.postCancel(qparams, authTokenService, input) : test;
    }
    
    @RequestMapping(value = "/prices", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getPrices(@RequestParam(required=false) Map<String,String> qparams, Principal principal) {
        String test = checkRequest(qparams, StandartRequestParams.getPricesStandartParams);
        return test != null ? mainAggService.getPrices(qparams, authTokenService) : test;
    }    
    
    private String checkRequest(Map<String,String> qparams, List<String> standart) {
        if (!(qparams.keySet().containsAll(standart))) {
            BadRequest result = new BadRequest(qparams, authTokenService);
            result.setError(new BadRequestError());
            return ServiceJSON.getJSONString(result);
        }
        return null;
    }
}
