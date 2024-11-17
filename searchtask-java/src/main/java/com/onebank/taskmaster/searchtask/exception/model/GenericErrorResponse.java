package com.onebank.taskmaster.searchtask.exception.model;

import com.onebank.taskmaster.searchtask.exception.utils.ExceptionConstantsUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class GenericErrorResponse {

    private String errorCode;
    private String message;

    public static GenericErrorResponse internalServer() {
        return build(ExceptionConstantsUtils.INTERNAL_SERVER_ERROR, ExceptionConstantsUtils.INTERNAL_SERVER_ERROR_MESSAGE);
    }
    public static GenericErrorResponse badRequest(String msg) {
        return build(ExceptionConstantsUtils.BAD_REQUEST, msg);
    }

    public static GenericErrorResponse badRequest(String errorCode, String msg) {
        return build(errorCode, msg);
    }

    public static GenericErrorResponse notFound(String msg) {
        return build(ExceptionConstantsUtils.NOT_FOUND, msg);
    }

    public static GenericErrorResponse build(String code, String msg){
        return new GenericErrorResponse(code, msg);
    }

}
