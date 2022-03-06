package org.example.demo.domain.asset.web.rest.exhandler;

import java.util.List;
import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String errorMessage, List<String> errors) {

}