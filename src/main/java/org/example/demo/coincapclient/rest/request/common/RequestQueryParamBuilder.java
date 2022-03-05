package org.example.demo.coincapclient.rest.request.common;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public final class RequestQueryParamBuilder {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
  private static final String EMPTY = "";

  private RequestQueryParamBuilder() {
    throw new AssertionError("Could not be instantiate!");
  }

  public static String buildQueryParam(Object object) {
    try {
      Field[] fields = object.getClass().getDeclaredFields();
      StringBuilder query = new StringBuilder(fields.length > 0 ? "?" : "");

      for (Field field : fields) {
        field.setAccessible(true);
        Object value = field.get(object);

        if (Objects.nonNull(value)) {
          query.append(field.getName()).append("=").append(formatValue(value)).append("&");
        }
      }

      return query.toString();
    } catch (Exception e) {
      return EMPTY;
    }
  }

  @SuppressWarnings("unchecked")
  private static String formatValue(Object value) {
    if (value instanceof LocalDateTime dateTime) {
      return formatDateValue(dateTime);
    }
    if (value instanceof Collection collection) {
      return formatCollectionValue(collection);
    }
    return value.toString();
  }

  private static String formatDateValue(LocalDateTime date) {
    return DATE_TIME_FORMATTER.format(date);
  }

  private static String formatCollectionValue(Collection<Object> value) {
    return value.stream().map(String::valueOf).collect(Collectors.joining(","));
  }
}
