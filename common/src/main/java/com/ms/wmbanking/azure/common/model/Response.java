package com.ms.wmbanking.azure.common.model;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class Response {
    public final HttpStatus status;
    public final String msg;

    @Override
    public String toString() {
        return status.name() + ": " + msg;
    }
}
