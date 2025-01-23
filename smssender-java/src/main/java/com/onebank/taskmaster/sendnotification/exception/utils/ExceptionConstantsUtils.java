package com.onebank.taskmaster.sendnotification.exception.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionConstantsUtils {
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    public static final String UNAUTHORIZED_MESSAGE = "User not authenticated";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong";
    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String BAD_REQUEST_ERROR_MESSAGE = "Invalid parameters";
    public static final String METHOD_NOT_ALLOWED = "METHOD_NOT_ALLOWED";
    public static final String UNSUPPORTED_MEDIA_TYPE = "UNSUPPORTED_MEDIA_TYPE";
    public static final String NOT_FOUND = "NOT_FOUND";
}
