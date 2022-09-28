package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;

public class InternalServerErrorException extends BusinessException{
    public InternalServerErrorException(ResponseCode responseCode){ super(responseCode.ERROR_INTERNAL_SERVER_ERROR); }
}
