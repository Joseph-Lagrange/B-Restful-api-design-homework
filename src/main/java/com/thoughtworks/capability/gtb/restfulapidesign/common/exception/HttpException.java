package com.thoughtworks.capability.gtb.restfulapidesign.common.exception;

import com.thoughtworks.capability.gtb.restfulapidesign.common.errors.ErrorCode;
import lombok.Getter;

/**
 * Created by wudibin
 * 2020/11/5
 */
public class HttpException extends RuntimeException {

    @Getter
    private ErrorCode errorCode;

    private HttpException(ErrorCode code, String message) {
        super(message);
        this.errorCode = code;
    }

    HttpException(ErrorCode code) {
        this(code, code.getMessage());
    }

}
