package com.stsoft.demotaxi.resperrors;

public interface TaxiExchangeError {
    public String getName();
    public String getMessage();
    public int getCode();
    public int getStatus();
    public String getType();
}
