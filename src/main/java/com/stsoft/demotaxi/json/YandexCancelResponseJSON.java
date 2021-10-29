package com.stsoft.demotaxi.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class YandexCancelResponseJSON {
   /* "id": {int64},
    "updateStatus": "{string}",
    "status": "{enum}", 
    "substatus": "{enum}",
    "errorDetails": "{string}"*/
    public Long id;
    public String updateStatus;
    public String status;
    public String errorDetails;
}
