package ru.invitro.adapter.model.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Класс, прокидывающий ошибку от сторонних API.
 */
@ApiModel(description = "Объект с информацией об ошибках сторонних сервисов")
public class CustomApiException extends Exception {

    /**
     * Сообщение об ошибке.
     */
    @Schema(description = "Сообщение ошибки")
    private String message;

    public CustomApiException(String message) {
        super(message);
        this.message = message;
    }
}