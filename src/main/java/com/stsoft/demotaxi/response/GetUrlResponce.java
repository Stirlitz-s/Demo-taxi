package com.stsoft.demotaxi.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class GetUrlResponce implements StandartResponse {
    public String url;
    public Long orderId;
}
