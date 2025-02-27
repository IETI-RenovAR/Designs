package org.adaschool.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuotationNotFound extends RuntimeException {
  public QuotationNotFound(String id) {
    super("Quotation with id: " + id + " not found.");
  }
}