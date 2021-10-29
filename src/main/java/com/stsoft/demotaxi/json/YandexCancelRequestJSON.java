package com.stsoft.demotaxi.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class YandexCancelRequestJSON {
    public Long id;
    public String status = "CANCELED";
    public String sustatus = "ORDER_CANCELED";
}
