package com.stsoft.demotaxi.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class GetPricesResponseJSON {
    public String currency;
    public List<GetPricesOptionsJSON> options;
    public float time;
}
