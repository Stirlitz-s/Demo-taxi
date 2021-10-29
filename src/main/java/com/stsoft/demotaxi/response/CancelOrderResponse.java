package com.stsoft.demotaxi.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class CancelOrderResponse implements StandartResponse {
    public long orderId;
    public String status;
    public String message;
    public String service_name;
}
