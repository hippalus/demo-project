package org.example.demo.common.rest;

import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.common.exception.BusinessException;
import org.example.demo.common.exception.DataNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends BaseController {


  private final MessageSource messageSource;

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response<ErrorResponse> handleException(Exception exception) {
    log.error("An error occurred! Details: ", exception);
    return createErrorResponseFromMessageSource("common.system.error.occurred");
  }

  @ExceptionHandler(WebExchangeBindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Response<ErrorResponse> handleRequestPropertyBindingError(WebExchangeBindException webExchangeBindException) {
    log.debug("Bad request!", webExchangeBindException);
    return createFieldErrorResponse(webExchangeBindException.getBindingResult());
  }

  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Response<ErrorResponse> handleBindException(BindException bindException) {
    log.debug("Bad request!", bindException);
    return createFieldErrorResponse(bindException.getBindingResult());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Response<ErrorResponse> handleInvalidArgumentException(MethodArgumentNotValidException methodArgumentNotValidException) {
    log.debug("Method argument not valid. Message: $methodArgumentNotValidException.message", methodArgumentNotValidException);
    return createFieldErrorResponse(methodArgumentNotValidException.getBindingResult());
  }

  @ExceptionHandler(BusinessException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public Response<ErrorResponse> handleTicketApiBusinessException(BusinessException ex) {
    return createErrorResponseFromMessageSource(ex.getKey(), ex.getArgs());
  }

  @ExceptionHandler(DataNotFoundException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public Response<ErrorResponse> handleTicketApiDataNotFoundException(DataNotFoundException ex) {
    return createErrorResponseFromMessageSource(ex.getKey(), ex.getArgs());
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  public Response<ErrorResponse> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
    log.trace("MethodArgumentTypeMismatchException occurred", methodArgumentTypeMismatchException);
    return createErrorResponseFromMessageSource("common.client.typeMismatch",
        methodArgumentTypeMismatchException.getName());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public Response<ErrorResponse> handleMethodNotSupportedException(
      HttpRequestMethodNotSupportedException methodNotSupportedException) {
    log.debug("HttpRequestMethodNotSupportedException occurred", methodNotSupportedException);
    return createErrorResponseFromMessageSource("common.client.methodNotSupported",
        methodNotSupportedException.getMethod());
  }

  private Response<ErrorResponse> createFieldErrorResponse(BindingResult bindingResult) {
    List<String> requiredFieldErrorMessages = retrieveLocalizationMessage("common.client.requiredField");
    String code = requiredFieldErrorMessages.get(0);

    String errorMessage = bindingResult
        .getFieldErrors().stream()
        .map(FieldError::getField)
        .map(error -> retrieveLocalizationMessage("common.client.requiredField", error))
        .map(errorMessageList -> errorMessageList.get(1))
        .collect(Collectors.joining(" && "));

    log.debug("Exception occurred while request validation: {}", errorMessage);

    return respond(new ErrorResponse(code, errorMessage));
  }

  private Response<ErrorResponse> createErrorResponseFromMessageSource(String key, String... args) {
    List<String> messageList = retrieveLocalizationMessage(key, args);
    return respond(new ErrorResponse(messageList.get(0), messageList.get(1)));
  }

  private List<String> retrieveLocalizationMessage(String key, String... args) {
    String message = messageSource.getMessage(key, args, Locale.forLanguageTag("en"));
    return Pattern.compile(";").splitAsStream(message).toList();
  }
}