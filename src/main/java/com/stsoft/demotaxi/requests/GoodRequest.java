package com.stsoft.demotaxi.requests;

import java.util.Map;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stsoft.demotaxi.aggregators.yandex.service.AuthTokenService;
import com.stsoft.demotaxi.json.GetPricesResponseJSON;
import com.stsoft.demotaxi.json.ServiceJSON;
import com.stsoft.demotaxi.resperrors.TaxiExchangeError;
import com.stsoft.demotaxi.response.StandartResponse;

public class GoodRequest  implements AggregatorRequest {
    private String testAnswer = "{\r\n"
            + "  \"currency\": \"RUB\",\r\n"
            + "  \"options\": [\r\n"
            + "    {\r\n"
            + "      \"class_level\": 50,\r\n"
            + "      \"class_name\": \"econom\",\r\n"
            + "      \"class_text\": \"Эконом\",\r\n"
            + "      \"min_price\": 99,\r\n"
            + "      \"price\": 239,\r\n"
            + "      \"price_text\": \"от 239 руб.\",\r\n"
            + "      \"waiting_time\": 203.98798614740372\r\n"
            + "    },\r\n"
            + "    {\r\n"
            + "      \"class_level\": 90,\r\n"
            + "      \"class_name\": \"vip\",\r\n"
            + "      \"class_text\": \"Бизнес\",\r\n"
            + "      \"min_price\": 299,\r\n"
            + "      \"price\": 299,\r\n"
            + "      \"price_text\": \"от 299 руб.\",\r\n"
            + "      \"waiting_time\": 708.15764345228672\r\n"
            + "    }\r\n"
            + "  ],\r\n"
            + "  \"time\": 3816.9397069215775 }";
    
    @JsonIgnore
    private String adressInfo = "https://taxi-routeinfo.taxi.yandex.net/taxi_info?";
    
    public StandartResponse getResult() {
        return result;
    }
    public TaxiExchangeError getError() {
        return error;
    }
    private StandartResponse result;
    private TaxiExchangeError error;

    public GoodRequest(Map<String, String> params, AuthTokenService authTokenService) {
        result = testclient();
    }
    private StandartResponse testclient()/*RestClientException*/ {
        ObjectMapper mapper = new ObjectMapper();
        GetPricesResponseJSON resp = new GetPricesResponseJSON();
        StandartResponse res = null;
        try {
            resp = (mapper.readValue(testAnswer, GetPricesResponseJSON.class));
            res = ServiceJSON.yandexToStandartConverter(resp);
        }  catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception ex){
             ex.printStackTrace();
        }
        return res;
    }
    
}
