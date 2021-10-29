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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stsoft.demotaxi.aggregators.yandex.service.AuthTokenService;
import com.stsoft.demotaxi.json.GetPricesResponseJSON;
import com.stsoft.demotaxi.json.ServiceJSON;
import com.stsoft.demotaxi.resperrors.YandexHTTPError;
import com.stsoft.demotaxi.resperrors.TaxiExchangeError;
import com.stsoft.demotaxi.response.StandartResponse;

@JsonAutoDetect
public class BadRequest implements AggregatorRequest {
    
    @JsonIgnore
    private Map<String, String> params;
    @JsonIgnore
    private AuthTokenService authTokenService;
    @JsonIgnore
    private String adressInfo = "https://taxi-routeinfo.taxi.yandex.net/taxi_info";
    private StandartResponse result;
    private TaxiExchangeError error;
    
    public StandartResponse getResult() {
        return result;
    }
    public TaxiExchangeError getError() {
        return error;
    }
    public void setError(TaxiExchangeError error) {
        this.error = error;
    }
    public BadRequest(Map<String, String> params, AuthTokenService authTokenService) {
        this.params = params;
        this.authTokenService = authTokenService;
        result = testclient();
//        error = null;//new ApproveError(null);
    }
    
    public StandartResponse testclient()/*RestClientException*/ {
        StandartResponse result = null;
        HttpHeaders headers = new HttpHeaders();
        GetPricesResponseJSON resp = new GetPricesResponseJSON();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entityHeaders = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        String res = "{\"error\" : null}";
        try {
            res = restTemplate.exchange(getRequestUrl(), HttpMethod.GET, entityHeaders, String.class).getBody();
            resp = (GetPricesResponseJSON) ServiceJSON.getJSON(res, GetPricesResponseJSON.class);
            result = ServiceJSON.yandexToStandartConverter(resp);
        } catch (HttpClientErrorException ex) {
            error = new YandexHTTPError(ex);
        } catch (RestClientException ex){
            error = new YandexHTTPError(ex);
       //     ex.printStackTrace();
        }
        return result;
    }
    private String getRequestUrl() {
        String clid = authTokenService.getClid().get().getKeyToken();
        String apikey = authTokenService.getApiKey().get().getKeyToken();
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(adressInfo)
                .queryParam("rll", params.get("start_point") + "~" + params.get("finish_point"))        
                .queryParam("clid", clid)        
                .queryParam("apikey", apikey)        
                .queryParam("lang", params.get("lang"))        
                .queryParam("class", "econom");
        return builder.toString();
    }
    

}
