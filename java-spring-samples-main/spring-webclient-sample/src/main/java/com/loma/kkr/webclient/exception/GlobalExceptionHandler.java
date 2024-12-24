package com.loma.kkr.webclient.exception;

import com.loma.kkr.common.model.ResultResp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

/**
 * @author akash
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler({ApiResponseException.class})
    public ResultResp<Object> handleApiResponseException(ApiResponseException ex) {
        log.error(ex.getLocalizedMessage());
        return ResultResp.failed(ex.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getAllErrors()
                .stream()
                .map(objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale()))
                .toList();
        ProblemDetail pd = ex.getBody();
        pd.setProperty("binding_errors", errorMessages);
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
    
    @ExceptionHandler({RuntimeException.class})
    public ResultResp<Object> handleRuntimeException(RuntimeException exception) {
        log.error("handleRuntimeException: ", exception);
        return ResultResp.failed(exception.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public ResultResp<Object> handleException(Exception e) {
        log.error("handleException: ", e);
        return ResultResp.failed("An unexpected error occurred: " + e.getMessage());
    }

    @ResponseStatus
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("handleIllegalArgumentException: ", e);
        return new ResponseEntity<>("Illegal argument: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ResultResp<Object> handleWebClientResponseException(WebClientResponseException ex) {
        log.error(ex.getLocalizedMessage());
        return ResultResp.failed(ex.getLocalizedMessage());
    }

}
