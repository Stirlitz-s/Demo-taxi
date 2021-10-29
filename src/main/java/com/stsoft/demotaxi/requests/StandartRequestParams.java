package com.stsoft.demotaxi.requests;

import java.util.Arrays;
import java.util.List;

public class StandartRequestParams {
    public static List<String> getPricesStandartParams = Arrays.asList("lang", "user_id", "start_place_point", "finish_place_point");
    public static List<String> getUrlStandartParams = Arrays.asList("lang",  "user_id", "service_name", "start_place_point", "finish_place_point");
    public static List<String> cancelOrdeStandartParams = Arrays.asList("user_id", "service_name", "order_id");
}
