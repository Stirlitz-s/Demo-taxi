package com.stsoft.demotaxi.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class GetPricesOptionsJSON {
    public int class_level;
    public String class_name;
    public String class_text;
    public int min_price;
    public int price;
    public String price_text;
    public float waiting_time;
}
