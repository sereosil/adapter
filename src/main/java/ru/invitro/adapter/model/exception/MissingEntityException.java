package ru.invitro.adapter.model.exception;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Класс с информацией об ошибке «Не найден объект».
 */
@Schema(description = "Объект с информацией об ошибке «Не найден объект»")
public class MissingEntityException extends Exception{

    /**
     * Сообщение об ошибке.
     */
    @Schema(description = "Сообщение об ошибке")
    private String message;

    public MissingEntityException() {
        super("Internal error, cant find ContactAttribute by packageID");
    }
}
