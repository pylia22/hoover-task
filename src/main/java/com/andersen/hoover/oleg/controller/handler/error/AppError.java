package com.andersen.hoover.oleg.controller.handler.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AppError {

    private int status;
    private String message;
}
