package com.stsoft.demotaxi.resperrors;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonAutoDetect
@JsonTypeName("error")
public class SimpleYandexError {
    public String text;
    public SimpleYandexError() {
        
    }
    public SimpleYandexError(String text) {
        this.text = text;
    }
}
