package com.amorepacific.iris.user.portal.common.exception;

import com.amorepacific.iris.user.portal.common.constants.ErrorCode;

import lombok.Builder;
import lombok.Getter;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -9198553659122661187L;
	
	@Getter
    private final ErrorCode errorCode;

    @Builder
    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    @Builder
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
