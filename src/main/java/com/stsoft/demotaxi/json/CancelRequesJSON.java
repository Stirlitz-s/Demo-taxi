package com.stsoft.demotaxi.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class CancelRequesJSON {
    public Long userId;
    public Long orderId;
}
