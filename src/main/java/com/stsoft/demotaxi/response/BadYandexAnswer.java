package com.stsoft.demotaxi.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.stsoft.demotaxi.resperrors.SimpleYandexError;

@JsonAutoDetect
public class BadYandexAnswer {
    public SimpleYandexError error;
    public BadYandexAnswer () {
        
    }
}
