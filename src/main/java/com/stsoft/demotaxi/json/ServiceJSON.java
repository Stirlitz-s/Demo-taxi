package com.stsoft.demotaxi.json;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stsoft.demotaxi.resperrors.SimpleYandexError;
import com.stsoft.demotaxi.response.BadYandexAnswer;
import com.stsoft.demotaxi.response.CancelOrderResponse;
import com.stsoft.demotaxi.response.StandartGetPricesResponse;
import com.stsoft.demotaxi.response.StandartResponse;

public class ServiceJSON {
    public static String getJSONString(Object request) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }
    public static StandartResponse yandexToStandartConverter(GetPricesResponseJSON input) {
        StandartGetPricesResponse res = new StandartGetPricesResponse();
        res.serviceName = "Yandex.Taxi";
        res.currency = input.currency;
        res.options = input.options;
        res.time = input.time;
        return res;
    }
    
    public static StandartResponse yandexToStandartCancelResponceConverter(YandexCancelResponseJSON input) {
        CancelOrderResponse res = new CancelOrderResponse();
        res.message = input.updateStatus;
        res.orderId = input.id;
        res.status = input.status;
        res.service_name = "Yandex.taxi";
        return res;
    }
    
    public static Object getJSON(String input, Class<?> nclass) {
        ObjectMapper mapper = new ObjectMapper();
        Object result = null;
        try {
            result = (mapper.readValue(input, nclass));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }
        return result;
    }

}
