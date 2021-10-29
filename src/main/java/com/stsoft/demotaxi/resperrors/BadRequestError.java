package com.stsoft.demotaxi.resperrors;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonAutoDetect
@JsonTypeName("error")
public class BadRequestError implements TaxiExchangeError {
    private String name;
    private String message;
    private int code;
    private int status;
    private String type;
    public String getName() {
        return name;
    }
    public String getMessage() {
        return message;
    }
    public int getCode() {
        return code;
    }
    public int getStatus() {
        return status;
    }
    public String getType() {
        return type;
    }

    public BadRequestError () {
        name = "BadRequestError"; 
        this.message = "Bad request";
        code = 400; 
        status = 0;
        type = "BadRequestError"; 
    }
    

}
