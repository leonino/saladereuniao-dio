package br.com.leonino.saladereuniao.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;
}
