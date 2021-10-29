package com.stsoft.demotaxi.requests;

import java.util.Arrays;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stsoft.demotaxi.aggregators.yandex.service.AuthTokenService;
import com.stsoft.demotaxi.json.CancelRequesJSON;
import com.stsoft.demotaxi.json.ServiceJSON;
import com.stsoft.demotaxi.json.YandexCancelRequestJSON;
import com.stsoft.demotaxi.json.YandexCancelResponseJSON;
import com.stsoft.demotaxi.resperrors.YandexHTTPError;
import com.stsoft.demotaxi.resperrors.TaxiExchangeError;
import com.stsoft.demotaxi.response.StandartGetPricesResponse;
import com.stsoft.demotaxi.response.StandartResponse;

public class YandexCancelOrderRequest implements AggregatorRequest {
    @JsonIgnore
    private Map<String, String> params;
    @JsonIgnore
    private AuthTokenService authTokenService;
    @JsonIgnore
    private String adressCancel = "https://taxi-routeinfo.taxi.yandex.net/taxi_info";
    private StandartGetPricesResponse result;
    private TaxiExchangeError error;
    private CancelRequesJSON input;

    @Override
    public StandartResponse getResult() {
        return result;
    }

    @Override
    public TaxiExchangeError getError() {
        return error;
    }
    //YandexCancelRequestJSON
    public YandexCancelOrderRequest(Map<String, String> params, AuthTokenService authTokenService, CancelRequesJSON input) {
        this.authTokenService = authTokenService;
        this.authTokenService = authTokenService;
        this.input = input;
    }
    public StandartResponse testclient()/*RestClientException*/ {
        StandartResponse result = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        YandexCancelRequestJSON yaCancelReq = new YandexCancelRequestJSON();
        yaCancelReq.id = input.orderId;
        HttpEntity<String> entityHeaders = new HttpEntity<String>(ServiceJSON.getJSONString(yaCancelReq), headers);
        RestTemplate restTemplate = new RestTemplate();
        String res = "{\"error\" : null}";
        try {
            res = restTemplate.exchange(getRequestUrl(), HttpMethod.POST, entityHeaders, String.class).getBody();
            result = ServiceJSON.yandexToStandartCancelResponceConverter((YandexCancelResponseJSON)ServiceJSON.getJSON(res, YandexCancelResponseJSON.class));
        } catch (HttpClientErrorException ex) {
            error = new YandexHTTPError(ex);
        } catch (RestClientException ex){
            error = new YandexHTTPError(ex);
        }
        return result;
    }
    private String getRequestUrl() {
        String clid = authTokenService.getClid().get().getKeyToken();
        String apikey = authTokenService.getApiKey().get().getKeyToken();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(adressCancel)
                .queryParam("clid", clid)        
                .queryParam("apikey", apikey);    
        return builder.toString();
    }
    

}
