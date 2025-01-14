package com.rbnb.rbnb.model;

public enum BookingStatus {
    PENDING,    // The reservation is awaiting approval
    ACCEPTED,   // The reservation is confirmed
    DECLINED,   // The reservation is declined
    CANCELLED   // The reservation is canceled by the client
}