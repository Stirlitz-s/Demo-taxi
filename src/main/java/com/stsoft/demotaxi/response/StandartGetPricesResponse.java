package com.stsoft.demotaxi.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.stsoft.demotaxi.json.GetPricesOptionsJSON;

@JsonAutoDetect
public class StandartGetPricesResponse implements StandartResponse {
    public String serviceName;
    public String currency;
    public List<GetPricesOptionsJSON> options;
    public float time;
}
