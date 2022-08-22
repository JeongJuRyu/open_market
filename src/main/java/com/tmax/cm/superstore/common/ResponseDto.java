package com.tmax.cm.superstore.common;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.tmax.cm.superstore.code.ResponseCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class ResponseDto<T> {
    @NotNull
    @JsonUnwrapped
    private ResponseCode responseCode;

    private T data;
}
