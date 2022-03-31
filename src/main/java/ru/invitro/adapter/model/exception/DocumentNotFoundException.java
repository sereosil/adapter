package ru.invitro.adapter.model.exception;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Класс с информацией об ошибке "Документы не найдены в кэше".
 */
@Schema(description = "Объект с информацией об ошибке \"Документы не найдены в кэше\"")
public class DocumentNotFoundException extends Exception {

    /**
     * Сообщение об ошибке.
     */
    @Schema(description = "Сообщение об ошибке")
    private String message;

    public DocumentNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
