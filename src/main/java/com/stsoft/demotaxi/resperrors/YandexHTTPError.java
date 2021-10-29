package com.stsoft.demotaxi.resperrors;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stsoft.demotaxi.response.BadYandexAnswer;

@JsonAutoDetect
@JsonTypeName("error")
public class YandexHTTPError implements TaxiExchangeError {
    private String name;
    private String message;
    private int code;
    private int status;
    private String type;
    public String getName() {
        return name;
    }
    public String getMessage() {
        return message;
    }
    public int getCode() {
        return code;
    }
    public int getStatus() {
        return status;
    }
    public String getType() {
        return type;
    }

    public YandexHTTPError (HttpClientErrorException ex) {
        name = ex.getStatusText(); 
        this.message = getYandexJSONErrorMessage(ex);
        code = ex.getRawStatusCode(); 
        status = 0;
        type = ex.getClass().getName().replaceAll(ex.getClass().getPackageName() + ".", "");//   .getTypeName();//  .getName();// "yii\\web\\HttpException"; 
    }
    
    public YandexHTTPError (RestClientException ex) {
        this.message = ex.getMessage();
        status = 0;
        type = ex.getClass().getName();
    }
        
    private String getYandexJSONErrorMessage(HttpClientErrorException ex) {
        String res = null;
        ObjectMapper mapper = new ObjectMapper();
        SimpleYandexError errorYa = new SimpleYandexError("unknown");
        if (ex.getRawStatusCode() == 400 || ex.getRawStatusCode() == 403) {

            try {
                int startIndex = ex.getMessage().indexOf('[');
                if (startIndex == -1) 
                    throw new Exception("bad message");
                else {
                    String errMess = ex.getMessage().substring(startIndex+2);
                    errMess = "{" + errMess.replaceAll("]", "}");
                    errorYa = (mapper.readValue(errMess, BadYandexAnswer.class)).error;
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (Exception e) {
            }
            res = errorYa.text;
        } else {
            res = ex.getMessage();
        }
        return res;
    }


}
