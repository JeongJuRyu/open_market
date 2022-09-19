package com.tmax.cm.superstore.error.exception;

import com.tmax.cm.superstore.code.ResponseCode;

public class EntityNotFoundException extends BusinessException {

    private EntityNotFoundException(ResponseCode responseCode) {
        super(responseCode);
    }

    public static EntityNotFoundException of(Class<?> notFoundEntity) {
        ResponseCode responseCode = ResponseCode.ERROR_ENTITY_NOT_FOUND;

        responseCode.setMessage(notFoundEntity.getName() + " not found");

        return new EntityNotFoundException(responseCode);
    }
}
