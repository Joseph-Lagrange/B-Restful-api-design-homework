package com.thoughtworks.capability.gtb.restfulapidesign.common.exception;

import com.thoughtworks.capability.gtb.restfulapidesign.common.errors.ErrorCode;

/**
 * Created by wudibin
 * 2020/11/5
 */
public class BadRequestException extends HttpException {

    public BadRequestException(ErrorCode code) {
        super(code);
    }

}
