package com.rates.spring.rest.controller;

import com.rates.spring.rest.dto.response.base.ErrorResponse;
import com.rates.spring.rest.exceptions.BaseExceptionTemplates;
import com.rates.spring.rest.exceptions.ServiceException;
import com.rates.spring.rest.service.converter.ConverterService;
import java.nio.file.AccessDeniedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    private final ConverterService<ServiceException, ErrorResponse> exceptionToResponseConverterService;

    @Autowired
    public ExceptionControllerAdvice(
            ConverterService<ServiceException, ErrorResponse> exceptionToResponseConverterService) {
        this.exceptionToResponseConverterService = exceptionToResponseConverterService;
    }
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindingException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();

        ServiceException.ServiceExceptionBuilder builder = ServiceException
                .fromTemplate(BaseExceptionTemplates.ValidationTemplates.ValidationError);
        builder.payloadIdentity(null);

        if (fieldError != null) {
            builder.payloadFieldName(fieldError.getField());
            builder.payloadFieldValue(fieldError.getRejectedValue());
        }

        ServiceException exception = builder.build();
        return buildResponse(exception);
    }

    /**
     * В запросе отсутствует обязательный параметр
     *
     * @param e объект исключения
     * @return объект ответа
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
            MissingServletRequestParameterException e) {
        ServiceException serviceException = ServiceException.fromTemplate(BaseExceptionTemplates.ValidationTemplates.RequiredFieldIsNullOrNotPresent)
                .causedBy(e)
                .logException(false)
                .payloadFieldName(e.getParameterName())
                .build();

        return buildResponse(serviceException);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handServiceException(ServiceException exception) {
        return buildResponse(exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleDefault(Exception e) {
        ServiceException exception = ServiceException
                .fromTemplate(BaseExceptionTemplates.UnknownError)
                .causedBy(e)
                .logException(true)
                .build();

        return buildResponse(exception);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        ServiceException serviceException = ServiceException.builder()
                .status().byHttpStatus(HttpStatus.METHOD_NOT_ALLOWED)
                .errorMessage("Request method not supported")
                .internalMessage(String.format("HTTP метод %s не поддерживается", e.getMethod()))
                .logException(true)
                .build();

        return buildResponse(serviceException);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        ServiceException exception = ServiceException.builder()
                .status(403)
                .errorMessage("Insufficient permissions")
                .logException(false)
                .build();

        return buildResponse(exception);
    }

    private ResponseEntity<ErrorResponse> buildResponse(ServiceException exception) {

        if (exception.isLogOnResponse()) {
            log.error("Ошибка во время обработки запроса: ", exception);
        }

        ErrorResponse dto = exceptionToResponseConverterService.convert(exception);
        return ResponseEntity.status(dto.getStatus()).body(dto);
    }
}
