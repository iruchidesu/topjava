package ru.javawebinar.topjava.util.exception;

public enum ErrorType {
    APP_ERROR("exception.appError"),
    DATA_NOT_FOUND("exception.dataNotFound"),
    DATA_ERROR("exception.dataError"),
    VALIDATION_ERROR("exception.validationError");

    private final String errorCode;

    ErrorType(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
