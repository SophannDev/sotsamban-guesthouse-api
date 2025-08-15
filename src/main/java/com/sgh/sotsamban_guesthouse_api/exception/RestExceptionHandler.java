package com.sgh.sotsamban_guesthouse_api.exception;

import com.sgh.sotsamban_guesthouse_api.common.ApiStatus;
import com.sgh.sotsamban_guesthouse_api.common.EmptyJsonResponse;
import com.sgh.sotsamban_guesthouse_api.common.StatusCode;
import com.sgh.sotsamban_guesthouse_api.dto.response.ApiResponse;
import com.sgh.sotsamban_guesthouse_api.logging.AppLogManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Component
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {



    /**
     * Handle HandleBusinessException
     *
     * @param ex BusinessException
     * @return the ApiError object
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(final BusinessException ex) {

        AppLogManager.error(ex);
        StatusCode statusCode = ex.getErrorCode();
        ApiStatus apiStatus = new ApiStatus(statusCode);
        apiStatus.setMessage(StringUtils.defaultIfBlank(ex.getMessage(), statusCode.getMessage()));
//        apiStatus.setMessage(MessageHelper.getMessage(String.valueOf(statusCode.getCode()), statusCode.getMessage()));
        return buildResponseEntity(apiStatus, ex.getBody(), statusCode.getHttpCode());

    }

    /**
     * Handle handleThrowable
     *
     * @param ex      Throwable
     * @return the ApiError object
     */
    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleThrowable(Throwable ex) {
        AppLogManager.error(ex);
//        apiError.setMessage(ex.getMessage());
//        apiError.setDebugMessage(ExceptionUtils.getStackTrace(ex));

        return buildResponseEntity(new ApiStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"));

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        AppLogManager.error(ex);
        return buildResponseEntity(new ApiStatus(statusCode.value(), "Internal Server Error"));
    }

    public ResponseEntity<Object> buildResponseEntity(ApiStatus apiStatus) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(apiStatus, new EmptyJsonResponse());

        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiStatus.getCode()));
    }

    public ResponseEntity<Object> buildResponseEntity(ApiStatus apiStatus, Object data, int httpCode) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(apiStatus, data == null ? new EmptyJsonResponse() : data);

        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(httpCode));
    }

}
