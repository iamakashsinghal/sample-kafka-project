package com.loma.kkr.webclient.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author akash
 */
@Getter
@AllArgsConstructor
public class ApiResponseException extends RuntimeException {

    public ApiResponseException(String message) {
        super(message);
    }

}
