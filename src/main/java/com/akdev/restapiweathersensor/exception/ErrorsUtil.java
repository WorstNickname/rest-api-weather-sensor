package com.akdev.restapiweathersensor.exception;

import lombok.experimental.UtilityClass;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@UtilityClass
public class ErrorsUtil {

    public static void returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();

        var fieldErrors = bindingResult.getFieldErrors();
        for (FieldError error : fieldErrors) {
            msg.append(error.getField()).append(" - ")
                    .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";");
        }

        throw new MeasurementException(msg.toString());
    }

}
