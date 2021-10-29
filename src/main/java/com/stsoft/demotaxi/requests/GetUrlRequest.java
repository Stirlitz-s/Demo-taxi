package com.stsoft.demotaxi.requests;

import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stsoft.demotaxi.aggregators.yandex.service.AuthTokenService;
import com.stsoft.demotaxi.resperrors.TaxiExchangeError;
import com.stsoft.demotaxi.response.GetUrlResponce;
import com.stsoft.demotaxi.response.StandartResponse;

@JsonAutoDetect
public class GetUrlRequest {
    @JsonIgnore
    private Map<String, String> params;
    @JsonIgnore
    private AuthTokenService authTokenService;
    private TaxiExchangeError error;
    private StandartResponse result;
    public StandartResponse getResult() {
        return result;
    }
    public TaxiExchangeError getError() {
        return error;
    }
    public GetUrlRequest (Map<String, String> params, AuthTokenService authTokenService) {
        String app_code = "3";
        String appmetrica_tracking_id = "25395763362139037";
        String url = "https://" + app_code + ".redirect.appmetrica.yandex.com/route";
        String start_lat = params.get("start_point").split(",")[0];
        String start_lon = params.get("start_point").split(",")[1];
        String end_lat = params.get("finish_point").split(",")[0];
        String end_lon = params.get("finish_point").split(",")[1];
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("start-lat", start_lat)        
                .queryParam("start-lon", start_lon)        
                .queryParam("end-lat", end_lat)        
                .queryParam("end-lon", end_lon)        
                .queryParam("level", "10")
                .queryParam("ref", "stsoft.com")        
                .queryParam("appmetrica_tracking_id", appmetrica_tracking_id)        
                .queryParam("lang", params.get("lang"));
        result = new GetUrlResponce();
        ((GetUrlResponce)result).url = builder.toString();
    }
}
