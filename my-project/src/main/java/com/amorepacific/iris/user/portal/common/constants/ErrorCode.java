package com.amorepacific.iris.user.portal.common.constants;

import lombok.Getter;

@Getter
public enum ErrorCode {

	BUSINESS_EXCEPTION_ERROR(200, "9000", "비즈니스 로직 처리 중, 오류가 발생하였습니다."),
	CONVERT_EXCEPTION_ERROR(200, "9001", "데이터 변환 중, 오류가 발생하였습니다."),

    /**
     * ******************************* Custom Error CodeList ***************************************
     */
    // Transaction Insert Error
    INSERT_ERROR(200, "9101", "데이터 생성 중, 오류가 발생하였습니다."),

    // Transaction Update Error
    UPDATE_ERROR(200, "9102", "데이터 수정 중, 오류가 발생하였습니다."),

    // Transaction Delete Error
    DELETE_ERROR(200, "9103", "데이터 삭제 중, 오류가 발생하였습니다."),

    ; // End

    /**
     * ******************************* Error Code Constructor ***************************************
     */
    // 에러 코드의 '코드 상태'을 반환한다.
    private int status;

    // 에러 코드의 '코드간 구분 값'을 반환한다.
    private String divisionCode;

    // 에러 코드의 '코드 메시지'을 반환한다.
    private String message;

    // 생성자 구성
    ErrorCode(final int status, final String divisionCode, final String message) {
        this.status = status;
        this.divisionCode = divisionCode;
        this.message = message;
    }
}
