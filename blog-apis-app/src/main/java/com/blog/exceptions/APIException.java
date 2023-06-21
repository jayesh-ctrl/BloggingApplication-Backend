package com.blog.exceptions;

import com.blog.payloads.APIResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class APIException extends RuntimeException{
    public APIException() {
        super();
    }

    public APIException(String message) {
        super(message);
    }
}
