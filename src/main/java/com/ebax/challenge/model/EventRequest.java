package com.ebax.challenge.model;

public class EventRequest {
    private String type;
    private String destination;
    private String origin;
    private Integer amount;

    public String getType() {
        return type;
    }

    public String getDestination() {
        return destination;
    }

    public String getOrigin() {
        return origin;
    }

    public Integer getAmount() {
        return amount;
    }
}
