package org.example.demo.domain.asset.web.rest.exhandler;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.demo.common.exception.DataNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings("ALL")
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


  private static final String FORMAT = "%s: %s";


  @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final var error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

    final var apiError = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getLocalizedMessage(), List.of(error));
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
  }

  // 404
  @ExceptionHandler(value = {DataNotFoundException.class})
  protected ResponseEntity<Object> handleNotFound(final RuntimeException ex, final WebRequest request) {
    final var apiError = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(),
        Collections.singletonList(ex.getMessage()));
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
  }

  //500
  @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class})
  public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("500 Status Code", ex);
    final var apiError = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
        Collections.singletonList("error occurred"));
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
  }

  // 500
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("500 Status Code", ex);
    final var apiError = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), List.of("error occurred"));
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
  }

  // 400
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final var errors = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(error -> String.format(FORMAT, error.getField(), error.getDefaultMessage()))
        .toList();

    ex.getBindingResult().getGlobalErrors()
        .stream()
        .map(error -> String.format(FORMAT, error.getObjectName(), error.getDefaultMessage()))
        .forEach(errors::add);
    final var apiError = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
    return handleExceptionInternal(ex, apiError, headers, apiError.status(), request);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final List<String> errors = ex.getBindingResult().getFieldErrors()
        .stream()
        .map(error -> String.format(FORMAT, error.getField(), error.getDefaultMessage()))
        .toList();

    ex.getBindingResult().getGlobalErrors()
        .stream()
        .map(error -> error.getObjectName() + ": " + error.getDefaultMessage())
        .forEach(errors::add);

    final var apiError = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
    return handleExceptionInternal(ex, apiError, headers, apiError.status(), request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final var error = String.format("%s value for %s should be of type %s",
        ex.getValue(),
        ex.getPropertyName(),
        ex.getRequiredType());

    final var apiError = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), List.of(error));
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final var error = ex.getRequestPartName() + " part is missing";
    final var apiError = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), List.of(error));
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final var error = ex.getParameterName() + " parameter is missing";
    final var apiError = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), List.of(error));
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
  }

  // 404
  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

    final var apiError = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), List.of(error));
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
  }

  // 405
  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final StringBuilder builder = new StringBuilder();
    builder.append(ex.getMethod());
    builder.append(" method is not supported for this request. Supported methods are ");
    Objects.requireNonNull(ex.getSupportedHttpMethods()).forEach(t -> builder.append(t).append(" "));

    final var apiError = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), List.of(builder.toString()));
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
  }

  // 415
  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final var builder = new StringBuilder();
    builder.append(ex.getContentType());
    builder.append(" media type is not supported. Supported media types are ");
    ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(" "));

    final var apiError = new ErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(),
        Collections.singletonList(builder.substring(0, builder.length() - 2)));
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
  }
}